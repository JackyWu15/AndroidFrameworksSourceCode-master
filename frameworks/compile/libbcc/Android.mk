#
# Copyright (C) 2010-2012 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

LOCAL_PATH := $(call my-dir)
LIBBCC_ROOT_PATH := $(LOCAL_PATH)
include $(LIBBCC_ROOT_PATH)/libbcc.mk

include frameworks/compile/slang/rs_version.mk

#=====================================================================
# Whole Static Library to Be Linked In
#=====================================================================

libbcc_WHOLE_STATIC_LIBRARIES += \
  libbccRenderscript \
  libbccExecutionEngine \
  libbccCore \
  libbccSupport

#=====================================================================
# Device Shared Library libbcc
#=====================================================================
ifneq (true,$(DISABLE_LLVM_DEVICE_BUILDS))
ifeq ($(TARGET_ARCH),mips64)
$(info TODOMips64: $(LOCAL_PATH)/Android.mk Enable libbcc build)
endif

include $(CLEAR_VARS)

LOCAL_MODULE := libbcc
LOCAL_MODULE_TAGS := optional
LOCAL_MODULE_CLASS := SHARED_LIBRARIES

LOCAL_WHOLE_STATIC_LIBRARIES := $(libbcc_WHOLE_STATIC_LIBRARIES)

LOCAL_WHOLE_STATIC_LIBRARIES += librsloader

LOCAL_SHARED_LIBRARIES := libbcinfo libLLVM libdl libutils libcutils liblog libc++

# Modules that need get installed if and only if the target libbcc.so is
# installed.
LOCAL_REQUIRED_MODULES := libclcore.bc libclcore_debug.bc libcompiler_rt

LOCAL_REQUIRED_MODULES_x86 += libclcore_x86.bc
LOCAL_REQUIRED_MODULES_x86_64 += libclcore_x86.bc

ifeq ($(ARCH_ARM_HAVE_NEON),true)
  LOCAL_REQUIRED_MODULES_arm += libclcore_neon.bc
endif

include $(LIBBCC_DEVICE_BUILD_MK)
include $(LLVM_DEVICE_BUILD_MK)
include $(BUILD_SHARED_LIBRARY)
endif

#=====================================================================
# Host Shared Library libbcc
#=====================================================================

# Don't build for unbundled branches
ifeq (,$(TARGET_BUILD_APPS))

include $(CLEAR_VARS)

LOCAL_MODULE := libbcc
LOCAL_MODULE_TAGS := optional
LOCAL_MODULE_CLASS := SHARED_LIBRARIES
LOCAL_IS_HOST_MODULE := true

ifneq ($(HOST_OS),windows)
LOCAL_CLANG := true
endif

LOCAL_WHOLE_STATIC_LIBRARIES += $(libbcc_WHOLE_STATIC_LIBRARIES)

LOCAL_WHOLE_STATIC_LIBRARIES += librsloader

LOCAL_STATIC_LIBRARIES += \
  libutils \
  libcutils \
  liblog

LOCAL_SHARED_LIBRARIES := libbcinfo libLLVM

ifndef USE_MINGW
LOCAL_LDLIBS := -ldl -lpthread
endif

include $(LIBBCC_HOST_BUILD_MK)
include $(LLVM_HOST_BUILD_MK)
include $(BUILD_HOST_SHARED_LIBRARY)

endif # Don't build in unbundled branches

#=====================================================================
# Include Subdirectories
#=====================================================================
include $(call all-makefiles-under,$(LOCAL_PATH))
