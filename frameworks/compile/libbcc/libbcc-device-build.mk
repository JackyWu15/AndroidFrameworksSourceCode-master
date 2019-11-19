#
# Copyright (C) 2012 The Android Open Source Project
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

LOCAL_CLANG := true

LOCAL_CFLAGS := \
  -Wall \
  -Wno-unused-parameter \
  -Werror \
  -DTARGET_BUILD \
  $(RS_VERSION_DEFINE) \
  $(LOCAL_CFLAGS)


ifneq ($(BOARD_OVERRIDE_RS_CPU_VARIANT_32),)
LOCAL_CFLAGS += -DFORCE_CPU_VARIANT_32=$(BOARD_OVERRIDE_RS_CPU_VARIANT_32)
endif

ifneq ($(BOARD_OVERRIDE_RS_CPU_VARIANT_64),)
LOCAL_CFLAGS += -DFORCE_CPU_VARIANT_64=$(BOARD_OVERRIDE_RS_CPU_VARIANT_64)
endif

ifeq ($(TARGET_BUILD_VARIANT),eng)
LOCAL_CFLAGS += -DANDROID_ENGINEERING_BUILD
else
LOCAL_CFLAGS += -D__DISABLE_ASSERTS
endif

#=====================================================================
# Architecture Selection
#=====================================================================
# Note: We should only use -DFORCE_ARCH_CODEGEN on target build.
# For the host build, we will include as many architecture as possible,
# so that we can test the execution engine easily.

LOCAL_MODULE_TARGET_ARCH := $(LLVM_SUPPORTED_ARCH)

ifeq ($(TARGET_ARCH),arm64)
$(info TODOArm64: $(LOCAL_PATH)/Android.mk Add Arm64 define to LOCAL_CFLAGS)
endif

ifeq ($(TARGET_ARCH),mips64)
$(info TODOMips64: $(LOCAL_PATH)/Android.mk Add Mips64 define to LOCAL_CFLAGS)
endif

include frameworks/compile/libbcc/libbcc-targets.mk

LOCAL_C_INCLUDES := \
  bionic \
  external/libcxx/include \
  $(LIBBCC_ROOT_PATH)/include \
  $(LLVM_ROOT_PATH)/include \
  $(LLVM_ROOT_PATH)/device/include \
  $(LOCAL_C_INCLUDES)
