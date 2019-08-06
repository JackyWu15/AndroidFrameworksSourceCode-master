/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include "rsCpuCore.h"
#include "rsCpuScript.h"
#include "rsCpuScriptGroup.h"

#include <malloc.h>
#include "rsContext.h"

#include <sys/types.h>
#include <sys/resource.h>
#include <sched.h>
#include <sys/syscall.h>
#include <string.h>
#include <unistd.h>

#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>

#if !defined(RS_SERVER) && !defined(RS_COMPATIBILITY_LIB)
#include <cutils/properties.h>
#include "utils/StopWatch.h"
#endif

#ifdef RS_SERVER
// Android exposes gettid(), standard Linux does not
static pid_t gettid() {
    return syscall(SYS_gettid);
}
#endif

using namespace android;
using namespace android::renderscript;

typedef void (*outer_foreach_t)(
    const android::renderscript::RsForEachStubParamStruct *,
    uint32_t x1, uint32_t x2,
    uint32_t instep, uint32_t outstep);


static pthread_key_t gThreadTLSKey = 0;
static uint32_t gThreadTLSKeyCount = 0;
static pthread_mutex_t gInitMutex = PTHREAD_MUTEX_INITIALIZER;

bool android::renderscript::gArchUseSIMD = false;

RsdCpuReference::~RsdCpuReference() {
}

RsdCpuReference * RsdCpuReference::create(Context *rsc, uint32_t version_major,
        uint32_t version_minor, sym_lookup_t lfn, script_lookup_t slfn
#ifndef RS_COMPATIBILITY_LIB
        , bcc::RSLinkRuntimeCallback pLinkRuntimeCallback,
        RSSelectRTCallback pSelectRTCallback,
        const char *pBccPluginName
#endif
        ) {

    RsdCpuReferenceImpl *cpu = new RsdCpuReferenceImpl(rsc);
    if (!cpu) {
        return NULL;
    }
    if (!cpu->init(version_major, version_minor, lfn, slfn)) {
        delete cpu;
        return NULL;
    }

#ifndef RS_COMPATIBILITY_LIB
    cpu->setLinkRuntimeCallback(pLinkRuntimeCallback);
    cpu->setSelectRTCallback(pSelectRTCallback);
    if (pBccPluginName) {
        cpu->setBccPluginName(pBccPluginName);
    }
#endif

    return cpu;
}


Context * RsdCpuReference::getTlsContext() {
    ScriptTLSStruct * tls = (ScriptTLSStruct *)pthread_getspecific(gThreadTLSKey);
    return tls->mContext;
}

const Script * RsdCpuReference::getTlsScript() {
    ScriptTLSStruct * tls = (ScriptTLSStruct *)pthread_getspecific(gThreadTLSKey);
    return tls->mScript;
}

pthread_key_t RsdCpuReference::getThreadTLSKey(){ return gThreadTLSKey; }

////////////////////////////////////////////////////////////
///

RsdCpuReferenceImpl::RsdCpuReferenceImpl(Context *rsc) {
    mRSC = rsc;

    version_major = 0;
    version_minor = 0;
    mInForEach = false;
    memset(&mWorkers, 0, sizeof(mWorkers));
    memset(&mTlsStruct, 0, sizeof(mTlsStruct));
    mExit = false;
#ifndef RS_COMPATIBILITY_LIB
    mLinkRuntimeCallback = NULL;
    mSelectRTCallback = NULL;
    mSetupCompilerCallback = NULL;
#endif
}


void * RsdCpuReferenceImpl::helperThreadProc(void *vrsc) {
    RsdCpuReferenceImpl *dc = (RsdCpuReferenceImpl *)vrsc;

    uint32_t idx = __sync_fetch_and_add(&dc->mWorkers.mLaunchCount, 1);

    //ALOGV("RS helperThread starting %p idx=%i", dc, idx);

    dc->mWorkers.mLaunchSignals[idx].init();
    dc->mWorkers.mNativeThreadId[idx] = gettid();

    memset(&dc->mTlsStruct, 0, sizeof(dc->mTlsStruct));
    int status = pthread_setspecific(gThreadTLSKey, &dc->mTlsStruct);
    if (status) {
        ALOGE("pthread_setspecific %i", status);
    }

#if 0
    typedef struct {uint64_t bits[1024 / 64]; } cpu_set_t;
    cpu_set_t cpuset;
    memset(&cpuset, 0, sizeof(cpuset));
    cpuset.bits[idx / 64] |= 1ULL << (idx % 64);
    int ret = syscall(241, rsc->mWorkers.mNativeThreadId[idx],
              sizeof(cpuset), &cpuset);
    ALOGE("SETAFFINITY ret = %i %s", ret, EGLUtils::strerror(ret));
#endif

    while (!dc->mExit) {
        dc->mWorkers.mLaunchSignals[idx].wait();
        if (dc->mWorkers.mLaunchCallback) {
           // idx +1 is used because the calling thread is always worker 0.
           dc->mWorkers.mLaunchCallback(dc->mWorkers.mLaunchData, idx+1);
        }
        __sync_fetch_and_sub(&dc->mWorkers.mRunningCount, 1);
        dc->mWorkers.mCompleteSignal.set();
    }

    //ALOGV("RS helperThread exited %p idx=%i", dc, idx);
    return NULL;
}

void RsdCpuReferenceImpl::launchThreads(WorkerCallback_t cbk, void *data) {
    mWorkers.mLaunchData = data;
    mWorkers.mLaunchCallback = cbk;

    // fast path for very small launches
    MTLaunchStruct *mtls = (MTLaunchStruct *)data;
    if (mtls && mtls->fep.dimY <= 1 && mtls->xEnd <= mtls->xStart + mtls->mSliceSize) {
        if (mWorkers.mLaunchCallback) {
            mWorkers.mLaunchCallback(mWorkers.mLaunchData, 0);
        }
        return;
    }

    mWorkers.mRunningCount = mWorkers.mCount;
    __sync_synchronize();

    for (uint32_t ct = 0; ct < mWorkers.mCount; ct++) {
        mWorkers.mLaunchSignals[ct].set();
    }

    // We use the calling thread as one of the workers so we can start without
    // the delay of the thread wakeup.
    if (mWorkers.mLaunchCallback) {
        mWorkers.mLaunchCallback(mWorkers.mLaunchData, 0);
    }

    while (__sync_fetch_and_or(&mWorkers.mRunningCount, 0) != 0) {
        mWorkers.mCompleteSignal.wait();
    }
}


void RsdCpuReferenceImpl::lockMutex() {
    pthread_mutex_lock(&gInitMutex);
}

void RsdCpuReferenceImpl::unlockMutex() {
    pthread_mutex_unlock(&gInitMutex);
}

static int
read_file(const char*  pathname, char*  buffer, size_t  buffsize)
{
    int  fd, len;

    fd = open(pathname, O_RDONLY);
    if (fd < 0)
        return -1;

    do {
        len = read(fd, buffer, buffsize);
    } while (len < 0 && errno == EINTR);

    close(fd);

    return len;
}

static void GetCpuInfo() {
    char cpuinfo[4096];
    int  cpuinfo_len;

    cpuinfo_len = read_file("/proc/cpuinfo", cpuinfo, sizeof cpuinfo);
    if (cpuinfo_len < 0)  /* should not happen */ {
        return;
    }

#if defined(ARCH_ARM_HAVE_VFP) || defined(ARCH_ARM_USE_INTRINSICS)
    gArchUseSIMD = (!!strstr(cpuinfo, " neon")) ||
                   (!!strstr(cpuinfo, " asimd"));
#elif defined(ARCH_X86_HAVE_SSSE3)
    gArchUseSIMD = !!strstr(cpuinfo, " ssse3");
#endif
}

bool RsdCpuReferenceImpl::init(uint32_t version_major, uint32_t version_minor,
                               sym_lookup_t lfn, script_lookup_t slfn) {

    mSymLookupFn = lfn;
    mScriptLookupFn = slfn;

    lockMutex();
    if (!gThreadTLSKeyCount) {
        int status = pthread_key_create(&gThreadTLSKey, NULL);
        if (status) {
            ALOGE("Failed to init thread tls key.");
            unlockMutex();
            return false;
        }
    }
    gThreadTLSKeyCount++;
    unlockMutex();

    mTlsStruct.mContext = mRSC;
    mTlsStruct.mScript = NULL;
    int status = pthread_setspecific(gThreadTLSKey, &mTlsStruct);
    if (status) {
        ALOGE("pthread_setspecific %i", status);
    }

    GetCpuInfo();

    int cpu = sysconf(_SC_NPROCESSORS_ONLN);
    if(mRSC->props.mDebugMaxThreads) {
        cpu = mRSC->props.mDebugMaxThreads;
    }
    if (cpu < 2) {
        mWorkers.mCount = 0;
        return true;
    }

    // Subtract one from the cpu count because we also use the command thread as a worker.
    mWorkers.mCount = (uint32_t)(cpu - 1);

    ALOGV("%p Launching thread(s), CPUs %i", mRSC, mWorkers.mCount + 1);

    mWorkers.mThreadId = (pthread_t *) calloc(mWorkers.mCount, sizeof(pthread_t));
    mWorkers.mNativeThreadId = (pid_t *) calloc(mWorkers.mCount, sizeof(pid_t));
    mWorkers.mLaunchSignals = new Signal[mWorkers.mCount];
    mWorkers.mLaunchCallback = NULL;

    mWorkers.mCompleteSignal.init();

    mWorkers.mRunningCount = mWorkers.mCount;
    mWorkers.mLaunchCount = 0;
    __sync_synchronize();

    pthread_attr_t threadAttr;
    status = pthread_attr_init(&threadAttr);
    if (status) {
        ALOGE("Failed to init thread attribute.");
        return false;
    }

    for (uint32_t ct=0; ct < mWorkers.mCount; ct++) {
        status = pthread_create(&mWorkers.mThreadId[ct], &threadAttr, helperThreadProc, this);
        if (status) {
            mWorkers.mCount = ct;
            ALOGE("Created fewer than expected number of RS threads.");
            break;
        }
    }
    while (__sync_fetch_and_or(&mWorkers.mRunningCount, 0) != 0) {
        usleep(100);
    }

    pthread_attr_destroy(&threadAttr);
    return true;
}


void RsdCpuReferenceImpl::setPriority(int32_t priority) {
    for (uint32_t ct=0; ct < mWorkers.mCount; ct++) {
        setpriority(PRIO_PROCESS, mWorkers.mNativeThreadId[ct], priority);
    }
}

RsdCpuReferenceImpl::~RsdCpuReferenceImpl() {
    mExit = true;
    mWorkers.mLaunchData = NULL;
    mWorkers.mLaunchCallback = NULL;
    mWorkers.mRunningCount = mWorkers.mCount;
    __sync_synchronize();
    for (uint32_t ct = 0; ct < mWorkers.mCount; ct++) {
        mWorkers.mLaunchSignals[ct].set();
    }
    void *res;
    for (uint32_t ct = 0; ct < mWorkers.mCount; ct++) {
        pthread_join(mWorkers.mThreadId[ct], &res);
    }
    rsAssert(__sync_fetch_and_or(&mWorkers.mRunningCount, 0) == 0);
    free(mWorkers.mThreadId);
    free(mWorkers.mNativeThreadId);
    delete[] mWorkers.mLaunchSignals;

    // Global structure cleanup.
    lockMutex();
    --gThreadTLSKeyCount;
    if (!gThreadTLSKeyCount) {
        pthread_key_delete(gThreadTLSKey);
    }
    unlockMutex();

}

typedef void (*rs_t)(const void *, void *, const void *, uint32_t, uint32_t, uint32_t, uint32_t);

static void wc_xy(void *usr, uint32_t idx) {
    MTLaunchStruct *mtls = (MTLaunchStruct *)usr;
    RsForEachStubParamStruct p;
    memcpy(&p, &mtls->fep, sizeof(p));
    p.lid = idx;
    uint32_t sig = mtls->sig;

    outer_foreach_t fn = (outer_foreach_t) mtls->kernel;
    while (1) {
        uint32_t slice = (uint32_t)__sync_fetch_and_add(&mtls->mSliceNum, 1);
        uint32_t yStart = mtls->yStart + slice * mtls->mSliceSize;
        uint32_t yEnd = yStart + mtls->mSliceSize;
        yEnd = rsMin(yEnd, mtls->yEnd);
        if (yEnd <= yStart) {
            return;
        }

        //ALOGE("usr idx %i, x %i,%i  y %i,%i", idx, mtls->xStart, mtls->xEnd, yStart, yEnd);
        //ALOGE("usr ptr in %p,  out %p", mtls->fep.ptrIn, mtls->fep.ptrOut);

        for (p.y = yStart; p.y < yEnd; p.y++) {
            p.out = mtls->fep.ptrOut + (mtls->fep.yStrideOut * p.y) +
                    (mtls->fep.eStrideOut * mtls->xStart);
            p.in = mtls->fep.ptrIn + (mtls->fep.yStrideIn * p.y) +
                   (mtls->fep.eStrideIn * mtls->xStart);
            fn(&p, mtls->xStart, mtls->xEnd, mtls->fep.eStrideIn, mtls->fep.eStrideOut);
        }
    }
}

static void wc_x(void *usr, uint32_t idx) {
    MTLaunchStruct *mtls = (MTLaunchStruct *)usr;
    RsForEachStubParamStruct p;
    memcpy(&p, &mtls->fep, sizeof(p));
    p.lid = idx;
    uint32_t sig = mtls->sig;

    outer_foreach_t fn = (outer_foreach_t) mtls->kernel;
    while (1) {
        uint32_t slice = (uint32_t)__sync_fetch_and_add(&mtls->mSliceNum, 1);
        uint32_t xStart = mtls->xStart + slice * mtls->mSliceSize;
        uint32_t xEnd = xStart + mtls->mSliceSize;
        xEnd = rsMin(xEnd, mtls->xEnd);
        if (xEnd <= xStart) {
            return;
        }

        //ALOGE("usr slice %i idx %i, x %i,%i", slice, idx, xStart, xEnd);
        //ALOGE("usr ptr in %p,  out %p", mtls->fep.ptrIn, mtls->fep.ptrOut);

        p.out = mtls->fep.ptrOut + (mtls->fep.eStrideOut * xStart);
        p.in = mtls->fep.ptrIn + (mtls->fep.eStrideIn * xStart);
        fn(&p, xStart, xEnd, mtls->fep.eStrideIn, mtls->fep.eStrideOut);
    }
}

void RsdCpuReferenceImpl::launchThreads(const Allocation * ain, Allocation * aout,
                                     const RsScriptCall *sc, MTLaunchStruct *mtls) {

    //android::StopWatch kernel_time("kernel time");

    if ((mWorkers.mCount >= 1) && mtls->isThreadable && !mInForEach) {
        const size_t targetByteChunk = 16 * 1024;
        mInForEach = true;
        if (mtls->fep.dimY > 1) {
            uint32_t s1 = mtls->fep.dimY / ((mWorkers.mCount + 1) * 4);
            uint32_t s2 = 0;

            // This chooses our slice size to rate limit atomic ops to
            // one per 16k bytes of reads/writes.
            if (mtls->fep.yStrideOut) {
                s2 = targetByteChunk / mtls->fep.yStrideOut;
            } else {
                s2 = targetByteChunk / mtls->fep.yStrideIn;
            }
            mtls->mSliceSize = rsMin(s1, s2);

            if(mtls->mSliceSize < 1) {
                mtls->mSliceSize = 1;
            }

         //   mtls->mSliceSize = 2;
            launchThreads(wc_xy, mtls);
        } else {
            uint32_t s1 = mtls->fep.dimX / ((mWorkers.mCount + 1) * 4);
            uint32_t s2 = 0;

            // This chooses our slice size to rate limit atomic ops to
            // one per 16k bytes of reads/writes.
            if (mtls->fep.eStrideOut) {
                s2 = targetByteChunk / mtls->fep.eStrideOut;
            } else {
                s2 = targetByteChunk / mtls->fep.eStrideIn;
            }
            mtls->mSliceSize = rsMin(s1, s2);

            if(mtls->mSliceSize < 1) {
                mtls->mSliceSize = 1;
            }

            launchThreads(wc_x, mtls);
        }
        mInForEach = false;

        //ALOGE("launch 1");
    } else {
        RsForEachStubParamStruct p;
        memcpy(&p, &mtls->fep, sizeof(p));
        uint32_t sig = mtls->sig;

        //ALOGE("launch 3");
        outer_foreach_t fn = (outer_foreach_t) mtls->kernel;
        for (p.ar[0] = mtls->arrayStart; p.ar[0] < mtls->arrayEnd; p.ar[0]++) {
            for (p.z = mtls->zStart; p.z < mtls->zEnd; p.z++) {
                for (p.y = mtls->yStart; p.y < mtls->yEnd; p.y++) {
                    uint32_t offset = mtls->fep.dimY * mtls->fep.dimZ * p.ar[0] +
                                      mtls->fep.dimY * p.z + p.y;
                    p.out = mtls->fep.ptrOut + (mtls->fep.yStrideOut * offset) +
                            (mtls->fep.eStrideOut * mtls->xStart);
                    p.in = mtls->fep.ptrIn + (mtls->fep.yStrideIn * offset) +
                           (mtls->fep.eStrideIn * mtls->xStart);
                    fn(&p, mtls->xStart, mtls->xEnd, mtls->fep.eStrideIn, mtls->fep.eStrideOut);
                }
            }
        }
    }
}

void RsdCpuReferenceImpl::launchThreads(const Allocation** ains, uint32_t inLen, Allocation* aout,
                                        const RsScriptCall* sc, MTLaunchStruct* mtls) {

    //android::StopWatch kernel_time("kernel time");

    if ((mWorkers.mCount >= 1) && mtls->isThreadable && !mInForEach) {
        const size_t targetByteChunk = 16 * 1024;
        mInForEach = true;
        if (mtls->fep.dimY > 1) {
            uint32_t s1 = mtls->fep.dimY / ((mWorkers.mCount + 1) * 4);
            uint32_t s2 = 0;

            // This chooses our slice size to rate limit atomic ops to
            // one per 16k bytes of reads/writes.
            if (mtls->fep.yStrideOut) {
                s2 = targetByteChunk / mtls->fep.yStrideOut;
            } else {
                s2 = targetByteChunk / mtls->fep.yStrideIn;
            }
            mtls->mSliceSize = rsMin(s1, s2);

            if(mtls->mSliceSize < 1) {
                mtls->mSliceSize = 1;
            }

         //   mtls->mSliceSize = 2;
            launchThreads(wc_xy, mtls);
        } else {
            uint32_t s1 = mtls->fep.dimX / ((mWorkers.mCount + 1) * 4);
            uint32_t s2 = 0;

            // This chooses our slice size to rate limit atomic ops to
            // one per 16k bytes of reads/writes.
            if (mtls->fep.eStrideOut) {
                s2 = targetByteChunk / mtls->fep.eStrideOut;
            } else {
                s2 = targetByteChunk / mtls->fep.eStrideIn;
            }
            mtls->mSliceSize = rsMin(s1, s2);

            if (mtls->mSliceSize < 1) {
                mtls->mSliceSize = 1;
            }

            launchThreads(wc_x, mtls);
        }
        mInForEach = false;

        //ALOGE("launch 1");
    } else {
        RsForEachStubParamStruct p;
        memcpy(&p, &mtls->fep, sizeof(p));
        uint32_t sig = mtls->sig;

        // Allocate space for our input base pointers.
        p.ins = new const void*[inLen];

        // Allocate space for our input stride information.
        p.eStrideIns = new uint32_t[inLen];

        // Fill our stride information.
        for (int index = inLen; --index >= 0;) {
          p.eStrideIns[index] = mtls->fep.inStrides[index].eStride;
        }

        //ALOGE("launch 3");
        outer_foreach_t fn = (outer_foreach_t) mtls->kernel;
        uint32_t offset_invariant = mtls->fep.dimY * mtls->fep.dimZ * p.ar[0];

        for (p.ar[0] = mtls->arrayStart; p.ar[0] < mtls->arrayEnd; p.ar[0]++) {
            uint32_t offset_part = offset_invariant * p.ar[0];

            for (p.z = mtls->zStart; p.z < mtls->zEnd; p.z++) {
                for (p.y = mtls->yStart; p.y < mtls->yEnd; p.y++) {
                    uint32_t offset = offset_part + mtls->fep.dimY * p.z + p.y;

                    p.out = mtls->fep.ptrOut + (mtls->fep.yStrideOut * offset) +
                            (mtls->fep.eStrideOut * mtls->xStart);

                    for (int index = inLen; --index >= 0;) {
                        StridePair &strides = mtls->fep.inStrides[index];

                        p.ins[index] = mtls->fep.ptrIns[index] +
                                       (strides.yStride * offset) +
                                       (strides.eStride * mtls->xStart);
                    }

                    /*
                     * The fourth argument is zero here because multi-input
                     * kernels get their stride information from a member of p
                     * that points to an array.
                     */
                    fn(&p, mtls->xStart, mtls->xEnd, 0, mtls->fep.eStrideOut);
                }
            }
        }

        // Free our arrays.
        delete[] p.ins;
        delete[] p.eStrideIns;
    }
}

RsdCpuScriptImpl * RsdCpuReferenceImpl::setTLS(RsdCpuScriptImpl *sc) {
    //ALOGE("setTls %p", sc);
    ScriptTLSStruct * tls = (ScriptTLSStruct *)pthread_getspecific(gThreadTLSKey);
    rsAssert(tls);
    RsdCpuScriptImpl *old = tls->mImpl;
    tls->mImpl = sc;
    tls->mContext = mRSC;
    if (sc) {
        tls->mScript = sc->getScript();
    } else {
        tls->mScript = NULL;
    }
    return old;
}

const RsdCpuReference::CpuSymbol * RsdCpuReferenceImpl::symLookup(const char *name) {
    return mSymLookupFn(mRSC, name);
}


RsdCpuReference::CpuScript * RsdCpuReferenceImpl::createScript(const ScriptC *s,
                                    char const *resName, char const *cacheDir,
                                    uint8_t const *bitcode, size_t bitcodeSize,
                                    uint32_t flags) {

    RsdCpuScriptImpl *i = new RsdCpuScriptImpl(this, s);
    if (!i->init(resName, cacheDir, bitcode, bitcodeSize, flags
#ifndef RS_COMPATIBILITY_LIB
        , getBccPluginName()
#endif
        )) {
        delete i;
        return NULL;
    }
    return i;
}

extern RsdCpuScriptImpl * rsdIntrinsic_3DLUT(RsdCpuReferenceImpl *ctx,
                                             const Script *s, const Element *e);
extern RsdCpuScriptImpl * rsdIntrinsic_Convolve3x3(RsdCpuReferenceImpl *ctx,
                                                   const Script *s, const Element *e);
extern RsdCpuScriptImpl * rsdIntrinsic_ColorMatrix(RsdCpuReferenceImpl *ctx,
                                                   const Script *s, const Element *e);
extern RsdCpuScriptImpl * rsdIntrinsic_LUT(RsdCpuReferenceImpl *ctx,
                                           const Script *s, const Element *e);
extern RsdCpuScriptImpl * rsdIntrinsic_Convolve5x5(RsdCpuReferenceImpl *ctx,
                                                   const Script *s, const Element *e);
extern RsdCpuScriptImpl * rsdIntrinsic_Blur(RsdCpuReferenceImpl *ctx,
                                            const Script *s, const Element *e);
extern RsdCpuScriptImpl * rsdIntrinsic_YuvToRGB(RsdCpuReferenceImpl *ctx,
                                                const Script *s, const Element *e);
extern RsdCpuScriptImpl * rsdIntrinsic_Blend(RsdCpuReferenceImpl *ctx,
                                             const Script *s, const Element *e);
extern RsdCpuScriptImpl * rsdIntrinsic_Histogram(RsdCpuReferenceImpl *ctx,
                                                 const Script *s, const Element *e);
extern RsdCpuScriptImpl * rsdIntrinsic_Resize(RsdCpuReferenceImpl *ctx,
                                              const Script *s, const Element *e);

RsdCpuReference::CpuScript * RsdCpuReferenceImpl::createIntrinsic(const Script *s,
                                    RsScriptIntrinsicID iid, Element *e) {

    RsdCpuScriptImpl *i = NULL;
    switch (iid) {
    case RS_SCRIPT_INTRINSIC_ID_3DLUT:
        i = rsdIntrinsic_3DLUT(this, s, e);
        break;
    case RS_SCRIPT_INTRINSIC_ID_CONVOLVE_3x3:
        i = rsdIntrinsic_Convolve3x3(this, s, e);
        break;
    case RS_SCRIPT_INTRINSIC_ID_COLOR_MATRIX:
        i = rsdIntrinsic_ColorMatrix(this, s, e);
        break;
    case RS_SCRIPT_INTRINSIC_ID_LUT:
        i = rsdIntrinsic_LUT(this, s, e);
        break;
    case RS_SCRIPT_INTRINSIC_ID_CONVOLVE_5x5:
        i = rsdIntrinsic_Convolve5x5(this, s, e);
        break;
    case RS_SCRIPT_INTRINSIC_ID_BLUR:
        i = rsdIntrinsic_Blur(this, s, e);
        break;
    case RS_SCRIPT_INTRINSIC_ID_YUV_TO_RGB:
        i = rsdIntrinsic_YuvToRGB(this, s, e);
        break;
    case RS_SCRIPT_INTRINSIC_ID_BLEND:
        i = rsdIntrinsic_Blend(this, s, e);
        break;
    case RS_SCRIPT_INTRINSIC_ID_HISTOGRAM:
        i = rsdIntrinsic_Histogram(this, s, e);
        break;
    case RS_SCRIPT_INTRINSIC_ID_RESIZE:
        i = rsdIntrinsic_Resize(this, s, e);
        break;

    default:
        rsAssert(0);
    }

    return i;
}

RsdCpuReference::CpuScriptGroup * RsdCpuReferenceImpl::createScriptGroup(const ScriptGroup *sg) {
    CpuScriptGroupImpl *sgi = new CpuScriptGroupImpl(this, sg);
    if (!sgi->init()) {
        delete sgi;
        return NULL;
    }
    return sgi;
}
