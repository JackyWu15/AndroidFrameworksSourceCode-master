/*
 * Copyright (C) 2013 The Android Open Source Project
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

#include "rsUtils.h"
#include "rsCppUtils.h"

namespace android {
namespace renderscript {

const char * rsuCopyString(const char *name) {
    return rsuCopyString(name, strlen(name));
}

const char * rsuCopyString(const char *name, size_t len) {
    char *n = new char[len+1];
    memcpy(n, name, len);
    n[len] = 0;
    return n;
}


}
}
