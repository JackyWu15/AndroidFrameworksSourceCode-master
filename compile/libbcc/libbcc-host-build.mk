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

ifneq ($(HOST_OS),windows)
LOCAL_CLANG := true
endif

LOCAL_CFLAGS := \
  -Wall \
  -Wno-unused-parameter \
  -Werror \
  -D__HOST__ \
  $(RS_VERSION_DEFINE) \
  $(LOCAL_CFLAGS)

ifneq ($(BOARD_OVERRIDE_RS_CPU_VARIANT_32),)
LOCAL_CFLAGS += -DFORCE_CPU_VARIANT_32=$(BOARD_OVERRIDE_RS_CPU_VARIANT_32)
endif

ifneq ($(BOARD_OVERRIDE_RS_CPU_VARIANT_64),)
LOCAL_CFLAGS += -DFORCE_CPU_VARIANT_64=$(BOARD_OVERRIDE_RS_CPU_VARIANT_64)
endif

ifeq ($(TARGET_BUILD_VARIANT),eng)
libbcc_CFLAGS += -DANDROID_ENGINEERING_BUILD
else
LOCAL_CFLAGS += -D__DISABLE_ASSERTS
endif

LOCAL_C_INCLUDES := \
  $(LIBBCC_ROOT_PATH)/include \
  $(LLVM_ROOT_PATH)/include \
  $(LLVM_ROOT_PATH)/host/include \
  $(LOCAL_C_INCLUDES)

LOCAL_IS_HOST_MODULE := true

LOCAL_32_BIT_ONLY := true
