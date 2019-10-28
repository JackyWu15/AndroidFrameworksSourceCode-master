/*
 * Copyright (C) 2010 The Android Open Source Project
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

#if defined(HAVE_PTHREADS)
#include <sys/resource.h>
#endif

#include <cutils/sched_policy.h>
#include <binder/IServiceManager.h>
#include <binder/IPCThreadState.h>
#include <binder/ProcessState.h>
#include <binder/IServiceManager.h>
#include "SurfaceFlinger.h"

using namespace android;

int main(int, char**) {
    // When SF is launched in its own process, limit the number of
    // binder threads to 4.//
    ProcessState::self()->setThreadPoolMaxThreadCount(4);

    // start the thread pool
    sp<ProcessState> ps(ProcessState::self());
    ps->startThreadPool();

    // instantiate surfaceflinger
    //构造SurfaceFlinger
    //以BootAnimation为例，Android系统的开机动画是主要一个BootAnimation对象来实现，BootAnimation对象在构造的时候，会在内部创建一个SurfaceComposerClient对象来负责创建一个到SurfaceFlinger服务的连接
    sp<SurfaceFlinger> flinger = new SurfaceFlinger();

#if defined(HAVE_PTHREADS)
    setpriority(PRIO_PROCESS, 0, PRIORITY_URGENT_DISPLAY);
#endif
    set_sched_policy(0, SP_FOREGROUND);

    // initialize before clients can connect
    //初始化
    flinger->init();

    // publish surface flinger
    //将SurfaceFlinger加入到ServiceManager中
    sp<IServiceManager> sm(defaultServiceManager());
    sm->addService(String16(SurfaceFlinger::getServiceName()), flinger, false);

    // run in this thread
    //运行
    flinger->run();

    return 0;
}
