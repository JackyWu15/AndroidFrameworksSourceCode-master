/*
 * Copyright (C) 2014 The Android Open Source Project
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

#ifndef NUPLAYER_DECODER_PASS_THROUGH_H_

#define NUPLAYER_DECODER_PASS_THROUGH_H_

#include "NuPlayer.h"

#include "NuPlayerDecoder.h"

namespace android {

struct NuPlayer::DecoderPassThrough : public Decoder {
    DecoderPassThrough(const sp<AMessage> &notify);

    virtual void configure(const sp<AMessage> &format);
    virtual void init();

    virtual void signalFlush();
    virtual void signalResume();
    virtual void initiateShutdown();

    bool supportsSeamlessFormatChange(const sp<AMessage> &to) const;

protected:

    virtual ~DecoderPassThrough();

    virtual void onMessageReceived(const sp<AMessage> &msg);

private:
    enum {
        kWhatRequestABuffer     = 'reqB',
        kWhatConfigure          = 'conf',
        kWhatInputBufferFilled  = 'inpF',
        kWhatBufferConsumed     = 'bufC',
        kWhatFlush              = 'flus',
        kWhatShutdown           = 'shuD',
    };

    sp<AMessage> mNotify;
    sp<ALooper> mDecoderLooper;

    /** Returns true if a buffer was requested.
     * Returns false if at EOS or cache already full.
     */
    bool requestABuffer();
    bool isStaleReply(const sp<AMessage> &msg);

    void onConfigure(const sp<AMessage> &format);
    void onFlush();
    void onInputBufferFilled(const sp<AMessage> &msg);
    void onBufferConsumed(int32_t size);
    void requestMaxBuffers();
    void onShutdown();

    int32_t mBufferGeneration;
    bool    mReachedEOS;
    // TODO mPendingBuffersToFill and mPendingBuffersToDrain are only for
    // debugging. They can be removed when the power investigation is done.
    size_t  mPendingBuffersToFill;
    size_t  mPendingBuffersToDrain;
    size_t  mCachedBytes;
    AString mComponentName;

    DISALLOW_EVIL_CONSTRUCTORS(DecoderPassThrough);
};

}  // namespace android

#endif  // NUPLAYER_DECODER_PASS_THROUGH_H_
