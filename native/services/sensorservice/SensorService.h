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

#ifndef ANDROID_SENSOR_SERVICE_H
#define ANDROID_SENSOR_SERVICE_H

#include <stdint.h>
#include <sys/types.h>

#include <utils/Vector.h>
#include <utils/SortedVector.h>
#include <utils/KeyedVector.h>
#include <utils/threads.h>
#include <utils/AndroidThreads.h>
#include <utils/RefBase.h>
#include <utils/Looper.h>

#include <binder/BinderService.h>

#include <gui/Sensor.h>
#include <gui/BitTube.h>
#include <gui/ISensorServer.h>
#include <gui/ISensorEventConnection.h>

#include "SensorInterface.h"

// ---------------------------------------------------------------------------

#define DEBUG_CONNECTIONS   false
// Max size is 100 KB which is enough to accept a batch of about 1000 events.
#define MAX_SOCKET_BUFFER_SIZE_BATCHED 100 * 1024
// For older HALs which don't support batching, use a smaller socket buffer size.
#define SOCKET_BUFFER_SIZE_NON_BATCHED 4 * 1024

struct sensors_poll_device_t;
struct sensors_module_t;

namespace android {
// ---------------------------------------------------------------------------

class SensorService :
        public BinderService<SensorService>,
        public BnSensorServer,
        protected Thread
{
    friend class BinderService<SensorService>;

    static const char* WAKE_LOCK_NAME;

    static char const* getServiceName() ANDROID_API { return "sensorservice"; }
    SensorService() ANDROID_API;
    virtual ~SensorService();

    virtual void onFirstRef();

    // Thread interface
    virtual bool threadLoop();

    // ISensorServer interface
    virtual Vector<Sensor> getSensorList();
    virtual sp<ISensorEventConnection> createSensorEventConnection();
    virtual status_t dump(int fd, const Vector<String16>& args);

    class SensorEventConnection : public BnSensorEventConnection, public LooperCallback {
        friend class SensorService;
        virtual ~SensorEventConnection();
        virtual void onFirstRef();
        virtual sp<BitTube> getSensorChannel() const;
        virtual status_t enableDisable(int handle, bool enabled, nsecs_t samplingPeriodNs,
                                       nsecs_t maxBatchReportLatencyNs, int reservedFlags);
        virtual status_t setEventRate(int handle, nsecs_t samplingPeriodNs);
        virtual status_t flush();
        // Count the number of flush complete events which are about to be dropped in the buffer.
        // Increment mPendingFlushEventsToSend in mSensorInfo. These flush complete events will be
        // sent separately before the next batch of events.
        void countFlushCompleteEventsLocked(sensors_event_t const* scratch, int numEventsDropped);

        // Check if there are any wake up events in the buffer. If yes, return the index of the
        // first wake_up sensor event in the buffer else return -1. This wake_up sensor event will
        // have the flag WAKE_UP_SENSOR_EVENT_NEEDS_ACK set. Exactly one event per packet will have
        // the wake_up flag set. SOCK_SEQPACKET ensures that either the entire packet is read or
        // dropped.
        int findWakeUpSensorEventLocked(sensors_event_t const* scratch, int count);

        // Send pending flush_complete events. There may have been flush_complete_events that are
        // dropped which need to be sent separately before other events. On older HALs (1_0) this
        // method emulates the behavior of flush().
        void sendPendingFlushEventsLocked();

        // Writes events from mEventCache to the socket.
        void writeToSocketFromCacheLocked();

        // Compute the approximate cache size from the FIFO sizes of various sensors registered for
        // this connection. Wake up and non-wake up sensors have separate FIFOs but FIFO may be
        // shared amongst wake-up sensors and non-wake up sensors.
        int computeMaxCacheSizeLocked() const;

        // When more sensors register, the maximum cache size desired may change. Compute max cache
        // size, reallocate memory and copy over events from the older cache.
        void reAllocateCacheLocked(sensors_event_t const* scratch, int count);

        // LooperCallback method. If there is data to read on this fd, it is an ack from the
        // app that it has read events from a wake up sensor, decrement mWakeLockRefCount.
        // If this fd is available for writing send the data from the cache.
        virtual int handleEvent(int fd, int events, void* data);

        // Increment mPendingFlushEventsToSend for the given sensor handle.
        void incrementPendingFlushCount(int32_t handle);

        // Add or remove the file descriptor associated with the BitTube to the looper. If mDead is
        // set to true or there are no more sensors for this connection, the file descriptor is
        // removed if it has been previously added to the Looper. Depending on the state of the
        // connection FD may be added to the Looper. The flags to set are determined by the internal
        // state of the connection. FDs are added to the looper when wake-up sensors are registered
        // (to poll for acknowledgements) and when write fails on the socket when there are too many
        // events (to poll when the FD is available for writing). FDs are removed when there is an
        // error and the other end hangs up or when this client unregisters for this connection.
        void updateLooperRegistration(const sp<Looper>& looper);
        void updateLooperRegistrationLocked(const sp<Looper>& looper);

        sp<SensorService> const mService;
        sp<BitTube> mChannel;
        uid_t mUid;
        mutable Mutex mConnectionLock;
        // Number of events from wake up sensors which are still pending and haven't been delivered
        // to the corresponding application. It is incremented by one unit for each write to the
        // socket.
        uint32_t mWakeLockRefCount;

        // If this flag is set to true, it means that the file descriptor associated with the
        // BitTube has been added to the Looper in SensorService. This flag is typically set when
        // this connection has wake-up sensors associated with it or when write has failed on this
        // connection and we're storing some events in the cache.
        bool mHasLooperCallbacks;
        // If there are any errors associated with the Looper this flag is set to true and
        // mWakeLockRefCount is reset to zero. needsWakeLock method will always return false, if
        // this flag is set.
        bool mDead;
        struct FlushInfo {
            // The number of flush complete events dropped for this sensor is stored here.
            // They are sent separately before the next batch of events.
            int mPendingFlushEventsToSend;
            // Every activate is preceded by a flush. Only after the first flush complete is
            // received, the events for the sensor are sent on that *connection*.
            bool mFirstFlushPending;
            FlushInfo() : mPendingFlushEventsToSend(0), mFirstFlushPending(false) {}
        };
        // protected by SensorService::mLock. Key for this vector is the sensor handle.
        KeyedVector<int, FlushInfo> mSensorInfo;
        sensors_event_t *mEventCache;
        int mCacheSize, mMaxCacheSize;

#if DEBUG_CONNECTIONS
        int mEventsReceived, mEventsSent, mEventsSentFromCache;
        int mTotalAcksNeeded, mTotalAcksReceived;
#endif

    public:
        SensorEventConnection(const sp<SensorService>& service, uid_t uid);

        status_t sendEvents(sensors_event_t const* buffer, size_t count,
                sensors_event_t* scratch,
                SensorEventConnection const * const * mapFlushEventsToConnections = NULL);
        bool hasSensor(int32_t handle) const;
        bool hasAnySensor() const;
        bool hasOneShotSensors() const;
        bool addSensor(int32_t handle);
        bool removeSensor(int32_t handle);
        void setFirstFlushPending(int32_t handle, bool value);
        void dump(String8& result);
        bool needsWakeLock();

        uid_t getUid() const { return mUid; }
    };

    class SensorRecord {
        SortedVector< wp<SensorEventConnection> > mConnections;
        // A queue of all flush() calls made on this sensor. Flush complete events will be
        // sent in this order.
        Vector< wp<SensorEventConnection> > mPendingFlushConnections;
    public:
        SensorRecord(const sp<SensorEventConnection>& connection);
        bool addConnection(const sp<SensorEventConnection>& connection);
        bool removeConnection(const wp<SensorEventConnection>& connection);
        size_t getNumConnections() const { return mConnections.size(); }

        void addPendingFlushConnection(const sp<SensorEventConnection>& connection);
        void removeFirstPendingFlushConnection();
        SensorEventConnection * getFirstPendingFlushConnection();
    };

    class SensorEventAckReceiver : public Thread {
        sp<SensorService> const mService;
    public:
        virtual bool threadLoop();
        SensorEventAckReceiver(const sp<SensorService>& service): mService(service) {}
    };

    String8 getSensorName(int handle) const;
    bool isVirtualSensor(int handle) const;
    Sensor getSensorFromHandle(int handle) const;
    bool isWakeUpSensor(int type) const;
    void recordLastValueLocked(sensors_event_t const* buffer, size_t count);
    static void sortEventBuffer(sensors_event_t* buffer, size_t count);
    Sensor registerSensor(SensorInterface* sensor);
    Sensor registerVirtualSensor(SensorInterface* sensor);
    status_t cleanupWithoutDisable(
            const sp<SensorEventConnection>& connection, int handle);
    status_t cleanupWithoutDisableLocked(
            const sp<SensorEventConnection>& connection, int handle);
    void cleanupAutoDisabledSensorLocked(const sp<SensorEventConnection>& connection,
            sensors_event_t const* buffer, const int count);
    static bool canAccessSensor(const Sensor& sensor);
    static bool verifyCanAccessSensor(const Sensor& sensor, const char* operation);
    // SensorService acquires a partial wakelock for delivering events from wake up sensors. This
    // method checks whether all the events from these wake up sensors have been delivered to the
    // corresponding applications, if yes the wakelock is released.
    void checkWakeLockState();
    void checkWakeLockStateLocked();
    bool isWakeUpSensorEvent(const sensors_event_t& event) const;

    SensorRecord * getSensorRecord(int handle);

    sp<Looper> getLooper() const;

    // constants
    Vector<Sensor> mSensorList;
    Vector<Sensor> mUserSensorListDebug;
    Vector<Sensor> mUserSensorList;
    DefaultKeyedVector<int, SensorInterface*> mSensorMap;
    Vector<SensorInterface *> mVirtualSensorList;
    status_t mInitCheck;
    // Socket buffersize used to initialize BitTube. This size depends on whether batching is
    // supported or not.
    uint32_t mSocketBufferSize;
    sp<Looper> mLooper;

    // protected by mLock
    mutable Mutex mLock;
    DefaultKeyedVector<int, SensorRecord*> mActiveSensors;
    DefaultKeyedVector<int, SensorInterface*> mActiveVirtualSensors;
    SortedVector< wp<SensorEventConnection> > mActiveConnections;
    bool mWakeLockAcquired;
    sensors_event_t *mSensorEventBuffer, *mSensorEventScratch;
    SensorEventConnection const **mMapFlushEventsToConnections;

    // The size of this vector is constant, only the items are mutable
    KeyedVector<int32_t, sensors_event_t> mLastEventSeen;

public:
    void cleanupConnection(SensorEventConnection* connection);
    status_t enable(const sp<SensorEventConnection>& connection, int handle,
                    nsecs_t samplingPeriodNs,  nsecs_t maxBatchReportLatencyNs, int reservedFlags);
    status_t disable(const sp<SensorEventConnection>& connection, int handle);
    status_t setEventRate(const sp<SensorEventConnection>& connection, int handle, nsecs_t ns);
    status_t flushSensor(const sp<SensorEventConnection>& connection);
};

// ---------------------------------------------------------------------------
}; // namespace android

#endif // ANDROID_SENSOR_SERVICE_H
