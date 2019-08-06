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

// #define LOG_NDEBUG 0
#define LOG_TAG "SoftVPXEncoder"
#include "SoftVPXEncoder.h"

#include <utils/Log.h>

#include <media/hardware/HardwareAPI.h>
#include <media/hardware/MetadataBufferType.h>
#include <media/stagefright/foundation/ADebug.h>
#include <media/stagefright/MediaDefs.h>

namespace android {

template<class T>
static void InitOMXParams(T *params) {
    params->nSize = sizeof(T);
    // OMX IL 1.1.2
    params->nVersion.s.nVersionMajor = 1;
    params->nVersion.s.nVersionMinor = 1;
    params->nVersion.s.nRevision = 2;
    params->nVersion.s.nStep = 0;
}


static int GetCPUCoreCount() {
    int cpuCoreCount = 1;
#if defined(_SC_NPROCESSORS_ONLN)
    cpuCoreCount = sysconf(_SC_NPROCESSORS_ONLN);
#else
    // _SC_NPROC_ONLN must be defined...
    cpuCoreCount = sysconf(_SC_NPROC_ONLN);
#endif
    CHECK_GE(cpuCoreCount, 1);
    return cpuCoreCount;
}

SoftVPXEncoder::SoftVPXEncoder(const char *name,
                               const OMX_CALLBACKTYPE *callbacks,
                               OMX_PTR appData,
                               OMX_COMPONENTTYPE **component)
    : SoftVideoEncoderOMXComponent(name, callbacks, appData, component),
      mCodecContext(NULL),
      mCodecConfiguration(NULL),
      mCodecInterface(NULL),
      mWidth(176),
      mHeight(144),
      mBitrate(192000),  // in bps
      mFramerate(30 << 16), // in Q16 format
      mBitrateUpdated(false),
      mBitrateControlMode(VPX_VBR),  // variable bitrate
      mDCTPartitions(0),
      mErrorResilience(OMX_FALSE),
      mColorFormat(OMX_COLOR_FormatYUV420Planar),
      mLevel(OMX_VIDEO_VP8Level_Version0),
      mKeyFrameInterval(0),
      mMinQuantizer(0),
      mMaxQuantizer(0),
      mTemporalLayers(0),
      mTemporalPatternType(OMX_VIDEO_VPXTemporalLayerPatternNone),
      mTemporalPatternLength(0),
      mTemporalPatternIdx(0),
      mLastTimestamp(0x7FFFFFFFFFFFFFFFLL),
      mConversionBuffer(NULL),
      mInputDataIsMeta(false),
      mKeyFrameRequested(false) {
    memset(mTemporalLayerBitrateRatio, 0, sizeof(mTemporalLayerBitrateRatio));
    mTemporalLayerBitrateRatio[0] = 100;
    initPorts();
}


SoftVPXEncoder::~SoftVPXEncoder() {
    releaseEncoder();
}


void SoftVPXEncoder::initPorts() {
    OMX_PARAM_PORTDEFINITIONTYPE inputPort;
    OMX_PARAM_PORTDEFINITIONTYPE outputPort;

    InitOMXParams(&inputPort);
    InitOMXParams(&outputPort);

    inputPort.nBufferCountMin = kNumBuffers;
    inputPort.nBufferCountActual = inputPort.nBufferCountMin;
    inputPort.bEnabled = OMX_TRUE;
    inputPort.bPopulated = OMX_FALSE;
    inputPort.eDomain = OMX_PortDomainVideo;
    inputPort.bBuffersContiguous = OMX_FALSE;
    inputPort.format.video.pNativeRender = NULL;
    inputPort.format.video.nFrameWidth = mWidth;
    inputPort.format.video.nFrameHeight = mHeight;
    inputPort.format.video.nStride = inputPort.format.video.nFrameWidth;
    inputPort.format.video.nSliceHeight = inputPort.format.video.nFrameHeight;
    inputPort.format.video.nBitrate = 0;
    // frameRate is in Q16 format.
    inputPort.format.video.xFramerate = mFramerate;
    inputPort.format.video.bFlagErrorConcealment = OMX_FALSE;
    inputPort.nPortIndex = kInputPortIndex;
    inputPort.eDir = OMX_DirInput;
    inputPort.nBufferAlignment = kInputBufferAlignment;
    inputPort.format.video.cMIMEType =
        const_cast<char *>(MEDIA_MIMETYPE_VIDEO_RAW);
    inputPort.format.video.eCompressionFormat = OMX_VIDEO_CodingUnused;
    inputPort.format.video.eColorFormat = mColorFormat;
    inputPort.format.video.pNativeWindow = NULL;
    inputPort.nBufferSize =
        (inputPort.format.video.nStride *
        inputPort.format.video.nSliceHeight * 3) / 2;

    addPort(inputPort);

    outputPort.nBufferCountMin = kNumBuffers;
    outputPort.nBufferCountActual = outputPort.nBufferCountMin;
    outputPort.bEnabled = OMX_TRUE;
    outputPort.bPopulated = OMX_FALSE;
    outputPort.eDomain = OMX_PortDomainVideo;
    outputPort.bBuffersContiguous = OMX_FALSE;
    outputPort.format.video.pNativeRender = NULL;
    outputPort.format.video.nFrameWidth = mWidth;
    outputPort.format.video.nFrameHeight = mHeight;
    outputPort.format.video.nStride = outputPort.format.video.nFrameWidth;
    outputPort.format.video.nSliceHeight = outputPort.format.video.nFrameHeight;
    outputPort.format.video.nBitrate = mBitrate;
    outputPort.format.video.xFramerate = 0;
    outputPort.format.video.bFlagErrorConcealment = OMX_FALSE;
    outputPort.nPortIndex = kOutputPortIndex;
    outputPort.eDir = OMX_DirOutput;
    outputPort.nBufferAlignment = kOutputBufferAlignment;
    outputPort.format.video.cMIMEType =
        const_cast<char *>(MEDIA_MIMETYPE_VIDEO_VP8);
    outputPort.format.video.eCompressionFormat = OMX_VIDEO_CodingVP8;
    outputPort.format.video.eColorFormat = OMX_COLOR_FormatUnused;
    outputPort.format.video.pNativeWindow = NULL;
    outputPort.nBufferSize = 1024 * 1024; // arbitrary

    addPort(outputPort);
}


status_t SoftVPXEncoder::initEncoder() {
    vpx_codec_err_t codec_return;

    mCodecContext = new vpx_codec_ctx_t;
    mCodecConfiguration = new vpx_codec_enc_cfg_t;
    mCodecInterface = vpx_codec_vp8_cx();

    if (mCodecInterface == NULL) {
        return UNKNOWN_ERROR;
    }
    ALOGD("VP8: initEncoder. BRMode: %u. TSLayers: %zu. KF: %u. QP: %u - %u",
          (uint32_t)mBitrateControlMode, mTemporalLayers, mKeyFrameInterval,
          mMinQuantizer, mMaxQuantizer);
    codec_return = vpx_codec_enc_config_default(mCodecInterface,
                                                mCodecConfiguration,
                                                0);  // Codec specific flags

    if (codec_return != VPX_CODEC_OK) {
        ALOGE("Error populating default configuration for vpx encoder.");
        return UNKNOWN_ERROR;
    }

    mCodecConfiguration->g_w = mWidth;
    mCodecConfiguration->g_h = mHeight;
    mCodecConfiguration->g_threads = GetCPUCoreCount();
    mCodecConfiguration->g_error_resilient = mErrorResilience;

    switch (mLevel) {
        case OMX_VIDEO_VP8Level_Version0:
            mCodecConfiguration->g_profile = 0;
            break;

        case OMX_VIDEO_VP8Level_Version1:
            mCodecConfiguration->g_profile = 1;
            break;

        case OMX_VIDEO_VP8Level_Version2:
            mCodecConfiguration->g_profile = 2;
            break;

        case OMX_VIDEO_VP8Level_Version3:
            mCodecConfiguration->g_profile = 3;
            break;

        default:
            mCodecConfiguration->g_profile = 0;
    }

    // OMX timebase unit is microsecond
    // g_timebase is in seconds (i.e. 1/1000000 seconds)
    mCodecConfiguration->g_timebase.num = 1;
    mCodecConfiguration->g_timebase.den = 1000000;
    // rc_target_bitrate is in kbps, mBitrate in bps
    mCodecConfiguration->rc_target_bitrate = (mBitrate + 500) / 1000;
    mCodecConfiguration->rc_end_usage = mBitrateControlMode;
    // Disable frame drop - not allowed in MediaCodec now.
    mCodecConfiguration->rc_dropframe_thresh = 0;
    if (mBitrateControlMode == VPX_CBR) {
        // Disable spatial resizing.
        mCodecConfiguration->rc_resize_allowed = 0;
        // Single-pass mode.
        mCodecConfiguration->g_pass = VPX_RC_ONE_PASS;
        // Maximum amount of bits that can be subtracted from the target
        // bitrate - expressed as percentage of the target bitrate.
        mCodecConfiguration->rc_undershoot_pct = 100;
        // Maximum amount of bits that can be added to the target
        // bitrate - expressed as percentage of the target bitrate.
        mCodecConfiguration->rc_overshoot_pct = 15;
        // Initial value of the buffer level in ms.
        mCodecConfiguration->rc_buf_initial_sz = 500;
        // Amount of data that the encoder should try to maintain in ms.
        mCodecConfiguration->rc_buf_optimal_sz = 600;
        // The amount of data that may be buffered by the decoding
        // application in ms.
        mCodecConfiguration->rc_buf_sz = 1000;
        // Enable error resilience - needed for packet loss.
        mCodecConfiguration->g_error_resilient = 1;
        // Disable lagged encoding.
        mCodecConfiguration->g_lag_in_frames = 0;
        // Maximum key frame interval - for CBR boost to 3000
        mCodecConfiguration->kf_max_dist = 3000;
        // Encoder determines optimal key frame placement automatically.
        mCodecConfiguration->kf_mode = VPX_KF_AUTO;
    }

    // Frames temporal pattern - for now WebRTC like pattern is only supported.
    switch (mTemporalLayers) {
        case 0:
        {
            mTemporalPatternLength = 0;
            break;
        }
        case 1:
        {
            mCodecConfiguration->ts_number_layers = 1;
            mCodecConfiguration->ts_rate_decimator[0] = 1;
            mCodecConfiguration->ts_periodicity = 1;
            mCodecConfiguration->ts_layer_id[0] = 0;
            mTemporalPattern[0] = kTemporalUpdateLastRefAll;
            mTemporalPatternLength = 1;
            break;
        }
        case 2:
        {
            mCodecConfiguration->ts_number_layers = 2;
            mCodecConfiguration->ts_rate_decimator[0] = 2;
            mCodecConfiguration->ts_rate_decimator[1] = 1;
            mCodecConfiguration->ts_periodicity = 2;
            mCodecConfiguration->ts_layer_id[0] = 0;
            mCodecConfiguration->ts_layer_id[1] = 1;
            mTemporalPattern[0] = kTemporalUpdateLastAndGoldenRefAltRef;
            mTemporalPattern[1] = kTemporalUpdateGoldenWithoutDependencyRefAltRef;
            mTemporalPattern[2] = kTemporalUpdateLastRefAltRef;
            mTemporalPattern[3] = kTemporalUpdateGoldenRefAltRef;
            mTemporalPattern[4] = kTemporalUpdateLastRefAltRef;
            mTemporalPattern[5] = kTemporalUpdateGoldenRefAltRef;
            mTemporalPattern[6] = kTemporalUpdateLastRefAltRef;
            mTemporalPattern[7] = kTemporalUpdateNone;
            mTemporalPatternLength = 8;
            break;
        }
        case 3:
        {
            mCodecConfiguration->ts_number_layers = 3;
            mCodecConfiguration->ts_rate_decimator[0] = 4;
            mCodecConfiguration->ts_rate_decimator[1] = 2;
            mCodecConfiguration->ts_rate_decimator[2] = 1;
            mCodecConfiguration->ts_periodicity = 4;
            mCodecConfiguration->ts_layer_id[0] = 0;
            mCodecConfiguration->ts_layer_id[1] = 2;
            mCodecConfiguration->ts_layer_id[2] = 1;
            mCodecConfiguration->ts_layer_id[3] = 2;
            mTemporalPattern[0] = kTemporalUpdateLastAndGoldenRefAltRef;
            mTemporalPattern[1] = kTemporalUpdateNoneNoRefGoldenRefAltRef;
            mTemporalPattern[2] = kTemporalUpdateGoldenWithoutDependencyRefAltRef;
            mTemporalPattern[3] = kTemporalUpdateNone;
            mTemporalPattern[4] = kTemporalUpdateLastRefAltRef;
            mTemporalPattern[5] = kTemporalUpdateNone;
            mTemporalPattern[6] = kTemporalUpdateGoldenRefAltRef;
            mTemporalPattern[7] = kTemporalUpdateNone;
            mTemporalPatternLength = 8;
            break;
        }
        default:
        {
            ALOGE("Wrong number of temporal layers %zu", mTemporalLayers);
            return UNKNOWN_ERROR;
        }
    }

    // Set bitrate values for each layer
    for (size_t i = 0; i < mCodecConfiguration->ts_number_layers; i++) {
        mCodecConfiguration->ts_target_bitrate[i] =
            mCodecConfiguration->rc_target_bitrate *
            mTemporalLayerBitrateRatio[i] / 100;
    }
    if (mKeyFrameInterval > 0) {
        mCodecConfiguration->kf_max_dist = mKeyFrameInterval;
        mCodecConfiguration->kf_min_dist = mKeyFrameInterval;
        mCodecConfiguration->kf_mode = VPX_KF_AUTO;
    }
    if (mMinQuantizer > 0) {
        mCodecConfiguration->rc_min_quantizer = mMinQuantizer;
    }
    if (mMaxQuantizer > 0) {
        mCodecConfiguration->rc_max_quantizer = mMaxQuantizer;
    }

    codec_return = vpx_codec_enc_init(mCodecContext,
                                      mCodecInterface,
                                      mCodecConfiguration,
                                      0);  // flags

    if (codec_return != VPX_CODEC_OK) {
        ALOGE("Error initializing vpx encoder");
        return UNKNOWN_ERROR;
    }

    codec_return = vpx_codec_control(mCodecContext,
                                     VP8E_SET_TOKEN_PARTITIONS,
                                     mDCTPartitions);
    if (codec_return != VPX_CODEC_OK) {
        ALOGE("Error setting dct partitions for vpx encoder.");
        return UNKNOWN_ERROR;
    }

    // Extra CBR settings
    if (mBitrateControlMode == VPX_CBR) {
        codec_return = vpx_codec_control(mCodecContext,
                                         VP8E_SET_STATIC_THRESHOLD,
                                         1);
        if (codec_return == VPX_CODEC_OK) {
            uint32_t rc_max_intra_target =
                mCodecConfiguration->rc_buf_optimal_sz * (mFramerate >> 17) / 10;
            // Don't go below 3 times per frame bandwidth.
            if (rc_max_intra_target < 300) {
                rc_max_intra_target = 300;
            }
            codec_return = vpx_codec_control(mCodecContext,
                                             VP8E_SET_MAX_INTRA_BITRATE_PCT,
                                             rc_max_intra_target);
        }
        if (codec_return == VPX_CODEC_OK) {
            codec_return = vpx_codec_control(mCodecContext,
                                             VP8E_SET_CPUUSED,
                                             -8);
        }
        if (codec_return != VPX_CODEC_OK) {
            ALOGE("Error setting cbr parameters for vpx encoder.");
            return UNKNOWN_ERROR;
        }
    }

    if (mColorFormat != OMX_COLOR_FormatYUV420Planar || mInputDataIsMeta) {
        free(mConversionBuffer);
        mConversionBuffer = (uint8_t *)malloc(mWidth * mHeight * 3 / 2);
        if (mConversionBuffer == NULL) {
            ALOGE("Allocating conversion buffer failed.");
            return UNKNOWN_ERROR;
        }
    }
    return OK;
}


status_t SoftVPXEncoder::releaseEncoder() {
    if (mCodecContext != NULL) {
        vpx_codec_destroy(mCodecContext);
        delete mCodecContext;
        mCodecContext = NULL;
    }

    if (mCodecConfiguration != NULL) {
        delete mCodecConfiguration;
        mCodecConfiguration = NULL;
    }

    if (mConversionBuffer != NULL) {
        free(mConversionBuffer);
        mConversionBuffer = NULL;
    }

    // this one is not allocated by us
    mCodecInterface = NULL;

    return OK;
}


OMX_ERRORTYPE SoftVPXEncoder::internalGetParameter(OMX_INDEXTYPE index,
                                                   OMX_PTR param) {
    // can include extension index OMX_INDEXEXTTYPE
    const int32_t indexFull = index;

    switch (indexFull) {
        case OMX_IndexParamVideoPortFormat: {
            OMX_VIDEO_PARAM_PORTFORMATTYPE *formatParams =
                (OMX_VIDEO_PARAM_PORTFORMATTYPE *)param;

            if (formatParams->nPortIndex == kInputPortIndex) {
                if (formatParams->nIndex >= kNumberOfSupportedColorFormats) {
                    return OMX_ErrorNoMore;
                }

                // Color formats, in order of preference
                if (formatParams->nIndex == 0) {
                    formatParams->eColorFormat = OMX_COLOR_FormatYUV420Planar;
                } else if (formatParams->nIndex == 1) {
                    formatParams->eColorFormat =
                        OMX_COLOR_FormatYUV420SemiPlanar;
                } else {
                    formatParams->eColorFormat = OMX_COLOR_FormatAndroidOpaque;
                }

                formatParams->eCompressionFormat = OMX_VIDEO_CodingUnused;
                formatParams->xFramerate = mFramerate;
                return OMX_ErrorNone;
            } else if (formatParams->nPortIndex == kOutputPortIndex) {
                formatParams->eCompressionFormat = OMX_VIDEO_CodingVP8;
                formatParams->eColorFormat = OMX_COLOR_FormatUnused;
                formatParams->xFramerate = 0;
                return OMX_ErrorNone;
            } else {
                return OMX_ErrorBadPortIndex;
            }
        }

        case OMX_IndexParamVideoBitrate: {
            OMX_VIDEO_PARAM_BITRATETYPE *bitrate =
                (OMX_VIDEO_PARAM_BITRATETYPE *)param;

                if (bitrate->nPortIndex != kOutputPortIndex) {
                    return OMX_ErrorUnsupportedIndex;
                }

                bitrate->nTargetBitrate = mBitrate;

                if (mBitrateControlMode == VPX_VBR) {
                    bitrate->eControlRate = OMX_Video_ControlRateVariable;
                } else if (mBitrateControlMode == VPX_CBR) {
                    bitrate->eControlRate = OMX_Video_ControlRateConstant;
                } else {
                    return OMX_ErrorUnsupportedSetting;
                }
                return OMX_ErrorNone;
        }

        // VP8 specific parameters that use extension headers
        case OMX_IndexParamVideoVp8: {
            OMX_VIDEO_PARAM_VP8TYPE *vp8Params =
                (OMX_VIDEO_PARAM_VP8TYPE *)param;

                if (vp8Params->nPortIndex != kOutputPortIndex) {
                    return OMX_ErrorUnsupportedIndex;
                }

                vp8Params->eProfile = OMX_VIDEO_VP8ProfileMain;
                vp8Params->eLevel = mLevel;
                vp8Params->nDCTPartitions = mDCTPartitions;
                vp8Params->bErrorResilientMode = mErrorResilience;
                return OMX_ErrorNone;
        }

        case OMX_IndexParamVideoAndroidVp8Encoder: {
            OMX_VIDEO_PARAM_ANDROID_VP8ENCODERTYPE *vp8AndroidParams =
                (OMX_VIDEO_PARAM_ANDROID_VP8ENCODERTYPE *)param;

                if (vp8AndroidParams->nPortIndex != kOutputPortIndex) {
                    return OMX_ErrorUnsupportedIndex;
                }

                vp8AndroidParams->nKeyFrameInterval = mKeyFrameInterval;
                vp8AndroidParams->eTemporalPattern = mTemporalPatternType;
                vp8AndroidParams->nTemporalLayerCount = mTemporalLayers;
                vp8AndroidParams->nMinQuantizer = mMinQuantizer;
                vp8AndroidParams->nMaxQuantizer = mMaxQuantizer;
                memcpy(vp8AndroidParams->nTemporalLayerBitrateRatio,
                       mTemporalLayerBitrateRatio, sizeof(mTemporalLayerBitrateRatio));
                return OMX_ErrorNone;
        }

        case OMX_IndexParamVideoProfileLevelQuerySupported: {
            OMX_VIDEO_PARAM_PROFILELEVELTYPE *profileAndLevel =
                (OMX_VIDEO_PARAM_PROFILELEVELTYPE *)param;

            if (profileAndLevel->nPortIndex != kOutputPortIndex) {
                return OMX_ErrorUnsupportedIndex;
            }

            switch (profileAndLevel->nProfileIndex) {
                case 0:
                    profileAndLevel->eLevel = OMX_VIDEO_VP8Level_Version0;
                    break;

                case 1:
                    profileAndLevel->eLevel = OMX_VIDEO_VP8Level_Version1;
                    break;

                case 2:
                    profileAndLevel->eLevel = OMX_VIDEO_VP8Level_Version2;
                    break;

                case 3:
                    profileAndLevel->eLevel = OMX_VIDEO_VP8Level_Version3;
                    break;

                default:
                    return OMX_ErrorNoMore;
            }

            profileAndLevel->eProfile = OMX_VIDEO_VP8ProfileMain;
            return OMX_ErrorNone;
        }

        case OMX_IndexParamVideoProfileLevelCurrent: {
            OMX_VIDEO_PARAM_PROFILELEVELTYPE *profileAndLevel =
                (OMX_VIDEO_PARAM_PROFILELEVELTYPE *)param;

            if (profileAndLevel->nPortIndex != kOutputPortIndex) {
                return OMX_ErrorUnsupportedIndex;
            }

            profileAndLevel->eLevel = mLevel;
            profileAndLevel->eProfile = OMX_VIDEO_VP8ProfileMain;
            return OMX_ErrorNone;
        }

        default:
            return SimpleSoftOMXComponent::internalGetParameter(index, param);
    }
}


OMX_ERRORTYPE SoftVPXEncoder::internalSetParameter(OMX_INDEXTYPE index,
                                                   const OMX_PTR param) {
    // can include extension index OMX_INDEXEXTTYPE
    const int32_t indexFull = index;

    switch (indexFull) {
        case OMX_IndexParamStandardComponentRole:
            return internalSetRoleParams(
                (const OMX_PARAM_COMPONENTROLETYPE *)param);

        case OMX_IndexParamVideoBitrate:
            return internalSetBitrateParams(
                (const OMX_VIDEO_PARAM_BITRATETYPE *)param);

        case OMX_IndexParamPortDefinition:
        {
            OMX_ERRORTYPE err = internalSetPortParams(
                (const OMX_PARAM_PORTDEFINITIONTYPE *)param);

            if (err != OMX_ErrorNone) {
                return err;
            }

            return SimpleSoftOMXComponent::internalSetParameter(index, param);
        }

        case OMX_IndexParamVideoPortFormat:
            return internalSetFormatParams(
                (const OMX_VIDEO_PARAM_PORTFORMATTYPE *)param);

        case OMX_IndexParamVideoVp8:
            return internalSetVp8Params(
                (const OMX_VIDEO_PARAM_VP8TYPE *)param);

        case OMX_IndexParamVideoAndroidVp8Encoder:
            return internalSetAndroidVp8Params(
                (const OMX_VIDEO_PARAM_ANDROID_VP8ENCODERTYPE *)param);

        case OMX_IndexParamVideoProfileLevelCurrent:
            return internalSetProfileLevel(
                (const OMX_VIDEO_PARAM_PROFILELEVELTYPE *)param);

        case kStoreMetaDataExtensionIndex:
        {
            // storeMetaDataInBuffers
            const StoreMetaDataInBuffersParams *storeParam =
                (const StoreMetaDataInBuffersParams *)param;

            if (storeParam->nPortIndex != kInputPortIndex) {
                return OMX_ErrorBadPortIndex;
            }

            mInputDataIsMeta = (storeParam->bStoreMetaData == OMX_TRUE);

            return OMX_ErrorNone;
        }

        default:
            return SimpleSoftOMXComponent::internalSetParameter(index, param);
    }
}

OMX_ERRORTYPE SoftVPXEncoder::setConfig(
        OMX_INDEXTYPE index, const OMX_PTR _params) {
    switch (index) {
        case OMX_IndexConfigVideoIntraVOPRefresh:
        {
            OMX_CONFIG_INTRAREFRESHVOPTYPE *params =
                (OMX_CONFIG_INTRAREFRESHVOPTYPE *)_params;

            if (params->nPortIndex != kOutputPortIndex) {
                return OMX_ErrorBadPortIndex;
            }

            mKeyFrameRequested = params->IntraRefreshVOP;
            return OMX_ErrorNone;
        }

        case OMX_IndexConfigVideoBitrate:
        {
            OMX_VIDEO_CONFIG_BITRATETYPE *params =
                (OMX_VIDEO_CONFIG_BITRATETYPE *)_params;

            if (params->nPortIndex != kOutputPortIndex) {
                return OMX_ErrorBadPortIndex;
            }

            if (mBitrate != params->nEncodeBitrate) {
                mBitrate = params->nEncodeBitrate;
                mBitrateUpdated = true;
            }
            return OMX_ErrorNone;
        }

        default:
            return SimpleSoftOMXComponent::setConfig(index, _params);
    }
}

OMX_ERRORTYPE SoftVPXEncoder::internalSetProfileLevel(
        const OMX_VIDEO_PARAM_PROFILELEVELTYPE* profileAndLevel) {
    if (profileAndLevel->nPortIndex != kOutputPortIndex) {
        return OMX_ErrorUnsupportedIndex;
    }

    if (profileAndLevel->eProfile != OMX_VIDEO_VP8ProfileMain) {
        return OMX_ErrorBadParameter;
    }

    if (profileAndLevel->eLevel == OMX_VIDEO_VP8Level_Version0 ||
        profileAndLevel->eLevel == OMX_VIDEO_VP8Level_Version1 ||
        profileAndLevel->eLevel == OMX_VIDEO_VP8Level_Version2 ||
        profileAndLevel->eLevel == OMX_VIDEO_VP8Level_Version3) {
        mLevel = (OMX_VIDEO_VP8LEVELTYPE)profileAndLevel->eLevel;
    } else {
        return OMX_ErrorBadParameter;
    }

    return OMX_ErrorNone;
}


OMX_ERRORTYPE SoftVPXEncoder::internalSetVp8Params(
        const OMX_VIDEO_PARAM_VP8TYPE* vp8Params) {
    if (vp8Params->nPortIndex != kOutputPortIndex) {
        return OMX_ErrorUnsupportedIndex;
    }

    if (vp8Params->eProfile != OMX_VIDEO_VP8ProfileMain) {
        return OMX_ErrorBadParameter;
    }

    if (vp8Params->eLevel == OMX_VIDEO_VP8Level_Version0 ||
        vp8Params->eLevel == OMX_VIDEO_VP8Level_Version1 ||
        vp8Params->eLevel == OMX_VIDEO_VP8Level_Version2 ||
        vp8Params->eLevel == OMX_VIDEO_VP8Level_Version3) {
        mLevel = vp8Params->eLevel;
    } else {
        return OMX_ErrorBadParameter;
    }

    if (vp8Params->nDCTPartitions <= kMaxDCTPartitions) {
        mDCTPartitions = vp8Params->nDCTPartitions;
    } else {
        return OMX_ErrorBadParameter;
    }

    mErrorResilience = vp8Params->bErrorResilientMode;
    return OMX_ErrorNone;
}

OMX_ERRORTYPE SoftVPXEncoder::internalSetAndroidVp8Params(
        const OMX_VIDEO_PARAM_ANDROID_VP8ENCODERTYPE* vp8AndroidParams) {
    if (vp8AndroidParams->nPortIndex != kOutputPortIndex) {
        return OMX_ErrorUnsupportedIndex;
    }
    if (vp8AndroidParams->eTemporalPattern != OMX_VIDEO_VPXTemporalLayerPatternNone &&
        vp8AndroidParams->eTemporalPattern != OMX_VIDEO_VPXTemporalLayerPatternWebRTC) {
        return OMX_ErrorBadParameter;
    }
    if (vp8AndroidParams->nTemporalLayerCount > OMX_VIDEO_ANDROID_MAXVP8TEMPORALLAYERS) {
        return OMX_ErrorBadParameter;
    }
    if (vp8AndroidParams->nMinQuantizer > vp8AndroidParams->nMaxQuantizer) {
        return OMX_ErrorBadParameter;
    }

    mTemporalPatternType = vp8AndroidParams->eTemporalPattern;
    if (vp8AndroidParams->eTemporalPattern == OMX_VIDEO_VPXTemporalLayerPatternWebRTC) {
        mTemporalLayers = vp8AndroidParams->nTemporalLayerCount;
    } else if (vp8AndroidParams->eTemporalPattern == OMX_VIDEO_VPXTemporalLayerPatternNone) {
        mTemporalLayers = 0;
    }
    // Check the bitrate distribution between layers is in increasing order
    if (mTemporalLayers > 1) {
        for (size_t i = 0; i < mTemporalLayers - 1; i++) {
            if (vp8AndroidParams->nTemporalLayerBitrateRatio[i + 1] <=
                    vp8AndroidParams->nTemporalLayerBitrateRatio[i]) {
                ALOGE("Wrong bitrate ratio - should be in increasing order.");
                return OMX_ErrorBadParameter;
            }
        }
    }
    mKeyFrameInterval = vp8AndroidParams->nKeyFrameInterval;
    mMinQuantizer = vp8AndroidParams->nMinQuantizer;
    mMaxQuantizer = vp8AndroidParams->nMaxQuantizer;
    memcpy(mTemporalLayerBitrateRatio, vp8AndroidParams->nTemporalLayerBitrateRatio,
            sizeof(mTemporalLayerBitrateRatio));
    ALOGD("VP8: internalSetAndroidVp8Params. BRMode: %u. TS: %zu. KF: %u."
          " QP: %u - %u BR0: %u. BR1: %u. BR2: %u",
          (uint32_t)mBitrateControlMode, mTemporalLayers, mKeyFrameInterval,
          mMinQuantizer, mMaxQuantizer, mTemporalLayerBitrateRatio[0],
          mTemporalLayerBitrateRatio[1], mTemporalLayerBitrateRatio[2]);
    return OMX_ErrorNone;
}

OMX_ERRORTYPE SoftVPXEncoder::internalSetFormatParams(
        const OMX_VIDEO_PARAM_PORTFORMATTYPE* format) {
    if (format->nPortIndex == kInputPortIndex) {
        if (format->eColorFormat == OMX_COLOR_FormatYUV420Planar ||
            format->eColorFormat == OMX_COLOR_FormatYUV420SemiPlanar ||
            format->eColorFormat == OMX_COLOR_FormatAndroidOpaque) {
            mColorFormat = format->eColorFormat;

            OMX_PARAM_PORTDEFINITIONTYPE *def = &editPortInfo(kInputPortIndex)->mDef;
            def->format.video.eColorFormat = mColorFormat;

            return OMX_ErrorNone;
        } else {
            ALOGE("Unsupported color format %i", format->eColorFormat);
            return OMX_ErrorUnsupportedSetting;
        }
    } else if (format->nPortIndex == kOutputPortIndex) {
        if (format->eCompressionFormat == OMX_VIDEO_CodingVP8) {
            return OMX_ErrorNone;
        } else {
            return OMX_ErrorUnsupportedSetting;
        }
    } else {
        return OMX_ErrorBadPortIndex;
    }
}


OMX_ERRORTYPE SoftVPXEncoder::internalSetRoleParams(
        const OMX_PARAM_COMPONENTROLETYPE* role) {
    const char* roleText = (const char*)role->cRole;
    const size_t roleTextMaxSize = OMX_MAX_STRINGNAME_SIZE - 1;

    if (strncmp(roleText, "video_encoder.vp8", roleTextMaxSize)) {
        ALOGE("Unsupported component role");
        return OMX_ErrorBadParameter;
    }

    return OMX_ErrorNone;
}


OMX_ERRORTYPE SoftVPXEncoder::internalSetPortParams(
        const OMX_PARAM_PORTDEFINITIONTYPE* port) {
    if (port->nPortIndex == kInputPortIndex) {
        mWidth = port->format.video.nFrameWidth;
        mHeight = port->format.video.nFrameHeight;

        // xFramerate comes in Q16 format, in frames per second unit
        mFramerate = port->format.video.xFramerate;

        if (port->format.video.eColorFormat == OMX_COLOR_FormatYUV420Planar ||
            port->format.video.eColorFormat == OMX_COLOR_FormatYUV420SemiPlanar ||
            port->format.video.eColorFormat == OMX_COLOR_FormatAndroidOpaque) {
            mColorFormat = port->format.video.eColorFormat;
        } else {
            return OMX_ErrorUnsupportedSetting;
        }

        OMX_PARAM_PORTDEFINITIONTYPE *def = &editPortInfo(kInputPortIndex)->mDef;
        def->format.video.nFrameWidth = mWidth;
        def->format.video.nFrameHeight = mHeight;
        def->format.video.xFramerate = mFramerate;
        def->format.video.eColorFormat = mColorFormat;
        def = &editPortInfo(kOutputPortIndex)->mDef;
        def->format.video.nFrameWidth = mWidth;
        def->format.video.nFrameHeight = mHeight;

        return OMX_ErrorNone;
    } else if (port->nPortIndex == kOutputPortIndex) {
        mBitrate = port->format.video.nBitrate;
        mWidth = port->format.video.nFrameWidth;
        mHeight = port->format.video.nFrameHeight;

        OMX_PARAM_PORTDEFINITIONTYPE *def = &editPortInfo(kOutputPortIndex)->mDef;
        def->format.video.nFrameWidth = mWidth;
        def->format.video.nFrameHeight = mHeight;
        def->format.video.nBitrate = mBitrate;
        return OMX_ErrorNone;
    } else {
        return OMX_ErrorBadPortIndex;
    }
}


OMX_ERRORTYPE SoftVPXEncoder::internalSetBitrateParams(
        const OMX_VIDEO_PARAM_BITRATETYPE* bitrate) {
    if (bitrate->nPortIndex != kOutputPortIndex) {
        return OMX_ErrorUnsupportedIndex;
    }

    mBitrate = bitrate->nTargetBitrate;

    if (bitrate->eControlRate == OMX_Video_ControlRateVariable) {
        mBitrateControlMode = VPX_VBR;
    } else if (bitrate->eControlRate == OMX_Video_ControlRateConstant) {
        mBitrateControlMode = VPX_CBR;
    } else {
        return OMX_ErrorUnsupportedSetting;
    }

    return OMX_ErrorNone;
}

vpx_enc_frame_flags_t SoftVPXEncoder::getEncodeFlags() {
    vpx_enc_frame_flags_t flags = 0;
    int patternIdx = mTemporalPatternIdx % mTemporalPatternLength;
    mTemporalPatternIdx++;
    switch (mTemporalPattern[patternIdx]) {
        case kTemporalUpdateLast:
            flags |= VP8_EFLAG_NO_UPD_GF;
            flags |= VP8_EFLAG_NO_UPD_ARF;
            flags |= VP8_EFLAG_NO_REF_GF;
            flags |= VP8_EFLAG_NO_REF_ARF;
            break;
        case kTemporalUpdateGoldenWithoutDependency:
            flags |= VP8_EFLAG_NO_REF_GF;
            // Deliberately no break here.
        case kTemporalUpdateGolden:
            flags |= VP8_EFLAG_NO_REF_ARF;
            flags |= VP8_EFLAG_NO_UPD_ARF;
            flags |= VP8_EFLAG_NO_UPD_LAST;
            break;
        case kTemporalUpdateAltrefWithoutDependency:
            flags |= VP8_EFLAG_NO_REF_ARF;
            flags |= VP8_EFLAG_NO_REF_GF;
            // Deliberately no break here.
        case kTemporalUpdateAltref:
            flags |= VP8_EFLAG_NO_UPD_GF;
            flags |= VP8_EFLAG_NO_UPD_LAST;
            break;
        case kTemporalUpdateNoneNoRefAltref:
            flags |= VP8_EFLAG_NO_REF_ARF;
            // Deliberately no break here.
        case kTemporalUpdateNone:
            flags |= VP8_EFLAG_NO_UPD_GF;
            flags |= VP8_EFLAG_NO_UPD_ARF;
            flags |= VP8_EFLAG_NO_UPD_LAST;
            flags |= VP8_EFLAG_NO_UPD_ENTROPY;
            break;
        case kTemporalUpdateNoneNoRefGoldenRefAltRef:
            flags |= VP8_EFLAG_NO_REF_GF;
            flags |= VP8_EFLAG_NO_UPD_GF;
            flags |= VP8_EFLAG_NO_UPD_ARF;
            flags |= VP8_EFLAG_NO_UPD_LAST;
            flags |= VP8_EFLAG_NO_UPD_ENTROPY;
            break;
        case kTemporalUpdateGoldenWithoutDependencyRefAltRef:
            flags |= VP8_EFLAG_NO_REF_GF;
            flags |= VP8_EFLAG_NO_UPD_ARF;
            flags |= VP8_EFLAG_NO_UPD_LAST;
            break;
        case kTemporalUpdateLastRefAltRef:
            flags |= VP8_EFLAG_NO_UPD_GF;
            flags |= VP8_EFLAG_NO_UPD_ARF;
            flags |= VP8_EFLAG_NO_REF_GF;
            break;
        case kTemporalUpdateGoldenRefAltRef:
            flags |= VP8_EFLAG_NO_UPD_ARF;
            flags |= VP8_EFLAG_NO_UPD_LAST;
            break;
        case kTemporalUpdateLastAndGoldenRefAltRef:
            flags |= VP8_EFLAG_NO_UPD_ARF;
            flags |= VP8_EFLAG_NO_REF_GF;
            break;
        case kTemporalUpdateLastRefAll:
            flags |= VP8_EFLAG_NO_UPD_ARF;
            flags |= VP8_EFLAG_NO_UPD_GF;
            break;
    }
    return flags;
}

void SoftVPXEncoder::onQueueFilled(OMX_U32 portIndex) {
    // Initialize encoder if not already
    if (mCodecContext == NULL) {
        if (OK != initEncoder()) {
            ALOGE("Failed to initialize encoder");
            notify(OMX_EventError,
                   OMX_ErrorUndefined,
                   0,  // Extra notification data
                   NULL);  // Notification data pointer
            return;
        }
    }

    vpx_codec_err_t codec_return;
    List<BufferInfo *> &inputBufferInfoQueue = getPortQueue(kInputPortIndex);
    List<BufferInfo *> &outputBufferInfoQueue = getPortQueue(kOutputPortIndex);

    while (!inputBufferInfoQueue.empty() && !outputBufferInfoQueue.empty()) {
        BufferInfo *inputBufferInfo = *inputBufferInfoQueue.begin();
        OMX_BUFFERHEADERTYPE *inputBufferHeader = inputBufferInfo->mHeader;

        BufferInfo *outputBufferInfo = *outputBufferInfoQueue.begin();
        OMX_BUFFERHEADERTYPE *outputBufferHeader = outputBufferInfo->mHeader;

        if (inputBufferHeader->nFlags & OMX_BUFFERFLAG_EOS) {
            inputBufferInfoQueue.erase(inputBufferInfoQueue.begin());
            inputBufferInfo->mOwnedByUs = false;
            notifyEmptyBufferDone(inputBufferHeader);

            outputBufferHeader->nFilledLen = 0;
            outputBufferHeader->nFlags = OMX_BUFFERFLAG_EOS;

            outputBufferInfoQueue.erase(outputBufferInfoQueue.begin());
            outputBufferInfo->mOwnedByUs = false;
            notifyFillBufferDone(outputBufferHeader);
            return;
        }

        const uint8_t *source =
            inputBufferHeader->pBuffer + inputBufferHeader->nOffset;

        if (mInputDataIsMeta) {
            source = extractGraphicBuffer(
                    mConversionBuffer, mWidth * mHeight * 3 / 2,
                    source, inputBufferHeader->nFilledLen,
                    mWidth, mHeight);
            if (source == NULL) {
                ALOGE("Unable to extract gralloc buffer in metadata mode");
                notify(OMX_EventError, OMX_ErrorUndefined, 0, 0);
                return;
            }
        } else if (mColorFormat == OMX_COLOR_FormatYUV420SemiPlanar) {
            ConvertYUV420SemiPlanarToYUV420Planar(
                    source, mConversionBuffer, mWidth, mHeight);

            source = mConversionBuffer;
        }
        vpx_image_t raw_frame;
        vpx_img_wrap(&raw_frame, VPX_IMG_FMT_I420, mWidth, mHeight,
                     kInputBufferAlignment, (uint8_t *)source);

        vpx_enc_frame_flags_t flags = 0;
        if (mTemporalPatternLength > 0) {
            flags = getEncodeFlags();
        }
        if (mKeyFrameRequested) {
            flags |= VPX_EFLAG_FORCE_KF;
            mKeyFrameRequested = false;
        }

        if (mBitrateUpdated) {
            mCodecConfiguration->rc_target_bitrate = mBitrate/1000;
            vpx_codec_err_t res = vpx_codec_enc_config_set(mCodecContext,
                                                           mCodecConfiguration);
            if (res != VPX_CODEC_OK) {
                ALOGE("vp8 encoder failed to update bitrate: %s",
                      vpx_codec_err_to_string(res));
                notify(OMX_EventError,
                       OMX_ErrorUndefined,
                       0, // Extra notification data
                       NULL); // Notification data pointer
            }
            mBitrateUpdated = false;
        }

        uint32_t frameDuration;
        if (inputBufferHeader->nTimeStamp > mLastTimestamp) {
            frameDuration = (uint32_t)(inputBufferHeader->nTimeStamp - mLastTimestamp);
        } else {
            frameDuration = (uint32_t)(((uint64_t)1000000 << 16) / mFramerate);
        }
        mLastTimestamp = inputBufferHeader->nTimeStamp;
        codec_return = vpx_codec_encode(
                mCodecContext,
                &raw_frame,
                inputBufferHeader->nTimeStamp,  // in timebase units
                frameDuration,  // frame duration in timebase units
                flags,  // frame flags
                VPX_DL_REALTIME);  // encoding deadline
        if (codec_return != VPX_CODEC_OK) {
            ALOGE("vpx encoder failed to encode frame");
            notify(OMX_EventError,
                   OMX_ErrorUndefined,
                   0,  // Extra notification data
                   NULL);  // Notification data pointer
            return;
        }

        vpx_codec_iter_t encoded_packet_iterator = NULL;
        const vpx_codec_cx_pkt_t* encoded_packet;

        while ((encoded_packet = vpx_codec_get_cx_data(
                        mCodecContext, &encoded_packet_iterator))) {
            if (encoded_packet->kind == VPX_CODEC_CX_FRAME_PKT) {
                outputBufferHeader->nTimeStamp = encoded_packet->data.frame.pts;
                outputBufferHeader->nFlags = 0;
                if (encoded_packet->data.frame.flags & VPX_FRAME_IS_KEY)
                  outputBufferHeader->nFlags |= OMX_BUFFERFLAG_SYNCFRAME;
                outputBufferHeader->nOffset = 0;
                outputBufferHeader->nFilledLen = encoded_packet->data.frame.sz;
                memcpy(outputBufferHeader->pBuffer,
                       encoded_packet->data.frame.buf,
                       encoded_packet->data.frame.sz);
                outputBufferInfo->mOwnedByUs = false;
                outputBufferInfoQueue.erase(outputBufferInfoQueue.begin());
                notifyFillBufferDone(outputBufferHeader);
            }
        }

        inputBufferInfo->mOwnedByUs = false;
        inputBufferInfoQueue.erase(inputBufferInfoQueue.begin());
        notifyEmptyBufferDone(inputBufferHeader);
    }
}

}  // namespace android


android::SoftOMXComponent *createSoftOMXComponent(
        const char *name, const OMX_CALLBACKTYPE *callbacks,
        OMX_PTR appData, OMX_COMPONENTTYPE **component) {
    return new android::SoftVPXEncoder(name, callbacks, appData, component);
}
