/*
 * Copyright (C) 2011-2012 The Android Open Source Project
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

#include "rsContext.h"
#include "rsScriptC.h"
#include "rsMatrix4x4.h"
#include "rsMatrix3x3.h"
#include "rsMatrix2x2.h"
#include "rsRuntime.h"

#include "rsCpuCore.h"
#include "rsCpuScript.h"

#include <time.h>

using namespace android;
using namespace android::renderscript;

typedef float float2 __attribute__((ext_vector_type(2)));
typedef float float3 __attribute__((ext_vector_type(3)));
typedef float float4 __attribute__((ext_vector_type(4)));
typedef double double2 __attribute__((ext_vector_type(2)));
typedef double double3 __attribute__((ext_vector_type(3)));
typedef double double4 __attribute__((ext_vector_type(4)));
typedef char char2 __attribute__((ext_vector_type(2)));
typedef char char3 __attribute__((ext_vector_type(3)));
typedef char char4 __attribute__((ext_vector_type(4)));
typedef unsigned char uchar2 __attribute__((ext_vector_type(2)));
typedef unsigned char uchar3 __attribute__((ext_vector_type(3)));
typedef unsigned char uchar4 __attribute__((ext_vector_type(4)));
typedef short short2 __attribute__((ext_vector_type(2)));
typedef short short3 __attribute__((ext_vector_type(3)));
typedef short short4 __attribute__((ext_vector_type(4)));
typedef unsigned short ushort2 __attribute__((ext_vector_type(2)));
typedef unsigned short ushort3 __attribute__((ext_vector_type(3)));
typedef unsigned short ushort4 __attribute__((ext_vector_type(4)));
typedef int32_t int2 __attribute__((ext_vector_type(2)));
typedef int32_t int3 __attribute__((ext_vector_type(3)));
typedef int32_t int4 __attribute__((ext_vector_type(4)));
typedef uint32_t uint2 __attribute__((ext_vector_type(2)));
typedef uint32_t uint3 __attribute__((ext_vector_type(3)));
typedef uint32_t uint4 __attribute__((ext_vector_type(4)));
typedef long long long2 __attribute__((ext_vector_type(2)));
typedef long long long3 __attribute__((ext_vector_type(3)));
typedef long long long4 __attribute__((ext_vector_type(4)));
typedef unsigned long long ulong2 __attribute__((ext_vector_type(2)));
typedef unsigned long long ulong3 __attribute__((ext_vector_type(3)));
typedef unsigned long long ulong4 __attribute__((ext_vector_type(4)));


//////////////////////////////////////////////////////////////////////////////
// Message routines
//////////////////////////////////////////////////////////////////////////////


static void SC_debugF(const char *s, float f) {
    ALOGD("float %s %f, 0x%08x", s, f, *((int *) (&f)));
}
static void SC_debugFv2(const char *s, float f1, float f2) {
    ALOGD("float x2 %s {%f, %f}", s, f1, f2);
}
static void SC_debugFv3(const char *s, float f1, float f2, float f3) {
    ALOGD("float x3 %s {%f, %f, %f}", s, f1, f2, f3);
}
static void SC_debugFv4(const char *s, float f1, float f2, float f3, float f4) {
    ALOGD("float x4 %s {%f, %f, %f, %f}", s, f1, f2, f3, f4);
}
static void SC_debugF2(const char *s, const float2 *f) {
    ALOGD("float2 %s {%f, %f}", s, f->x, f->y);
}
static void SC_debugF3(const char *s, const float3 *f) {
    ALOGD("float3 %s {%f, %f, %f}", s, f->x, f->y, f->z);
}
static void SC_debugF4(const char *s, const float4 *f) {
    ALOGD("float4 %s {%f, %f, %f, %f}", s, f->x, f->y, f->z, f->w);
}
static void SC_debugD(const char *s, double d) {
    ALOGD("double %s %f, 0x%08llx", s, d, *((long long *) (&d)));
}
static void SC_debugD2(const char *s, const double2 *f) {
    ALOGD("double2 %s {%f, %f}", s, f->x, f->y);
}
static void SC_debugD3(const char *s, const double3 *f) {
    ALOGD("double3 %s {%f, %f, %f}", s, f->x, f->y, f->z);
}
static void SC_debugD4(const char *s, const double4 *f) {
    ALOGD("double4 %s {%f, %f, %f, %f}", s, f->x, f->y, f->z, f->w);
}

static void SC_debugFM4v4(const char *s, const float *f) {
    ALOGD("matrix4x4 %s {%f, %f, %f, %f", s, f[0], f[4], f[8], f[12]);
    ALOGD("          %s  %f, %f, %f, %f", s, f[1], f[5], f[9], f[13]);
    ALOGD("          %s  %f, %f, %f, %f", s, f[2], f[6], f[10], f[14]);
    ALOGD("          %s  %f, %f, %f, %f}", s, f[3], f[7], f[11], f[15]);
}
static void SC_debugFM3v3(const char *s, const float *f) {
    ALOGD("matrix3x3 %s {%f, %f, %f", s, f[0], f[3], f[6]);
    ALOGD("          %s  %f, %f, %f", s, f[1], f[4], f[7]);
    ALOGD("          %s  %f, %f, %f}",s, f[2], f[5], f[8]);
}
static void SC_debugFM2v2(const char *s, const float *f) {
    ALOGD("matrix2x2 %s {%f, %f", s, f[0], f[2]);
    ALOGD("          %s  %f, %f}",s, f[1], f[3]);
}
static void SC_debugI8(const char *s, char c) {
    ALOGD("char %s %hhd  0x%hhx", s, c, (unsigned char)c);
}
static void SC_debugC2(const char *s, const char2 *c) {
    ALOGD("char2 %s {%hhd, %hhd}  0x%hhx 0x%hhx", s, c->x, c->y, (unsigned char)c->x, (unsigned char)c->y);
}
static void SC_debugC3(const char *s, const char3 *c) {
    ALOGD("char3 %s {%hhd, %hhd, %hhd}  0x%hhx 0x%hhx 0x%hhx", s, c->x, c->y, c->z, (unsigned char)c->x, (unsigned char)c->y, (unsigned char)c->z);
}
static void SC_debugC4(const char *s, const char4 *c) {
    ALOGD("char4 %s {%hhd, %hhd, %hhd, %hhd}  0x%hhx 0x%hhx 0x%hhx 0x%hhx", s, c->x, c->y, c->z, c->w, (unsigned char)c->x, (unsigned char)c->y, (unsigned char)c->z, (unsigned char)c->w);
}
static void SC_debugU8(const char *s, unsigned char c) {
    ALOGD("uchar %s %hhu  0x%hhx", s, c, c);
}
static void SC_debugUC2(const char *s, const uchar2 *c) {
    ALOGD("uchar2 %s {%hhu, %hhu}  0x%hhx 0x%hhx", s, c->x, c->y, c->x, c->y);
}
static void SC_debugUC3(const char *s, const uchar3 *c) {
    ALOGD("uchar3 %s {%hhu, %hhu, %hhu}  0x%hhx 0x%hhx 0x%hhx", s, c->x, c->y, c->z, c->x, c->y, c->z);
}
static void SC_debugUC4(const char *s, const uchar4 *c) {
    ALOGD("uchar4 %s {%hhu, %hhu, %hhu, %hhu}  0x%hhx 0x%hhx 0x%hhx 0x%hhx", s, c->x, c->y, c->z, c->w, c->x, c->y, c->z, c->w);
}
static void SC_debugI16(const char *s, short c) {
    ALOGD("short %s %hd  0x%hx", s, c, c);
}
static void SC_debugS2(const char *s, const short2 *c) {
    ALOGD("short2 %s {%hd, %hd}  0x%hx 0x%hx", s, c->x, c->y, c->x, c->y);
}
static void SC_debugS3(const char *s, const short3 *c) {
    ALOGD("short3 %s {%hd, %hd, %hd}  0x%hx 0x%hx 0x%hx", s, c->x, c->y, c->z, c->x, c->y, c->z);
}
static void SC_debugS4(const char *s, const short4 *c) {
    ALOGD("short4 %s {%hd, %hd, %hd, %hd}  0x%hx 0x%hx 0x%hx 0x%hx", s, c->x, c->y, c->z, c->w, c->x, c->y, c->z, c->w);
}
static void SC_debugU16(const char *s, unsigned short c) {
    ALOGD("ushort %s %hu  0x%hx", s, c, c);
}
static void SC_debugUS2(const char *s, const ushort2 *c) {
    ALOGD("ushort2 %s {%hu, %hu}  0x%hx 0x%hx", s, c->x, c->y, c->x, c->y);
}
static void SC_debugUS3(const char *s, const ushort3 *c) {
    ALOGD("ushort3 %s {%hu, %hu, %hu}  0x%hx 0x%hx 0x%hx", s, c->x, c->y, c->z, c->x, c->y, c->z);
}
static void SC_debugUS4(const char *s, const ushort4 *c) {
    ALOGD("ushort4 %s {%hu, %hu, %hu, %hu}  0x%hx 0x%hx 0x%hx 0x%hx", s, c->x, c->y, c->z, c->w, c->x, c->y, c->z, c->w);
}
static void SC_debugI32(const char *s, int32_t i) {
    ALOGD("int %s %d  0x%x", s, i, i);
}
static void SC_debugI2(const char *s, const int2 *i) {
    ALOGD("int2 %s {%d, %d}  0x%x 0x%x", s, i->x, i->y, i->x, i->y);
}
static void SC_debugI3(const char *s, const int3 *i) {
    ALOGD("int3 %s {%d, %d, %d}  0x%x 0x%x 0x%x", s, i->x, i->y, i->z, i->x, i->y, i->z);
}
static void SC_debugI4(const char *s, const int4 *i) {
    ALOGD("int4 %s {%d, %d, %d, %d}  0x%x 0x%x 0x%x 0x%x", s, i->x, i->y, i->z, i->w, i->x, i->y, i->z, i->w);
}
static void SC_debugU32(const char *s, uint32_t i) {
    ALOGD("uint %s %u  0x%x", s, i, i);
}
static void SC_debugUI2(const char *s, const uint2 *i) {
    ALOGD("uint2 %s {%u, %u}  0x%x 0x%x", s, i->x, i->y, i->x, i->y);
}
static void SC_debugUI3(const char *s, const uint3 *i) {
    ALOGD("uint3 %s {%u, %u, %u}  0x%x 0x%x 0x%x", s, i->x, i->y, i->z, i->x, i->y, i->z);
}
static void SC_debugUI4(const char *s, const uint4 *i) {
    ALOGD("uint4 %s {%u, %u, %u, %u}  0x%x 0x%x 0x%x 0x%x", s, i->x, i->y, i->z, i->w, i->x, i->y, i->z, i->w);
}
static void SC_debugLL64(const char *s, long long ll) {
    ALOGD("long %s %lld  0x%llx", s, ll, ll);
}
static void SC_debugL2(const char *s, const long2 *ll) {
    ALOGD("long2 %s {%lld, %lld}  0x%llx 0x%llx", s, ll->x, ll->y, ll->x, ll->y);
}
static void SC_debugL3(const char *s, const long3 *ll) {
    ALOGD("long3 %s {%lld, %lld, %lld}  0x%llx 0x%llx 0x%llx", s, ll->x, ll->y, ll->z, ll->x, ll->y, ll->z);
}
static void SC_debugL4(const char *s, const long4 *ll) {
    ALOGD("long4 %s {%lld, %lld, %lld, %lld}  0x%llx 0x%llx 0x%llx 0x%llx", s, ll->x, ll->y, ll->z, ll->w, ll->x, ll->y, ll->z, ll->w);
}
static void SC_debugULL64(const char *s, unsigned long long ll) {
    ALOGD("ulong %s %llu  0x%llx", s, ll, ll);
}
static void SC_debugUL2(const char *s, const ulong2 *ll) {
    ALOGD("ulong2 %s {%llu, %llu}  0x%llx 0x%llx", s, ll->x, ll->y, ll->x, ll->y);
}
static void SC_debugUL3(const char *s, const ulong3 *ll) {
    ALOGD("ulong3 %s {%llu, %llu, %llu}  0x%llx 0x%llx 0x%llx", s, ll->x, ll->y, ll->z, ll->x, ll->y, ll->z);
}
static void SC_debugUL4(const char *s, const ulong4 *ll) {
    ALOGD("ulong4 %s {%llu, %llu, %llu, %llu}  0x%llx 0x%llx 0x%llx 0x%llx", s, ll->x, ll->y, ll->z, ll->w, ll->x, ll->y, ll->z, ll->w);
}
static void SC_debugP(const char *s, const void *p) {
    ALOGD("void * %s %p", s, p);
}


//////////////////////////////////////////////////////////////////////////////
// Stub implementation
//////////////////////////////////////////////////////////////////////////////

// llvm name mangling ref
//  <builtin-type> ::= v  # void
//                 ::= b  # bool
//                 ::= c  # char
//                 ::= a  # signed char
//                 ::= h  # unsigned char
//                 ::= s  # short
//                 ::= t  # unsigned short
//                 ::= i  # int
//                 ::= j  # unsigned int
//                 ::= l  # long
//                 ::= m  # unsigned long
//                 ::= x  # long long, __int64
//                 ::= y  # unsigned long long, __int64
//                 ::= f  # float
//                 ::= d  # double

static RsdCpuReference::CpuSymbol gSyms[] = {
    { "memset", (void *)&memset, true },
    { "memcpy", (void *)&memcpy, true },

    // Debug
    { "_Z7rsDebugPKcf", (void *)&SC_debugF, true },
    { "_Z7rsDebugPKcff", (void *)&SC_debugFv2, true },
    { "_Z7rsDebugPKcfff", (void *)&SC_debugFv3, true },
    { "_Z7rsDebugPKcffff", (void *)&SC_debugFv4, true },
    { "_Z7rsDebugPKcPKDv2_f", (void *)&SC_debugF2, true },
    { "_Z7rsDebugPKcPKDv3_f", (void *)&SC_debugF3, true },
    { "_Z7rsDebugPKcPKDv4_f", (void *)&SC_debugF4, true },
    { "_Z7rsDebugPKcd", (void *)&SC_debugD, true },
    { "_Z7rsDebugPKcPKDv2_d", (void *)&SC_debugD2, true },
    { "_Z7rsDebugPKcPKDv3_d", (void *)&SC_debugD3, true },
    { "_Z7rsDebugPKcPKDv4_d", (void *)&SC_debugD4, true },
    { "_Z7rsDebugPKcPK12rs_matrix4x4", (void *)&SC_debugFM4v4, true },
    { "_Z7rsDebugPKcPK12rs_matrix3x3", (void *)&SC_debugFM3v3, true },
    { "_Z7rsDebugPKcPK12rs_matrix2x2", (void *)&SC_debugFM2v2, true },
    { "_Z7rsDebugPKcc", (void *)&SC_debugI8, true },
    { "_Z7rsDebugPKcPKDv2_c", (void *)&SC_debugC2, true },
    { "_Z7rsDebugPKcPKDv3_c", (void *)&SC_debugC3, true },
    { "_Z7rsDebugPKcPKDv4_c", (void *)&SC_debugC4, true },
    { "_Z7rsDebugPKch", (void *)&SC_debugU8, true },
    { "_Z7rsDebugPKcPKDv2_h", (void *)&SC_debugUC2, true },
    { "_Z7rsDebugPKcPKDv3_h", (void *)&SC_debugUC3, true },
    { "_Z7rsDebugPKcPKDv4_h", (void *)&SC_debugUC4, true },
    { "_Z7rsDebugPKcs", (void *)&SC_debugI16, true },
    { "_Z7rsDebugPKcPKDv2_s", (void *)&SC_debugS2, true },
    { "_Z7rsDebugPKcPKDv3_s", (void *)&SC_debugS3, true },
    { "_Z7rsDebugPKcPKDv4_s", (void *)&SC_debugS4, true },
    { "_Z7rsDebugPKct", (void *)&SC_debugU16, true },
    { "_Z7rsDebugPKcPKDv2_t", (void *)&SC_debugUS2, true },
    { "_Z7rsDebugPKcPKDv3_t", (void *)&SC_debugUS3, true },
    { "_Z7rsDebugPKcPKDv4_t", (void *)&SC_debugUS4, true },
    { "_Z7rsDebugPKci", (void *)&SC_debugI32, true },
    { "_Z7rsDebugPKcPKDv2_i", (void *)&SC_debugI2, true },
    { "_Z7rsDebugPKcPKDv3_i", (void *)&SC_debugI3, true },
    { "_Z7rsDebugPKcPKDv4_i", (void *)&SC_debugI4, true },
    { "_Z7rsDebugPKcj", (void *)&SC_debugU32, true },
    { "_Z7rsDebugPKcPKDv2_j", (void *)&SC_debugUI2, true },
    { "_Z7rsDebugPKcPKDv3_j", (void *)&SC_debugUI3, true },
    { "_Z7rsDebugPKcPKDv4_j", (void *)&SC_debugUI4, true },
    // Both "long" and "unsigned long" need to be redirected to their
    // 64-bit counterparts, since we have hacked Slang to use 64-bit
    // for "long" on Arm (to be similar to Java).
    { "_Z7rsDebugPKcl", (void *)&SC_debugLL64, true },
    { "_Z7rsDebugPKcPKDv2_l", (void *)&SC_debugL2, true },
    { "_Z7rsDebugPKcPKDv3_l", (void *)&SC_debugL3, true },
    { "_Z7rsDebugPKcPKDv4_l", (void *)&SC_debugL4, true },
    { "_Z7rsDebugPKcm", (void *)&SC_debugULL64, true },
    { "_Z7rsDebugPKcPKDv2_m", (void *)&SC_debugUL2, true },
    { "_Z7rsDebugPKcPKDv3_m", (void *)&SC_debugUL3, true },
    { "_Z7rsDebugPKcPKDv4_m", (void *)&SC_debugUL4, true },
    { "_Z7rsDebugPKcx", (void *)&SC_debugLL64, true },
    { "_Z7rsDebugPKcPKDv2_x", (void *)&SC_debugL2, true },
    { "_Z7rsDebugPKcPKDv3_x", (void *)&SC_debugL3, true },
    { "_Z7rsDebugPKcPKDv4_x", (void *)&SC_debugL4, true },
    { "_Z7rsDebugPKcy", (void *)&SC_debugULL64, true },
    { "_Z7rsDebugPKcPKDv2_y", (void *)&SC_debugUL2, true },
    { "_Z7rsDebugPKcPKDv3_y", (void *)&SC_debugUL3, true },
    { "_Z7rsDebugPKcPKDv4_y", (void *)&SC_debugUL4, true },
    { "_Z7rsDebugPKcDv2_y", (void *)&SC_debugUL2, true },
    { "_Z7rsDebugPKcDv3_y", (void *)&SC_debugUL3, true },
    { "_Z7rsDebugPKcDv4_y", (void *)&SC_debugUL4, true },

    { "_Z7rsDebugPKcPKv", (void *)&SC_debugP, true },

    { NULL, NULL, false }
};


void * RsdCpuScriptImpl::lookupRuntimeStub(void* pContext, char const* name) {
    RsdCpuScriptImpl *s = (RsdCpuScriptImpl *)pContext;
    const RsdCpuReference::CpuSymbol *syms = gSyms;
    const RsdCpuReference::CpuSymbol *sym = NULL;

    sym = s->mCtx->symLookup(name);
    if (!sym) {
        sym = s->lookupSymbolMath(name);
    }
    if (!sym) {
        while (syms->fnPtr) {
            if (!strcmp(syms->name, name)) {
                sym = syms;
            }
            syms++;
        }
    }

    if (sym) {
        s->mIsThreadable &= sym->threadable;
        return sym->fnPtr;
    }
    ALOGE("ScriptC sym lookup failed for %s", name);
    return NULL;
}


