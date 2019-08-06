#
# Copyright (C) 2014 The Android Open Source Project
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

# This package provides the 'glue' layer between Chromium and WebView.

LOCAL_PATH := $(call my-dir)
CHROMIUM_PATH := external/chromium_org

ifeq (,$(wildcard $(CHROMIUM_PATH)))
# Some branches don't have the chromium sources; they shouldn't depend on
# webview, but just in case this is a mistake, warn about it.
$(warning Chromium sources missing, skipping webview package build)
else
# Chromium sources exist, build the package.

# Java glue layer JAR, calls directly into the chromium AwContents Java API.
include $(CLEAR_VARS)

LOCAL_PACKAGE_NAME := webview

LOCAL_MANIFEST_FILE := AndroidManifest.xml

LOCAL_MODULE_TAGS := optional

LOCAL_STATIC_JAVA_LIBRARIES += android_webview_java_with_new_resources

LOCAL_SRC_FILES := $(call all-java-files-under, java)
LOCAL_SRC_FILES += $(extra_java_files)

LOCAL_JARJAR_RULES := $(CHROMIUM_PATH)/android_webview/build/jarjar-rules.txt

include $(CHROMIUM_PATH)/android_webview/build/resources_config.mk
LOCAL_RESOURCE_DIR := \
    $(res_overrides) \
    $(LOCAL_PATH)/res \
    $(android_webview_resources_dirs)

LOCAL_AAPT_FLAGS := $(android_webview_aapt_flags)
LOCAL_AAPT_FLAGS += --extra-packages com.android.webview.chromium
LOCAL_AAPT_FLAGS += --shared-lib

include $(LOCAL_PATH)/version.mk
LOCAL_AAPT_FLAGS += --version-code "$(version_code)" --version-name "$(version_name)"

LOCAL_JNI_SHARED_LIBRARIES += libwebviewchromium

LOCAL_MULTILIB := both

# TODO: filter webviewchromium_webkit_strings based on PRODUCT_LOCALES.
LOCAL_REQUIRED_MODULES := \
        libwebviewchromium \
        libwebviewchromium_loader \
        libwebviewchromium_plat_support

LOCAL_PROGUARD_ENABLED := full
LOCAL_PROGUARD_FLAG_FILES := proguard.flags

LOCAL_JAVACFLAGS := -Xlint:unchecked -Werror

include $(BUILD_PACKAGE)

$(LOCAL_BUILT_MODULE): $(android_webview_intermediates_pak_additional_deps)
$(LOCAL_BUILT_MODULE): PRIVATE_ASSET_DIR += $(android_webview_asset_dirs)
# This is needed to force the grd->string.xml conversion to run before we
# attempt to generate the R.java file.
$(R_file_stamp): $(call intermediates-dir-for,GYP,android_webview_resources)/android_webview_resources.stamp

ifneq ($(strip $(LOCAL_JARJAR_RULES)),)
# Add build rules to check that the jarjar'ed jar only contains whitelisted
# packages. Only enable this when we are running jarjar.
LOCAL_JAR_CHECK_WHITELIST := $(LOCAL_PATH)/jar_package_whitelist.txt

jar_check_ok := $(intermediates.COMMON)/jar_check_ok
$(jar_check_ok): PRIVATE_JAR_CHECK := $(LOCAL_PATH)/tools/jar_check.py
$(jar_check_ok): PRIVATE_JAR_CHECK_WHITELIST := $(LOCAL_JAR_CHECK_WHITELIST)
$(jar_check_ok): $(full_classes_jarjar_jar) $(LOCAL_PATH)/tools/jar_check.py $(LOCAL_JAR_CHECK_WHITELIST)
	@echo Jar check: $@
	$(hide) $(PRIVATE_JAR_CHECK) $< $(PRIVATE_JAR_CHECK_WHITELIST)
	$(hide) touch $@

$(LOCAL_BUILT_MODULE): $(jar_check_ok)
endif

endif  # CHROMIUM_PATH existence test
