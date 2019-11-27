/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: K:\\AndroidFrameworksSourceCode-master\\base\\media\\java\\android\\media\\tv\\ITvInputManager.aidl
 */
package android.media.tv;
/**
 * Interface to the TV input manager service.
 * @hide
 */
public interface ITvInputManager extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.media.tv.ITvInputManager
{
private static final java.lang.String DESCRIPTOR = "android.media.tv.ITvInputManager";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.media.tv.ITvInputManager interface,
 * generating a proxy if needed.
 */
public static android.media.tv.ITvInputManager asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.media.tv.ITvInputManager))) {
return ((android.media.tv.ITvInputManager)iin);
}
return new android.media.tv.ITvInputManager.Stub.Proxy(obj);
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
case TRANSACTION_getTvInputList:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
java.util.List<android.media.tv.TvInputInfo> _result = this.getTvInputList(_arg0);
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_getTvInputInfo:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
int _arg1;
_arg1 = data.readInt();
android.media.tv.TvInputInfo _result = this.getTvInputInfo(_arg0, _arg1);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_getTvContentRatingSystemList:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
java.util.List<android.media.tv.TvContentRatingSystemInfo> _result = this.getTvContentRatingSystemList(_arg0);
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_registerCallback:
{
data.enforceInterface(descriptor);
android.media.tv.ITvInputManagerCallback _arg0;
_arg0 = android.media.tv.ITvInputManagerCallback.Stub.asInterface(data.readStrongBinder());
int _arg1;
_arg1 = data.readInt();
this.registerCallback(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_unregisterCallback:
{
data.enforceInterface(descriptor);
android.media.tv.ITvInputManagerCallback _arg0;
_arg0 = android.media.tv.ITvInputManagerCallback.Stub.asInterface(data.readStrongBinder());
int _arg1;
_arg1 = data.readInt();
this.unregisterCallback(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_isParentalControlsEnabled:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
boolean _result = this.isParentalControlsEnabled(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_setParentalControlsEnabled:
{
data.enforceInterface(descriptor);
boolean _arg0;
_arg0 = (0!=data.readInt());
int _arg1;
_arg1 = data.readInt();
this.setParentalControlsEnabled(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_isRatingBlocked:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
int _arg1;
_arg1 = data.readInt();
boolean _result = this.isRatingBlocked(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getBlockedRatings:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
java.util.List<java.lang.String> _result = this.getBlockedRatings(_arg0);
reply.writeNoException();
reply.writeStringList(_result);
return true;
}
case TRANSACTION_addBlockedRating:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
int _arg1;
_arg1 = data.readInt();
this.addBlockedRating(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_removeBlockedRating:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
int _arg1;
_arg1 = data.readInt();
this.removeBlockedRating(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_createSession:
{
data.enforceInterface(descriptor);
android.media.tv.ITvInputClient _arg0;
_arg0 = android.media.tv.ITvInputClient.Stub.asInterface(data.readStrongBinder());
java.lang.String _arg1;
_arg1 = data.readString();
int _arg2;
_arg2 = data.readInt();
int _arg3;
_arg3 = data.readInt();
this.createSession(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_releaseSession:
{
data.enforceInterface(descriptor);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
int _arg1;
_arg1 = data.readInt();
this.releaseSession(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_setMainSession:
{
data.enforceInterface(descriptor);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
int _arg1;
_arg1 = data.readInt();
this.setMainSession(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_setSurface:
{
data.enforceInterface(descriptor);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
android.view.Surface _arg1;
if ((0!=data.readInt())) {
_arg1 = android.view.Surface.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
int _arg2;
_arg2 = data.readInt();
this.setSurface(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_dispatchSurfaceChanged:
{
data.enforceInterface(descriptor);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
int _arg3;
_arg3 = data.readInt();
int _arg4;
_arg4 = data.readInt();
this.dispatchSurfaceChanged(_arg0, _arg1, _arg2, _arg3, _arg4);
reply.writeNoException();
return true;
}
case TRANSACTION_setVolume:
{
data.enforceInterface(descriptor);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
float _arg1;
_arg1 = data.readFloat();
int _arg2;
_arg2 = data.readInt();
this.setVolume(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_tune:
{
data.enforceInterface(descriptor);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
android.net.Uri _arg1;
if ((0!=data.readInt())) {
_arg1 = android.net.Uri.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
android.os.Bundle _arg2;
if ((0!=data.readInt())) {
_arg2 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg2 = null;
}
int _arg3;
_arg3 = data.readInt();
this.tune(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_setCaptionEnabled:
{
data.enforceInterface(descriptor);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
boolean _arg1;
_arg1 = (0!=data.readInt());
int _arg2;
_arg2 = data.readInt();
this.setCaptionEnabled(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_selectTrack:
{
data.enforceInterface(descriptor);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
int _arg1;
_arg1 = data.readInt();
java.lang.String _arg2;
_arg2 = data.readString();
int _arg3;
_arg3 = data.readInt();
this.selectTrack(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_sendAppPrivateCommand:
{
data.enforceInterface(descriptor);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
java.lang.String _arg1;
_arg1 = data.readString();
android.os.Bundle _arg2;
if ((0!=data.readInt())) {
_arg2 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg2 = null;
}
int _arg3;
_arg3 = data.readInt();
this.sendAppPrivateCommand(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_createOverlayView:
{
data.enforceInterface(descriptor);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
android.os.IBinder _arg1;
_arg1 = data.readStrongBinder();
android.graphics.Rect _arg2;
if ((0!=data.readInt())) {
_arg2 = android.graphics.Rect.CREATOR.createFromParcel(data);
}
else {
_arg2 = null;
}
int _arg3;
_arg3 = data.readInt();
this.createOverlayView(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_relayoutOverlayView:
{
data.enforceInterface(descriptor);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
android.graphics.Rect _arg1;
if ((0!=data.readInt())) {
_arg1 = android.graphics.Rect.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
int _arg2;
_arg2 = data.readInt();
this.relayoutOverlayView(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_removeOverlayView:
{
data.enforceInterface(descriptor);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
int _arg1;
_arg1 = data.readInt();
this.removeOverlayView(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_requestUnblockContent:
{
data.enforceInterface(descriptor);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
java.lang.String _arg1;
_arg1 = data.readString();
int _arg2;
_arg2 = data.readInt();
this.requestUnblockContent(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_getHardwareList:
{
data.enforceInterface(descriptor);
java.util.List<android.media.tv.TvInputHardwareInfo> _result = this.getHardwareList();
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_acquireTvInputHardware:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
android.media.tv.ITvInputHardwareCallback _arg1;
_arg1 = android.media.tv.ITvInputHardwareCallback.Stub.asInterface(data.readStrongBinder());
android.media.tv.TvInputInfo _arg2;
if ((0!=data.readInt())) {
_arg2 = android.media.tv.TvInputInfo.CREATOR.createFromParcel(data);
}
else {
_arg2 = null;
}
int _arg3;
_arg3 = data.readInt();
android.media.tv.ITvInputHardware _result = this.acquireTvInputHardware(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_releaseTvInputHardware:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
android.media.tv.ITvInputHardware _arg1;
_arg1 = android.media.tv.ITvInputHardware.Stub.asInterface(data.readStrongBinder());
int _arg2;
_arg2 = data.readInt();
this.releaseTvInputHardware(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_getAvailableTvStreamConfigList:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
int _arg1;
_arg1 = data.readInt();
java.util.List<android.media.tv.TvStreamConfig> _result = this.getAvailableTvStreamConfigList(_arg0, _arg1);
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_captureFrame:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
android.view.Surface _arg1;
if ((0!=data.readInt())) {
_arg1 = android.view.Surface.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
android.media.tv.TvStreamConfig _arg2;
if ((0!=data.readInt())) {
_arg2 = android.media.tv.TvStreamConfig.CREATOR.createFromParcel(data);
}
else {
_arg2 = null;
}
int _arg3;
_arg3 = data.readInt();
boolean _result = this.captureFrame(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_isSingleSessionActive:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
boolean _result = this.isSingleSessionActive(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements android.media.tv.ITvInputManager
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
@Override public java.util.List<android.media.tv.TvInputInfo> getTvInputList(int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<android.media.tv.TvInputInfo> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_getTvInputList, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(android.media.tv.TvInputInfo.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public android.media.tv.TvInputInfo getTvInputInfo(java.lang.String inputId, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.media.tv.TvInputInfo _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(inputId);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_getTvInputInfo, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = android.media.tv.TvInputInfo.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List<android.media.tv.TvContentRatingSystemInfo> getTvContentRatingSystemList(int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<android.media.tv.TvContentRatingSystemInfo> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_getTvContentRatingSystemList, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(android.media.tv.TvContentRatingSystemInfo.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void registerCallback(android.media.tv.ITvInputManagerCallback callback, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_registerCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void unregisterCallback(android.media.tv.ITvInputManagerCallback callback, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_unregisterCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public boolean isParentalControlsEnabled(int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_isParentalControlsEnabled, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setParentalControlsEnabled(boolean enabled, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((enabled)?(1):(0)));
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_setParentalControlsEnabled, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public boolean isRatingBlocked(java.lang.String rating, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(rating);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_isRatingBlocked, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List<java.lang.String> getBlockedRatings(int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<java.lang.String> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_getBlockedRatings, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArrayList();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void addBlockedRating(java.lang.String rating, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(rating);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_addBlockedRating, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void removeBlockedRating(java.lang.String rating, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(rating);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_removeBlockedRating, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void createSession(android.media.tv.ITvInputClient client, java.lang.String inputId, int seq, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((client!=null))?(client.asBinder()):(null)));
_data.writeString(inputId);
_data.writeInt(seq);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_createSession, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void releaseSession(android.os.IBinder sessionToken, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(sessionToken);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_releaseSession, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void setMainSession(android.os.IBinder sessionToken, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(sessionToken);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_setMainSession, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void setSurface(android.os.IBinder sessionToken, android.view.Surface surface, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(sessionToken);
if ((surface!=null)) {
_data.writeInt(1);
surface.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_setSurface, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void dispatchSurfaceChanged(android.os.IBinder sessionToken, int format, int width, int height, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(sessionToken);
_data.writeInt(format);
_data.writeInt(width);
_data.writeInt(height);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_dispatchSurfaceChanged, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void setVolume(android.os.IBinder sessionToken, float volume, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(sessionToken);
_data.writeFloat(volume);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_setVolume, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void tune(android.os.IBinder sessionToken, android.net.Uri channelUri, android.os.Bundle params, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(sessionToken);
if ((channelUri!=null)) {
_data.writeInt(1);
channelUri.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
if ((params!=null)) {
_data.writeInt(1);
params.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_tune, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void setCaptionEnabled(android.os.IBinder sessionToken, boolean enabled, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(sessionToken);
_data.writeInt(((enabled)?(1):(0)));
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_setCaptionEnabled, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void selectTrack(android.os.IBinder sessionToken, int type, java.lang.String trackId, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(sessionToken);
_data.writeInt(type);
_data.writeString(trackId);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_selectTrack, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void sendAppPrivateCommand(android.os.IBinder sessionToken, java.lang.String action, android.os.Bundle data, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(sessionToken);
_data.writeString(action);
if ((data!=null)) {
_data.writeInt(1);
data.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_sendAppPrivateCommand, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void createOverlayView(android.os.IBinder sessionToken, android.os.IBinder windowToken, android.graphics.Rect frame, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(sessionToken);
_data.writeStrongBinder(windowToken);
if ((frame!=null)) {
_data.writeInt(1);
frame.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_createOverlayView, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void relayoutOverlayView(android.os.IBinder sessionToken, android.graphics.Rect frame, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(sessionToken);
if ((frame!=null)) {
_data.writeInt(1);
frame.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_relayoutOverlayView, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void removeOverlayView(android.os.IBinder sessionToken, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(sessionToken);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_removeOverlayView, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void requestUnblockContent(android.os.IBinder sessionToken, java.lang.String unblockedRating, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(sessionToken);
_data.writeString(unblockedRating);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_requestUnblockContent, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// For TV input hardware binding

@Override public java.util.List<android.media.tv.TvInputHardwareInfo> getHardwareList() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<android.media.tv.TvInputHardwareInfo> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getHardwareList, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(android.media.tv.TvInputHardwareInfo.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public android.media.tv.ITvInputHardware acquireTvInputHardware(int deviceId, android.media.tv.ITvInputHardwareCallback callback, android.media.tv.TvInputInfo info, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.media.tv.ITvInputHardware _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(deviceId);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
if ((info!=null)) {
_data.writeInt(1);
info.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_acquireTvInputHardware, _data, _reply, 0);
_reply.readException();
_result = android.media.tv.ITvInputHardware.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void releaseTvInputHardware(int deviceId, android.media.tv.ITvInputHardware hardware, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(deviceId);
_data.writeStrongBinder((((hardware!=null))?(hardware.asBinder()):(null)));
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_releaseTvInputHardware, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// For TV input capturing

@Override public java.util.List<android.media.tv.TvStreamConfig> getAvailableTvStreamConfigList(java.lang.String inputId, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<android.media.tv.TvStreamConfig> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(inputId);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_getAvailableTvStreamConfigList, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(android.media.tv.TvStreamConfig.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean captureFrame(java.lang.String inputId, android.view.Surface surface, android.media.tv.TvStreamConfig config, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(inputId);
if ((surface!=null)) {
_data.writeInt(1);
surface.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
if ((config!=null)) {
_data.writeInt(1);
config.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_captureFrame, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean isSingleSessionActive(int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_isSingleSessionActive, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getTvInputList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getTvInputInfo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getTvContentRatingSystemList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_registerCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_unregisterCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_isParentalControlsEnabled = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_setParentalControlsEnabled = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_isRatingBlocked = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_getBlockedRatings = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_addBlockedRating = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_removeBlockedRating = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_createSession = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_releaseSession = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
static final int TRANSACTION_setMainSession = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
static final int TRANSACTION_setSurface = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
static final int TRANSACTION_dispatchSurfaceChanged = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
static final int TRANSACTION_setVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16);
static final int TRANSACTION_tune = (android.os.IBinder.FIRST_CALL_TRANSACTION + 17);
static final int TRANSACTION_setCaptionEnabled = (android.os.IBinder.FIRST_CALL_TRANSACTION + 18);
static final int TRANSACTION_selectTrack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 19);
static final int TRANSACTION_sendAppPrivateCommand = (android.os.IBinder.FIRST_CALL_TRANSACTION + 20);
static final int TRANSACTION_createOverlayView = (android.os.IBinder.FIRST_CALL_TRANSACTION + 21);
static final int TRANSACTION_relayoutOverlayView = (android.os.IBinder.FIRST_CALL_TRANSACTION + 22);
static final int TRANSACTION_removeOverlayView = (android.os.IBinder.FIRST_CALL_TRANSACTION + 23);
static final int TRANSACTION_requestUnblockContent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 24);
static final int TRANSACTION_getHardwareList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 25);
static final int TRANSACTION_acquireTvInputHardware = (android.os.IBinder.FIRST_CALL_TRANSACTION + 26);
static final int TRANSACTION_releaseTvInputHardware = (android.os.IBinder.FIRST_CALL_TRANSACTION + 27);
static final int TRANSACTION_getAvailableTvStreamConfigList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 28);
static final int TRANSACTION_captureFrame = (android.os.IBinder.FIRST_CALL_TRANSACTION + 29);
static final int TRANSACTION_isSingleSessionActive = (android.os.IBinder.FIRST_CALL_TRANSACTION + 30);
}
public java.util.List<android.media.tv.TvInputInfo> getTvInputList(int userId) throws android.os.RemoteException;
public android.media.tv.TvInputInfo getTvInputInfo(java.lang.String inputId, int userId) throws android.os.RemoteException;
public java.util.List<android.media.tv.TvContentRatingSystemInfo> getTvContentRatingSystemList(int userId) throws android.os.RemoteException;
public void registerCallback(android.media.tv.ITvInputManagerCallback callback, int userId) throws android.os.RemoteException;
public void unregisterCallback(android.media.tv.ITvInputManagerCallback callback, int userId) throws android.os.RemoteException;
public boolean isParentalControlsEnabled(int userId) throws android.os.RemoteException;
public void setParentalControlsEnabled(boolean enabled, int userId) throws android.os.RemoteException;
public boolean isRatingBlocked(java.lang.String rating, int userId) throws android.os.RemoteException;
public java.util.List<java.lang.String> getBlockedRatings(int userId) throws android.os.RemoteException;
public void addBlockedRating(java.lang.String rating, int userId) throws android.os.RemoteException;
public void removeBlockedRating(java.lang.String rating, int userId) throws android.os.RemoteException;
public void createSession(android.media.tv.ITvInputClient client, java.lang.String inputId, int seq, int userId) throws android.os.RemoteException;
public void releaseSession(android.os.IBinder sessionToken, int userId) throws android.os.RemoteException;
public void setMainSession(android.os.IBinder sessionToken, int userId) throws android.os.RemoteException;
public void setSurface(android.os.IBinder sessionToken, android.view.Surface surface, int userId) throws android.os.RemoteException;
public void dispatchSurfaceChanged(android.os.IBinder sessionToken, int format, int width, int height, int userId) throws android.os.RemoteException;
public void setVolume(android.os.IBinder sessionToken, float volume, int userId) throws android.os.RemoteException;
public void tune(android.os.IBinder sessionToken, android.net.Uri channelUri, android.os.Bundle params, int userId) throws android.os.RemoteException;
public void setCaptionEnabled(android.os.IBinder sessionToken, boolean enabled, int userId) throws android.os.RemoteException;
public void selectTrack(android.os.IBinder sessionToken, int type, java.lang.String trackId, int userId) throws android.os.RemoteException;
public void sendAppPrivateCommand(android.os.IBinder sessionToken, java.lang.String action, android.os.Bundle data, int userId) throws android.os.RemoteException;
public void createOverlayView(android.os.IBinder sessionToken, android.os.IBinder windowToken, android.graphics.Rect frame, int userId) throws android.os.RemoteException;
public void relayoutOverlayView(android.os.IBinder sessionToken, android.graphics.Rect frame, int userId) throws android.os.RemoteException;
public void removeOverlayView(android.os.IBinder sessionToken, int userId) throws android.os.RemoteException;
public void requestUnblockContent(android.os.IBinder sessionToken, java.lang.String unblockedRating, int userId) throws android.os.RemoteException;
// For TV input hardware binding

public java.util.List<android.media.tv.TvInputHardwareInfo> getHardwareList() throws android.os.RemoteException;
public android.media.tv.ITvInputHardware acquireTvInputHardware(int deviceId, android.media.tv.ITvInputHardwareCallback callback, android.media.tv.TvInputInfo info, int userId) throws android.os.RemoteException;
public void releaseTvInputHardware(int deviceId, android.media.tv.ITvInputHardware hardware, int userId) throws android.os.RemoteException;
// For TV input capturing

public java.util.List<android.media.tv.TvStreamConfig> getAvailableTvStreamConfigList(java.lang.String inputId, int userId) throws android.os.RemoteException;
public boolean captureFrame(java.lang.String inputId, android.view.Surface surface, android.media.tv.TvStreamConfig config, int userId) throws android.os.RemoteException;
public boolean isSingleSessionActive(int userId) throws android.os.RemoteException;
}
