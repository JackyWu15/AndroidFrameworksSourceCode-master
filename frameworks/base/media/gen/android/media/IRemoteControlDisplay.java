/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: K:\\AndroidFrameworksSourceCode-master\\base\\media\\java\\android\\media\\IRemoteControlDisplay.aidl
 */
package android.media;
/**
 * @hide
 * Interface registered through AudioManager of an object that displays information
 * received from a remote control client.
 * {@see AudioManager#registerRemoteControlDisplay(IRemoteControlDisplay)}.
 */
public interface IRemoteControlDisplay extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.media.IRemoteControlDisplay
{
private static final java.lang.String DESCRIPTOR = "android.media.IRemoteControlDisplay";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.media.IRemoteControlDisplay interface,
 * generating a proxy if needed.
 */
public static android.media.IRemoteControlDisplay asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.media.IRemoteControlDisplay))) {
return ((android.media.IRemoteControlDisplay)iin);
}
return new android.media.IRemoteControlDisplay.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
java.lang.String descriptor = DESCRIPTOR;
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(descriptor);
return true;
}
case TRANSACTION_setCurrentClientId:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
android.app.PendingIntent _arg1;
if ((0!=data.readInt())) {
_arg1 = android.app.PendingIntent.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
boolean _arg2;
_arg2 = (0!=data.readInt());
this.setCurrentClientId(_arg0, _arg1, _arg2);
return true;
}
case TRANSACTION_setEnabled:
{
data.enforceInterface(descriptor);
boolean _arg0;
_arg0 = (0!=data.readInt());
this.setEnabled(_arg0);
return true;
}
case TRANSACTION_setPlaybackState:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
long _arg2;
_arg2 = data.readLong();
long _arg3;
_arg3 = data.readLong();
float _arg4;
_arg4 = data.readFloat();
this.setPlaybackState(_arg0, _arg1, _arg2, _arg3, _arg4);
return true;
}
case TRANSACTION_setTransportControlInfo:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
this.setTransportControlInfo(_arg0, _arg1, _arg2);
return true;
}
case TRANSACTION_setMetadata:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
android.os.Bundle _arg1;
if ((0!=data.readInt())) {
_arg1 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
this.setMetadata(_arg0, _arg1);
return true;
}
case TRANSACTION_setArtwork:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
android.graphics.Bitmap _arg1;
if ((0!=data.readInt())) {
_arg1 = android.graphics.Bitmap.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
this.setArtwork(_arg0, _arg1);
return true;
}
case TRANSACTION_setAllMetadata:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
android.os.Bundle _arg1;
if ((0!=data.readInt())) {
_arg1 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
android.graphics.Bitmap _arg2;
if ((0!=data.readInt())) {
_arg2 = android.graphics.Bitmap.CREATOR.createFromParcel(data);
}
else {
_arg2 = null;
}
this.setAllMetadata(_arg0, _arg1, _arg2);
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements android.media.IRemoteControlDisplay
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
/**
     * Sets the generation counter of the current client that is displayed on the remote control.
     * @param clientGeneration the new RemoteControlClient generation
     * @param clientMediaIntent the PendingIntent associated with the client.
     *    May be null, which implies there is no registered media button event receiver.
     * @param clearing true if the new client generation value maps to a remote control update
     *    where the display should be cleared.
     */
@Override public void setCurrentClientId(int clientGeneration, android.app.PendingIntent clientMediaIntent, boolean clearing) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(clientGeneration);
if ((clientMediaIntent!=null)) {
_data.writeInt(1);
clientMediaIntent.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeInt(((clearing)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setCurrentClientId, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
/**
     * Sets whether the controls of this display are enabled
     * @param if false, the display shouldn't any commands
     */
@Override public void setEnabled(boolean enabled) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((enabled)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setEnabled, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
/**
     * Sets the playback information (state, position and speed) of a client.
     * @param generationId the current generation ID as known by this client
     * @param state the current playback state, one of the following values:
     *       {@link RemoteControlClient#PLAYSTATE_STOPPED},
     *       {@link RemoteControlClient#PLAYSTATE_PAUSED},
     *       {@link RemoteControlClient#PLAYSTATE_PLAYING},
     *       {@link RemoteControlClient#PLAYSTATE_FAST_FORWARDING},
     *       {@link RemoteControlClient#PLAYSTATE_REWINDING},
     *       {@link RemoteControlClient#PLAYSTATE_SKIPPING_FORWARDS},
     *       {@link RemoteControlClient#PLAYSTATE_SKIPPING_BACKWARDS},
     *       {@link RemoteControlClient#PLAYSTATE_BUFFERING},
     *       {@link RemoteControlClient#PLAYSTATE_ERROR}.
     * @param stateChangeTimeMs the time at which the client reported the playback information
     * @param currentPosMs a 0 or positive value for the current media position expressed in ms
     *    Strictly negative values imply that position is not known:
     *    a value of {@link RemoteControlClient#PLAYBACK_POSITION_INVALID} is intended to express
     *    that an application doesn't know the position (e.g. listening to a live stream of a radio)
     *    or that the position information is not applicable (e.g. when state
     *    is {@link RemoteControlClient#PLAYSTATE_BUFFERING} and nothing had played yet);
     *    a value of {@link RemoteControlClient#PLAYBACK_POSITION_ALWAYS_UNKNOWN} implies that the
     *    application uses {@link RemoteControlClient#setPlaybackState(int)} (legacy API) and will
     *    never pass a playback position.
     * @param speed a value expressed as a ratio of 1x playback: 1.0f is normal playback,
     *    2.0f is 2x, 0.5f is half-speed, -2.0f is rewind at 2x speed. 0.0f means nothing is
     *    playing (e.g. when state is {@link RemoteControlClient#PLAYSTATE_ERROR}).
     */
@Override public void setPlaybackState(int generationId, int state, long stateChangeTimeMs, long currentPosMs, float speed) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(generationId);
_data.writeInt(state);
_data.writeLong(stateChangeTimeMs);
_data.writeLong(currentPosMs);
_data.writeFloat(speed);
mRemote.transact(Stub.TRANSACTION_setPlaybackState, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
/**
     * Sets the transport control flags and playback position capabilities of a client.
     * @param generationId the current generation ID as known by this client
     * @param transportControlFlags bitmask of the transport controls this client supports, see
     *         {@link RemoteControlClient#setTransportControlFlags(int)}
     * @param posCapabilities a bit mask for playback position capabilities, see
     *         {@link RemoteControlClient#MEDIA_POSITION_READABLE} and
     *         {@link RemoteControlClient#MEDIA_POSITION_WRITABLE}
     */
@Override public void setTransportControlInfo(int generationId, int transportControlFlags, int posCapabilities) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(generationId);
_data.writeInt(transportControlFlags);
_data.writeInt(posCapabilities);
mRemote.transact(Stub.TRANSACTION_setTransportControlInfo, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void setMetadata(int generationId, android.os.Bundle metadata) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(generationId);
if ((metadata!=null)) {
_data.writeInt(1);
metadata.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_setMetadata, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void setArtwork(int generationId, android.graphics.Bitmap artwork) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(generationId);
if ((artwork!=null)) {
_data.writeInt(1);
artwork.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_setArtwork, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
/**
     * To combine metadata text and artwork in one binder call
     */
@Override public void setAllMetadata(int generationId, android.os.Bundle metadata, android.graphics.Bitmap artwork) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(generationId);
if ((metadata!=null)) {
_data.writeInt(1);
metadata.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
if ((artwork!=null)) {
_data.writeInt(1);
artwork.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_setAllMetadata, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_setCurrentClientId = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_setEnabled = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_setPlaybackState = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_setTransportControlInfo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_setMetadata = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_setArtwork = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_setAllMetadata = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
}
/**
     * Sets the generation counter of the current client that is displayed on the remote control.
     * @param clientGeneration the new RemoteControlClient generation
     * @param clientMediaIntent the PendingIntent associated with the client.
     *    May be null, which implies there is no registered media button event receiver.
     * @param clearing true if the new client generation value maps to a remote control update
     *    where the display should be cleared.
     */
public void setCurrentClientId(int clientGeneration, android.app.PendingIntent clientMediaIntent, boolean clearing) throws android.os.RemoteException;
/**
     * Sets whether the controls of this display are enabled
     * @param if false, the display shouldn't any commands
     */
public void setEnabled(boolean enabled) throws android.os.RemoteException;
/**
     * Sets the playback information (state, position and speed) of a client.
     * @param generationId the current generation ID as known by this client
     * @param state the current playback state, one of the following values:
     *       {@link RemoteControlClient#PLAYSTATE_STOPPED},
     *       {@link RemoteControlClient#PLAYSTATE_PAUSED},
     *       {@link RemoteControlClient#PLAYSTATE_PLAYING},
     *       {@link RemoteControlClient#PLAYSTATE_FAST_FORWARDING},
     *       {@link RemoteControlClient#PLAYSTATE_REWINDING},
     *       {@link RemoteControlClient#PLAYSTATE_SKIPPING_FORWARDS},
     *       {@link RemoteControlClient#PLAYSTATE_SKIPPING_BACKWARDS},
     *       {@link RemoteControlClient#PLAYSTATE_BUFFERING},
     *       {@link RemoteControlClient#PLAYSTATE_ERROR}.
     * @param stateChangeTimeMs the time at which the client reported the playback information
     * @param currentPosMs a 0 or positive value for the current media position expressed in ms
     *    Strictly negative values imply that position is not known:
     *    a value of {@link RemoteControlClient#PLAYBACK_POSITION_INVALID} is intended to express
     *    that an application doesn't know the position (e.g. listening to a live stream of a radio)
     *    or that the position information is not applicable (e.g. when state
     *    is {@link RemoteControlClient#PLAYSTATE_BUFFERING} and nothing had played yet);
     *    a value of {@link RemoteControlClient#PLAYBACK_POSITION_ALWAYS_UNKNOWN} implies that the
     *    application uses {@link RemoteControlClient#setPlaybackState(int)} (legacy API) and will
     *    never pass a playback position.
     * @param speed a value expressed as a ratio of 1x playback: 1.0f is normal playback,
     *    2.0f is 2x, 0.5f is half-speed, -2.0f is rewind at 2x speed. 0.0f means nothing is
     *    playing (e.g. when state is {@link RemoteControlClient#PLAYSTATE_ERROR}).
     */
public void setPlaybackState(int generationId, int state, long stateChangeTimeMs, long currentPosMs, float speed) throws android.os.RemoteException;
/**
     * Sets the transport control flags and playback position capabilities of a client.
     * @param generationId the current generation ID as known by this client
     * @param transportControlFlags bitmask of the transport controls this client supports, see
     *         {@link RemoteControlClient#setTransportControlFlags(int)}
     * @param posCapabilities a bit mask for playback position capabilities, see
     *         {@link RemoteControlClient#MEDIA_POSITION_READABLE} and
     *         {@link RemoteControlClient#MEDIA_POSITION_WRITABLE}
     */
public void setTransportControlInfo(int generationId, int transportControlFlags, int posCapabilities) throws android.os.RemoteException;
public void setMetadata(int generationId, android.os.Bundle metadata) throws android.os.RemoteException;
public void setArtwork(int generationId, android.graphics.Bitmap artwork) throws android.os.RemoteException;
/**
     * To combine metadata text and artwork in one binder call
     */
public void setAllMetadata(int generationId, android.os.Bundle metadata, android.graphics.Bitmap artwork) throws android.os.RemoteException;
}
