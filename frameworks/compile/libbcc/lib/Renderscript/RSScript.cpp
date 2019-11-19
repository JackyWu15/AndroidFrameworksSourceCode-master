/*
 * Copyright 2012, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include "bcc/Renderscript/RSScript.h"

#include "bcc/Assert.h"
#include "bcc/Renderscript/RSInfo.h"
#include "bcc/Source.h"
#include "bcc/Support/Log.h"

using namespace bcc;

bool RSScript::LinkRuntime(RSScript &pScript, const char *core_lib) {
  bccAssert(core_lib != NULL);

  // Using the same context with the source in pScript.
  BCCContext &context = pScript.getSource().getContext();

  Source *libclcore_source = Source::CreateFromFile(context, core_lib);
  if (libclcore_source == NULL) {
    ALOGE("Failed to load Renderscript library '%s' to link!", core_lib);
    return false;
  }

  if (NULL != pScript.mLinkRuntimeCallback) {
    pScript.mLinkRuntimeCallback(&pScript,
        &pScript.getSource().getModule(), &libclcore_source->getModule());
  }

  if (!pScript.getSource().merge(*libclcore_source,
                                 /* pPreserveSource */false)) {
    ALOGE("Failed to link Renderscript library '%s'!", core_lib);
    delete libclcore_source;
    return false;
  }

  return true;
}

RSScript::RSScript(Source &pSource)
  : Script(pSource), mInfo(NULL), mCompilerVersion(0),
    mOptimizationLevel(kOptLvl3), mLinkRuntimeCallback(NULL),
    mEmbedInfo(false) { }

bool RSScript::doReset() {
  mInfo = NULL;
  mCompilerVersion = 0;
  mOptimizationLevel = kOptLvl3;
  return true;
}
