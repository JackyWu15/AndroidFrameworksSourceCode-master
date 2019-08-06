/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: K:\\AndroidFrameworksSourceCode-master\\base\\media\\java\\android\\media\\IAudioService.aidl
 */
package android.media;
/**
 * {@hide}
 */
public interface IAudioService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.media.IAudioService
{
private static final java.lang.String DESCRIPTOR = "android.media.IAudioService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.media.IAudioService interface,
 * generating a proxy if needed.
 */
public static android.media.IAudioService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.media.IAudioService))) {
return ((android.media.IAudioService)iin);
}
return new android.media.IAudioService.Stub.Proxy(obj);
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
case TRANSACTION_adjustSuggestedStreamVolume:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
java.lang.String _arg3;
_arg3 = data.readString();
this.adjustSuggestedStreamVolume(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_adjustStreamVolume:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
java.lang.String _arg3;
_arg3 = data.readString();
this.adjustStreamVolume(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_adjustMasterVolume:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
java.lang.String _arg2;
_arg2 = data.readString();
this.adjustMasterVolume(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_setStreamVolume:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
java.lang.String _arg3;
_arg3 = data.readString();
this.setStreamVolume(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_setRemoteStreamVolume:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
this.setRemoteStreamVolume(_arg0);
return true;
}
case TRANSACTION_setMasterVolume:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
java.lang.String _arg2;
_arg2 = data.readString();
this.setMasterVolume(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_setStreamSolo:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
boolean _arg1;
_arg1 = (0!=data.readInt());
android.os.IBinder _arg2;
_arg2 = data.readStrongBinder();
this.setStreamSolo(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_setStreamMute:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
boolean _arg1;
_arg1 = (0!=data.readInt());
android.os.IBinder _arg2;
_arg2 = data.readStrongBinder();
this.setStreamMute(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_isStreamMute:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
boolean _result = this.isStreamMute(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_forceRemoteSubmixFullVolume:
{
data.enforceInterface(descriptor);
boolean _arg0;
_arg0 = (0!=data.readInt());
android.os.IBinder _arg1;
_arg1 = data.readStrongBinder();
this.forceRemoteSubmixFullVolume(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_setMasterMute:
{
data.enforceInterface(descriptor);
boolean _arg0;
_arg0 = (0!=data.readInt());
int _arg1;
_arg1 = data.readInt();
java.lang.String _arg2;
_arg2 = data.readString();
android.os.IBinder _arg3;
_arg3 = data.readStrongBinder();
this.setMasterMute(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_isMasterMute:
{
data.enforceInterface(descriptor);
boolean _result = this.isMasterMute();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getStreamVolume:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _result = this.getStreamVolume(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getMasterVolume:
{
data.enforceInterface(descriptor);
int _result = this.getMasterVolume();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getStreamMaxVolume:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _result = this.getStreamMaxVolume(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getMasterMaxVolume:
{
data.enforceInterface(descriptor);
int _result = this.getMasterMaxVolume();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getLastAudibleStreamVolume:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _result = this.getLastAudibleStreamVolume(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getLastAudibleMasterVolume:
{
data.enforceInterface(descriptor);
int _result = this.getLastAudibleMasterVolume();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_setMicrophoneMute:
{
data.enforceInterface(descriptor);
boolean _arg0;
_arg0 = (0!=data.readInt());
java.lang.String _arg1;
_arg1 = data.readString();
this.setMicrophoneMute(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_setRingerMode:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
boolean _arg1;
_arg1 = (0!=data.readInt());
this.setRingerMode(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_getRingerMode:
{
data.enforceInterface(descriptor);
int _result = this.getRingerMode();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_setVibrateSetting:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
this.setVibrateSetting(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_getVibrateSetting:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _result = this.getVibrateSetting(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_shouldVibrate:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
boolean _result = this.shouldVibrate(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_setMode:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
android.os.IBinder _arg1;
_arg1 = data.readStrongBinder();
this.setMode(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_getMode:
{
data.enforceInterface(descriptor);
int _result = this.getMode();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_playSoundEffect:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
this.playSoundEffect(_arg0);
return true;
}
case TRANSACTION_playSoundEffectVolume:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
float _arg1;
_arg1 = data.readFloat();
this.playSoundEffectVolume(_arg0, _arg1);
return true;
}
case TRANSACTION_loadSoundEffects:
{
data.enforceInterface(descriptor);
boolean _result = this.loadSoundEffects();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_unloadSoundEffects:
{
data.enforceInterface(descriptor);
this.unloadSoundEffects();
return true;
}
case TRANSACTION_reloadAudioSettings:
{
data.enforceInterface(descriptor);
this.reloadAudioSettings();
return true;
}
case TRANSACTION_avrcpSupportsAbsoluteVolume:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _arg1;
_arg1 = (0!=data.readInt());
this.avrcpSupportsAbsoluteVolume(_arg0, _arg1);
return true;
}
case TRANSACTION_setSpeakerphoneOn:
{
data.enforceInterface(descriptor);
boolean _arg0;
_arg0 = (0!=data.readInt());
this.setSpeakerphoneOn(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_isSpeakerphoneOn:
{
data.enforceInterface(descriptor);
boolean _result = this.isSpeakerphoneOn();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_setBluetoothScoOn:
{
data.enforceInterface(descriptor);
boolean _arg0;
_arg0 = (0!=data.readInt());
this.setBluetoothScoOn(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_isBluetoothScoOn:
{
data.enforceInterface(descriptor);
boolean _result = this.isBluetoothScoOn();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_setBluetoothA2dpOn:
{
data.enforceInterface(descriptor);
boolean _arg0;
_arg0 = (0!=data.readInt());
this.setBluetoothA2dpOn(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_isBluetoothA2dpOn:
{
data.enforceInterface(descriptor);
boolean _result = this.isBluetoothA2dpOn();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_requestAudioFocus:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
android.os.IBinder _arg2;
_arg2 = data.readStrongBinder();
android.media.IAudioFocusDispatcher _arg3;
_arg3 = android.media.IAudioFocusDispatcher.Stub.asInterface(data.readStrongBinder());
java.lang.String _arg4;
_arg4 = data.readString();
java.lang.String _arg5;
_arg5 = data.readString();
int _result = this.requestAudioFocus(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_abandonAudioFocus:
{
data.enforceInterface(descriptor);
android.media.IAudioFocusDispatcher _arg0;
_arg0 = android.media.IAudioFocusDispatcher.Stub.asInterface(data.readStrongBinder());
java.lang.String _arg1;
_arg1 = data.readString();
int _result = this.abandonAudioFocus(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_unregisterAudioFocusClient:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
this.unregisterAudioFocusClient(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getCurrentAudioFocus:
{
data.enforceInterface(descriptor);
int _result = this.getCurrentAudioFocus();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_registerRemoteControlDisplay:
{
data.enforceInterface(descriptor);
android.media.IRemoteControlDisplay _arg0;
_arg0 = android.media.IRemoteControlDisplay.Stub.asInterface(data.readStrongBinder());
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
boolean _result = this.registerRemoteControlDisplay(_arg0, _arg1, _arg2);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_registerRemoteController:
{
data.enforceInterface(descriptor);
android.media.IRemoteControlDisplay _arg0;
_arg0 = android.media.IRemoteControlDisplay.Stub.asInterface(data.readStrongBinder());
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
android.content.ComponentName _arg3;
if ((0!=data.readInt())) {
_arg3 = android.content.ComponentName.CREATOR.createFromParcel(data);
}
else {
_arg3 = null;
}
boolean _result = this.registerRemoteController(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_unregisterRemoteControlDisplay:
{
data.enforceInterface(descriptor);
android.media.IRemoteControlDisplay _arg0;
_arg0 = android.media.IRemoteControlDisplay.Stub.asInterface(data.readStrongBinder());
this.unregisterRemoteControlDisplay(_arg0);
return true;
}
case TRANSACTION_remoteControlDisplayUsesBitmapSize:
{
data.enforceInterface(descriptor);
android.media.IRemoteControlDisplay _arg0;
_arg0 = android.media.IRemoteControlDisplay.Stub.asInterface(data.readStrongBinder());
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
this.remoteControlDisplayUsesBitmapSize(_arg0, _arg1, _arg2);
return true;
}
case TRANSACTION_remoteControlDisplayWantsPlaybackPositionSync:
{
data.enforceInterface(descriptor);
android.media.IRemoteControlDisplay _arg0;
_arg0 = android.media.IRemoteControlDisplay.Stub.asInterface(data.readStrongBinder());
boolean _arg1;
_arg1 = (0!=data.readInt());
this.remoteControlDisplayWantsPlaybackPositionSync(_arg0, _arg1);
return true;
}
case TRANSACTION_startBluetoothSco:
{
data.enforceInterface(descriptor);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
int _arg1;
_arg1 = data.readInt();
this.startBluetoothSco(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_startBluetoothScoVirtualCall:
{
data.enforceInterface(descriptor);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
this.startBluetoothScoVirtualCall(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_stopBluetoothSco:
{
data.enforceInterface(descriptor);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
this.stopBluetoothSco(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_forceVolumeControlStream:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
android.os.IBinder _arg1;
_arg1 = data.readStrongBinder();
this.forceVolumeControlStream(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_setRingtonePlayer:
{
data.enforceInterface(descriptor);
android.media.IRingtonePlayer _arg0;
_arg0 = android.media.IRingtonePlayer.Stub.asInterface(data.readStrongBinder());
this.setRingtonePlayer(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getRingtonePlayer:
{
data.enforceInterface(descriptor);
android.media.IRingtonePlayer _result = this.getRingtonePlayer();
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_getMasterStreamType:
{
data.enforceInterface(descriptor);
int _result = this.getMasterStreamType();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_setWiredDeviceConnectionState:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
java.lang.String _arg2;
_arg2 = data.readString();
this.setWiredDeviceConnectionState(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_setBluetoothA2dpDeviceConnectionState:
{
data.enforceInterface(descriptor);
android.bluetooth.BluetoothDevice _arg0;
if ((0!=data.readInt())) {
_arg0 = android.bluetooth.BluetoothDevice.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
int _result = this.setBluetoothA2dpDeviceConnectionState(_arg0, _arg1, _arg2);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_startWatchingRoutes:
{
data.enforceInterface(descriptor);
android.media.IAudioRoutesObserver _arg0;
_arg0 = android.media.IAudioRoutesObserver.Stub.asInterface(data.readStrongBinder());
android.media.AudioRoutesInfo _result = this.startWatchingRoutes(_arg0);
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
case TRANSACTION_isCameraSoundForced:
{
data.enforceInterface(descriptor);
boolean _result = this.isCameraSoundForced();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_setVolumeController:
{
data.enforceInterface(descriptor);
android.media.IVolumeController _arg0;
_arg0 = android.media.IVolumeController.Stub.asInterface(data.readStrongBinder());
this.setVolumeController(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_notifyVolumeControllerVisible:
{
data.enforceInterface(descriptor);
android.media.IVolumeController _arg0;
_arg0 = android.media.IVolumeController.Stub.asInterface(data.readStrongBinder());
boolean _arg1;
_arg1 = (0!=data.readInt());
this.notifyVolumeControllerVisible(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_isStreamAffectedByRingerMode:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
boolean _result = this.isStreamAffectedByRingerMode(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_disableSafeMediaVolume:
{
data.enforceInterface(descriptor);
this.disableSafeMediaVolume();
reply.writeNoException();
return true;
}
case TRANSACTION_setHdmiSystemAudioSupported:
{
data.enforceInterface(descriptor);
boolean _arg0;
_arg0 = (0!=data.readInt());
int _result = this.setHdmiSystemAudioSupported(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_isHdmiSystemAudioSupported:
{
data.enforceInterface(descriptor);
boolean _result = this.isHdmiSystemAudioSupported();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_registerAudioPolicy:
{
data.enforceInterface(descriptor);
android.media.audiopolicy.AudioPolicyConfig _arg0;
if ((0!=data.readInt())) {
_arg0 = android.media.audiopolicy.AudioPolicyConfig.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
android.os.IBinder _arg1;
_arg1 = data.readStrongBinder();
boolean _result = this.registerAudioPolicy(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_unregisterAudioPolicyAsync:
{
data.enforceInterface(descriptor);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
this.unregisterAudioPolicyAsync(_arg0);
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements android.media.IAudioService
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
@Override public void adjustSuggestedStreamVolume(int direction, int suggestedStreamType, int flags, java.lang.String callingPackage) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(direction);
_data.writeInt(suggestedStreamType);
_data.writeInt(flags);
_data.writeString(callingPackage);
mRemote.transact(Stub.TRANSACTION_adjustSuggestedStreamVolume, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void adjustStreamVolume(int streamType, int direction, int flags, java.lang.String callingPackage) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(streamType);
_data.writeInt(direction);
_data.writeInt(flags);
_data.writeString(callingPackage);
mRemote.transact(Stub.TRANSACTION_adjustStreamVolume, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void adjustMasterVolume(int direction, int flags, java.lang.String callingPackage) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(direction);
_data.writeInt(flags);
_data.writeString(callingPackage);
mRemote.transact(Stub.TRANSACTION_adjustMasterVolume, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void setStreamVolume(int streamType, int index, int flags, java.lang.String callingPackage) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(streamType);
_data.writeInt(index);
_data.writeInt(flags);
_data.writeString(callingPackage);
mRemote.transact(Stub.TRANSACTION_setStreamVolume, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void setRemoteStreamVolume(int index) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(index);
mRemote.transact(Stub.TRANSACTION_setRemoteStreamVolume, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void setMasterVolume(int index, int flags, java.lang.String callingPackage) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(index);
_data.writeInt(flags);
_data.writeString(callingPackage);
mRemote.transact(Stub.TRANSACTION_setMasterVolume, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void setStreamSolo(int streamType, boolean state, android.os.IBinder cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(streamType);
_data.writeInt(((state)?(1):(0)));
_data.writeStrongBinder(cb);
mRemote.transact(Stub.TRANSACTION_setStreamSolo, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void setStreamMute(int streamType, boolean state, android.os.IBinder cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(streamType);
_data.writeInt(((state)?(1):(0)));
_data.writeStrongBinder(cb);
mRemote.transact(Stub.TRANSACTION_setStreamMute, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public boolean isStreamMute(int streamType) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(streamType);
mRemote.transact(Stub.TRANSACTION_isStreamMute, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void forceRemoteSubmixFullVolume(boolean startForcing, android.os.IBinder cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((startForcing)?(1):(0)));
_data.writeStrongBinder(cb);
mRemote.transact(Stub.TRANSACTION_forceRemoteSubmixFullVolume, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void setMasterMute(boolean state, int flags, java.lang.String callingPackage, android.os.IBinder cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((state)?(1):(0)));
_data.writeInt(flags);
_data.writeString(callingPackage);
_data.writeStrongBinder(cb);
mRemote.transact(Stub.TRANSACTION_setMasterMute, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public boolean isMasterMute() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isMasterMute, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getStreamVolume(int streamType) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(streamType);
mRemote.transact(Stub.TRANSACTION_getStreamVolume, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getMasterVolume() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getMasterVolume, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getStreamMaxVolume(int streamType) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(streamType);
mRemote.transact(Stub.TRANSACTION_getStreamMaxVolume, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getMasterMaxVolume() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getMasterMaxVolume, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getLastAudibleStreamVolume(int streamType) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(streamType);
mRemote.transact(Stub.TRANSACTION_getLastAudibleStreamVolume, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getLastAudibleMasterVolume() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getLastAudibleMasterVolume, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setMicrophoneMute(boolean on, java.lang.String callingPackage) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((on)?(1):(0)));
_data.writeString(callingPackage);
mRemote.transact(Stub.TRANSACTION_setMicrophoneMute, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void setRingerMode(int ringerMode, boolean checkZen) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(ringerMode);
_data.writeInt(((checkZen)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setRingerMode, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public int getRingerMode() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getRingerMode, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setVibrateSetting(int vibrateType, int vibrateSetting) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(vibrateType);
_data.writeInt(vibrateSetting);
mRemote.transact(Stub.TRANSACTION_setVibrateSetting, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public int getVibrateSetting(int vibrateType) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(vibrateType);
mRemote.transact(Stub.TRANSACTION_getVibrateSetting, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean shouldVibrate(int vibrateType) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(vibrateType);
mRemote.transact(Stub.TRANSACTION_shouldVibrate, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setMode(int mode, android.os.IBinder cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(mode);
_data.writeStrongBinder(cb);
mRemote.transact(Stub.TRANSACTION_setMode, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public int getMode() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getMode, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void playSoundEffect(int effectType) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(effectType);
mRemote.transact(Stub.TRANSACTION_playSoundEffect, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void playSoundEffectVolume(int effectType, float volume) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(effectType);
_data.writeFloat(volume);
mRemote.transact(Stub.TRANSACTION_playSoundEffectVolume, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public boolean loadSoundEffects() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_loadSoundEffects, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void unloadSoundEffects() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_unloadSoundEffects, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void reloadAudioSettings() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_reloadAudioSettings, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void avrcpSupportsAbsoluteVolume(java.lang.String address, boolean support) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(address);
_data.writeInt(((support)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_avrcpSupportsAbsoluteVolume, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void setSpeakerphoneOn(boolean on) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((on)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setSpeakerphoneOn, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public boolean isSpeakerphoneOn() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isSpeakerphoneOn, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setBluetoothScoOn(boolean on) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((on)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setBluetoothScoOn, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public boolean isBluetoothScoOn() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isBluetoothScoOn, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setBluetoothA2dpOn(boolean on) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((on)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setBluetoothA2dpOn, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public boolean isBluetoothA2dpOn() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isBluetoothA2dpOn, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int requestAudioFocus(int mainStreamType, int durationHint, android.os.IBinder cb, android.media.IAudioFocusDispatcher fd, java.lang.String clientId, java.lang.String callingPackageName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(mainStreamType);
_data.writeInt(durationHint);
_data.writeStrongBinder(cb);
_data.writeStrongBinder((((fd!=null))?(fd.asBinder()):(null)));
_data.writeString(clientId);
_data.writeString(callingPackageName);
mRemote.transact(Stub.TRANSACTION_requestAudioFocus, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int abandonAudioFocus(android.media.IAudioFocusDispatcher fd, java.lang.String clientId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((fd!=null))?(fd.asBinder()):(null)));
_data.writeString(clientId);
mRemote.transact(Stub.TRANSACTION_abandonAudioFocus, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void unregisterAudioFocusClient(java.lang.String clientId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(clientId);
mRemote.transact(Stub.TRANSACTION_unregisterAudioFocusClient, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public int getCurrentAudioFocus() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getCurrentAudioFocus, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * Register an IRemoteControlDisplay.
     * Success of registration is subject to a check on
     *   the android.Manifest.permission.MEDIA_CONTENT_CONTROL permission.
     * Notify all IRemoteControlClient of the new display and cause the RemoteControlClient
     * at the top of the stack to update the new display with its information.
     * @param rcd the IRemoteControlDisplay to register. No effect if null.
     * @param w the maximum width of the expected bitmap. Negative or zero values indicate this
     *   display doesn't need to receive artwork.
     * @param h the maximum height of the expected bitmap. Negative or zero values indicate this
     *   display doesn't need to receive artwork.
     */
@Override public boolean registerRemoteControlDisplay(android.media.IRemoteControlDisplay rcd, int w, int h) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((rcd!=null))?(rcd.asBinder()):(null)));
_data.writeInt(w);
_data.writeInt(h);
mRemote.transact(Stub.TRANSACTION_registerRemoteControlDisplay, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * Like registerRemoteControlDisplay, but with success being subject to a check on
     *   the android.Manifest.permission.MEDIA_CONTENT_CONTROL permission, and if it fails,
     *   success is subject to listenerComp being one of the ENABLED_NOTIFICATION_LISTENERS
     *   components.
     */
@Override public boolean registerRemoteController(android.media.IRemoteControlDisplay rcd, int w, int h, android.content.ComponentName listenerComp) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((rcd!=null))?(rcd.asBinder()):(null)));
_data.writeInt(w);
_data.writeInt(h);
if ((listenerComp!=null)) {
_data.writeInt(1);
listenerComp.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_registerRemoteController, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * Unregister an IRemoteControlDisplay.
     * No effect if the IRemoteControlDisplay hasn't been successfully registered.
     * @param rcd the IRemoteControlDisplay to unregister. No effect if null.
     */
@Override public void unregisterRemoteControlDisplay(android.media.IRemoteControlDisplay rcd) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((rcd!=null))?(rcd.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterRemoteControlDisplay, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
/**
     * Update the size of the artwork used by an IRemoteControlDisplay.
     * @param rcd the IRemoteControlDisplay with the new artwork size requirement
     * @param w the maximum width of the expected bitmap. Negative or zero values indicate this
     *   display doesn't need to receive artwork.
     * @param h the maximum height of the expected bitmap. Negative or zero values indicate this
     *   display doesn't need to receive artwork.
     */
@Override public void remoteControlDisplayUsesBitmapSize(android.media.IRemoteControlDisplay rcd, int w, int h) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((rcd!=null))?(rcd.asBinder()):(null)));
_data.writeInt(w);
_data.writeInt(h);
mRemote.transact(Stub.TRANSACTION_remoteControlDisplayUsesBitmapSize, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
/**
     * Controls whether a remote control display needs periodic checks of the RemoteControlClient
     * playback position to verify that the estimated position has not drifted from the actual
     * position. By default the check is not performed.
     * The IRemoteControlDisplay must have been previously registered for this to have any effect.
     * @param rcd the IRemoteControlDisplay for which the anti-drift mechanism will be enabled
     *     or disabled. Not null.
     * @param wantsSync if true, RemoteControlClient instances which expose their playback position
     *     to the framework will regularly compare the estimated playback position with the actual
     *     position, and will update the IRemoteControlDisplay implementation whenever a drift is
     *     detected.
     */
@Override public void remoteControlDisplayWantsPlaybackPositionSync(android.media.IRemoteControlDisplay rcd, boolean wantsSync) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((rcd!=null))?(rcd.asBinder()):(null)));
_data.writeInt(((wantsSync)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_remoteControlDisplayWantsPlaybackPositionSync, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void startBluetoothSco(android.os.IBinder cb, int targetSdkVersion) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(cb);
_data.writeInt(targetSdkVersion);
mRemote.transact(Stub.TRANSACTION_startBluetoothSco, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void startBluetoothScoVirtualCall(android.os.IBinder cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(cb);
mRemote.transact(Stub.TRANSACTION_startBluetoothScoVirtualCall, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void stopBluetoothSco(android.os.IBinder cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(cb);
mRemote.transact(Stub.TRANSACTION_stopBluetoothSco, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void forceVolumeControlStream(int streamType, android.os.IBinder cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(streamType);
_data.writeStrongBinder(cb);
mRemote.transact(Stub.TRANSACTION_forceVolumeControlStream, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void setRingtonePlayer(android.media.IRingtonePlayer player) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((player!=null))?(player.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_setRingtonePlayer, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public android.media.IRingtonePlayer getRingtonePlayer() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.media.IRingtonePlayer _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getRingtonePlayer, _data, _reply, 0);
_reply.readException();
_result = android.media.IRingtonePlayer.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getMasterStreamType() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getMasterStreamType, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setWiredDeviceConnectionState(int device, int state, java.lang.String name) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(device);
_data.writeInt(state);
_data.writeString(name);
mRemote.transact(Stub.TRANSACTION_setWiredDeviceConnectionState, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public int setBluetoothA2dpDeviceConnectionState(android.bluetooth.BluetoothDevice device, int state, int profile) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((device!=null)) {
_data.writeInt(1);
device.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeInt(state);
_data.writeInt(profile);
mRemote.transact(Stub.TRANSACTION_setBluetoothA2dpDeviceConnectionState, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public android.media.AudioRoutesInfo startWatchingRoutes(android.media.IAudioRoutesObserver observer) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.media.AudioRoutesInfo _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((observer!=null))?(observer.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_startWatchingRoutes, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = android.media.AudioRoutesInfo.CREATOR.createFromParcel(_reply);
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
@Override public boolean isCameraSoundForced() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isCameraSoundForced, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setVolumeController(android.media.IVolumeController controller) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((controller!=null))?(controller.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_setVolumeController, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void notifyVolumeControllerVisible(android.media.IVolumeController controller, boolean visible) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((controller!=null))?(controller.asBinder()):(null)));
_data.writeInt(((visible)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_notifyVolumeControllerVisible, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public boolean isStreamAffectedByRingerMode(int streamType) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(streamType);
mRemote.transact(Stub.TRANSACTION_isStreamAffectedByRingerMode, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void disableSafeMediaVolume() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_disableSafeMediaVolume, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public int setHdmiSystemAudioSupported(boolean on) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((on)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setHdmiSystemAudioSupported, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean isHdmiSystemAudioSupported() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isHdmiSystemAudioSupported, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean registerAudioPolicy(android.media.audiopolicy.AudioPolicyConfig policyConfig, android.os.IBinder cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((policyConfig!=null)) {
_data.writeInt(1);
policyConfig.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeStrongBinder(cb);
mRemote.transact(Stub.TRANSACTION_registerAudioPolicy, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void unregisterAudioPolicyAsync(android.os.IBinder cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(cb);
mRemote.transact(Stub.TRANSACTION_unregisterAudioPolicyAsync, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_adjustSuggestedStreamVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_adjustStreamVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_adjustMasterVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_setStreamVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_setRemoteStreamVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_setMasterVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_setStreamSolo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_setStreamMute = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_isStreamMute = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_forceRemoteSubmixFullVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_setMasterMute = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_isMasterMute = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_getStreamVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
static final int TRANSACTION_getMasterVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
static final int TRANSACTION_getStreamMaxVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
static final int TRANSACTION_getMasterMaxVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
static final int TRANSACTION_getLastAudibleStreamVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16);
static final int TRANSACTION_getLastAudibleMasterVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 17);
static final int TRANSACTION_setMicrophoneMute = (android.os.IBinder.FIRST_CALL_TRANSACTION + 18);
static final int TRANSACTION_setRingerMode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 19);
static final int TRANSACTION_getRingerMode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 20);
static final int TRANSACTION_setVibrateSetting = (android.os.IBinder.FIRST_CALL_TRANSACTION + 21);
static final int TRANSACTION_getVibrateSetting = (android.os.IBinder.FIRST_CALL_TRANSACTION + 22);
static final int TRANSACTION_shouldVibrate = (android.os.IBinder.FIRST_CALL_TRANSACTION + 23);
static final int TRANSACTION_setMode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 24);
static final int TRANSACTION_getMode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 25);
static final int TRANSACTION_playSoundEffect = (android.os.IBinder.FIRST_CALL_TRANSACTION + 26);
static final int TRANSACTION_playSoundEffectVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 27);
static final int TRANSACTION_loadSoundEffects = (android.os.IBinder.FIRST_CALL_TRANSACTION + 28);
static final int TRANSACTION_unloadSoundEffects = (android.os.IBinder.FIRST_CALL_TRANSACTION + 29);
static final int TRANSACTION_reloadAudioSettings = (android.os.IBinder.FIRST_CALL_TRANSACTION + 30);
static final int TRANSACTION_avrcpSupportsAbsoluteVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 31);
static final int TRANSACTION_setSpeakerphoneOn = (android.os.IBinder.FIRST_CALL_TRANSACTION + 32);
static final int TRANSACTION_isSpeakerphoneOn = (android.os.IBinder.FIRST_CALL_TRANSACTION + 33);
static final int TRANSACTION_setBluetoothScoOn = (android.os.IBinder.FIRST_CALL_TRANSACTION + 34);
static final int TRANSACTION_isBluetoothScoOn = (android.os.IBinder.FIRST_CALL_TRANSACTION + 35);
static final int TRANSACTION_setBluetoothA2dpOn = (android.os.IBinder.FIRST_CALL_TRANSACTION + 36);
static final int TRANSACTION_isBluetoothA2dpOn = (android.os.IBinder.FIRST_CALL_TRANSACTION + 37);
static final int TRANSACTION_requestAudioFocus = (android.os.IBinder.FIRST_CALL_TRANSACTION + 38);
static final int TRANSACTION_abandonAudioFocus = (android.os.IBinder.FIRST_CALL_TRANSACTION + 39);
static final int TRANSACTION_unregisterAudioFocusClient = (android.os.IBinder.FIRST_CALL_TRANSACTION + 40);
static final int TRANSACTION_getCurrentAudioFocus = (android.os.IBinder.FIRST_CALL_TRANSACTION + 41);
static final int TRANSACTION_registerRemoteControlDisplay = (android.os.IBinder.FIRST_CALL_TRANSACTION + 42);
static final int TRANSACTION_registerRemoteController = (android.os.IBinder.FIRST_CALL_TRANSACTION + 43);
static final int TRANSACTION_unregisterRemoteControlDisplay = (android.os.IBinder.FIRST_CALL_TRANSACTION + 44);
static final int TRANSACTION_remoteControlDisplayUsesBitmapSize = (android.os.IBinder.FIRST_CALL_TRANSACTION + 45);
static final int TRANSACTION_remoteControlDisplayWantsPlaybackPositionSync = (android.os.IBinder.FIRST_CALL_TRANSACTION + 46);
static final int TRANSACTION_startBluetoothSco = (android.os.IBinder.FIRST_CALL_TRANSACTION + 47);
static final int TRANSACTION_startBluetoothScoVirtualCall = (android.os.IBinder.FIRST_CALL_TRANSACTION + 48);
static final int TRANSACTION_stopBluetoothSco = (android.os.IBinder.FIRST_CALL_TRANSACTION + 49);
static final int TRANSACTION_forceVolumeControlStream = (android.os.IBinder.FIRST_CALL_TRANSACTION + 50);
static final int TRANSACTION_setRingtonePlayer = (android.os.IBinder.FIRST_CALL_TRANSACTION + 51);
static final int TRANSACTION_getRingtonePlayer = (android.os.IBinder.FIRST_CALL_TRANSACTION + 52);
static final int TRANSACTION_getMasterStreamType = (android.os.IBinder.FIRST_CALL_TRANSACTION + 53);
static final int TRANSACTION_setWiredDeviceConnectionState = (android.os.IBinder.FIRST_CALL_TRANSACTION + 54);
static final int TRANSACTION_setBluetoothA2dpDeviceConnectionState = (android.os.IBinder.FIRST_CALL_TRANSACTION + 55);
static final int TRANSACTION_startWatchingRoutes = (android.os.IBinder.FIRST_CALL_TRANSACTION + 56);
static final int TRANSACTION_isCameraSoundForced = (android.os.IBinder.FIRST_CALL_TRANSACTION + 57);
static final int TRANSACTION_setVolumeController = (android.os.IBinder.FIRST_CALL_TRANSACTION + 58);
static final int TRANSACTION_notifyVolumeControllerVisible = (android.os.IBinder.FIRST_CALL_TRANSACTION + 59);
static final int TRANSACTION_isStreamAffectedByRingerMode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 60);
static final int TRANSACTION_disableSafeMediaVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 61);
static final int TRANSACTION_setHdmiSystemAudioSupported = (android.os.IBinder.FIRST_CALL_TRANSACTION + 62);
static final int TRANSACTION_isHdmiSystemAudioSupported = (android.os.IBinder.FIRST_CALL_TRANSACTION + 63);
static final int TRANSACTION_registerAudioPolicy = (android.os.IBinder.FIRST_CALL_TRANSACTION + 64);
static final int TRANSACTION_unregisterAudioPolicyAsync = (android.os.IBinder.FIRST_CALL_TRANSACTION + 65);
}
public void adjustSuggestedStreamVolume(int direction, int suggestedStreamType, int flags, java.lang.String callingPackage) throws android.os.RemoteException;
public void adjustStreamVolume(int streamType, int direction, int flags, java.lang.String callingPackage) throws android.os.RemoteException;
public void adjustMasterVolume(int direction, int flags, java.lang.String callingPackage) throws android.os.RemoteException;
public void setStreamVolume(int streamType, int index, int flags, java.lang.String callingPackage) throws android.os.RemoteException;
public void setRemoteStreamVolume(int index) throws android.os.RemoteException;
public void setMasterVolume(int index, int flags, java.lang.String callingPackage) throws android.os.RemoteException;
public void setStreamSolo(int streamType, boolean state, android.os.IBinder cb) throws android.os.RemoteException;
public void setStreamMute(int streamType, boolean state, android.os.IBinder cb) throws android.os.RemoteException;
public boolean isStreamMute(int streamType) throws android.os.RemoteException;
public void forceRemoteSubmixFullVolume(boolean startForcing, android.os.IBinder cb) throws android.os.RemoteException;
public void setMasterMute(boolean state, int flags, java.lang.String callingPackage, android.os.IBinder cb) throws android.os.RemoteException;
public boolean isMasterMute() throws android.os.RemoteException;
public int getStreamVolume(int streamType) throws android.os.RemoteException;
public int getMasterVolume() throws android.os.RemoteException;
public int getStreamMaxVolume(int streamType) throws android.os.RemoteException;
public int getMasterMaxVolume() throws android.os.RemoteException;
public int getLastAudibleStreamVolume(int streamType) throws android.os.RemoteException;
public int getLastAudibleMasterVolume() throws android.os.RemoteException;
public void setMicrophoneMute(boolean on, java.lang.String callingPackage) throws android.os.RemoteException;
public void setRingerMode(int ringerMode, boolean checkZen) throws android.os.RemoteException;
public int getRingerMode() throws android.os.RemoteException;
public void setVibrateSetting(int vibrateType, int vibrateSetting) throws android.os.RemoteException;
public int getVibrateSetting(int vibrateType) throws android.os.RemoteException;
public boolean shouldVibrate(int vibrateType) throws android.os.RemoteException;
public void setMode(int mode, android.os.IBinder cb) throws android.os.RemoteException;
public int getMode() throws android.os.RemoteException;
public void playSoundEffect(int effectType) throws android.os.RemoteException;
public void playSoundEffectVolume(int effectType, float volume) throws android.os.RemoteException;
public boolean loadSoundEffects() throws android.os.RemoteException;
public void unloadSoundEffects() throws android.os.RemoteException;
public void reloadAudioSettings() throws android.os.RemoteException;
public void avrcpSupportsAbsoluteVolume(java.lang.String address, boolean support) throws android.os.RemoteException;
public void setSpeakerphoneOn(boolean on) throws android.os.RemoteException;
public boolean isSpeakerphoneOn() throws android.os.RemoteException;
public void setBluetoothScoOn(boolean on) throws android.os.RemoteException;
public boolean isBluetoothScoOn() throws android.os.RemoteException;
public void setBluetoothA2dpOn(boolean on) throws android.os.RemoteException;
public boolean isBluetoothA2dpOn() throws android.os.RemoteException;
public int requestAudioFocus(int mainStreamType, int durationHint, android.os.IBinder cb, android.media.IAudioFocusDispatcher fd, java.lang.String clientId, java.lang.String callingPackageName) throws android.os.RemoteException;
public int abandonAudioFocus(android.media.IAudioFocusDispatcher fd, java.lang.String clientId) throws android.os.RemoteException;
public void unregisterAudioFocusClient(java.lang.String clientId) throws android.os.RemoteException;
public int getCurrentAudioFocus() throws android.os.RemoteException;
/**
     * Register an IRemoteControlDisplay.
     * Success of registration is subject to a check on
     *   the android.Manifest.permission.MEDIA_CONTENT_CONTROL permission.
     * Notify all IRemoteControlClient of the new display and cause the RemoteControlClient
     * at the top of the stack to update the new display with its information.
     * @param rcd the IRemoteControlDisplay to register. No effect if null.
     * @param w the maximum width of the expected bitmap. Negative or zero values indicate this
     *   display doesn't need to receive artwork.
     * @param h the maximum height of the expected bitmap. Negative or zero values indicate this
     *   display doesn't need to receive artwork.
     */
public boolean registerRemoteControlDisplay(android.media.IRemoteControlDisplay rcd, int w, int h) throws android.os.RemoteException;
/**
     * Like registerRemoteControlDisplay, but with success being subject to a check on
     *   the android.Manifest.permission.MEDIA_CONTENT_CONTROL permission, and if it fails,
     *   success is subject to listenerComp being one of the ENABLED_NOTIFICATION_LISTENERS
     *   components.
     */
public boolean registerRemoteController(android.media.IRemoteControlDisplay rcd, int w, int h, android.content.ComponentName listenerComp) throws android.os.RemoteException;
/**
     * Unregister an IRemoteControlDisplay.
     * No effect if the IRemoteControlDisplay hasn't been successfully registered.
     * @param rcd the IRemoteControlDisplay to unregister. No effect if null.
     */
public void unregisterRemoteControlDisplay(android.media.IRemoteControlDisplay rcd) throws android.os.RemoteException;
/**
     * Update the size of the artwork used by an IRemoteControlDisplay.
     * @param rcd the IRemoteControlDisplay with the new artwork size requirement
     * @param w the maximum width of the expected bitmap. Negative or zero values indicate this
     *   display doesn't need to receive artwork.
     * @param h the maximum height of the expected bitmap. Negative or zero values indicate this
     *   display doesn't need to receive artwork.
     */
public void remoteControlDisplayUsesBitmapSize(android.media.IRemoteControlDisplay rcd, int w, int h) throws android.os.RemoteException;
/**
     * Controls whether a remote control display needs periodic checks of the RemoteControlClient
     * playback position to verify that the estimated position has not drifted from the actual
     * position. By default the check is not performed.
     * The IRemoteControlDisplay must have been previously registered for this to have any effect.
     * @param rcd the IRemoteControlDisplay for which the anti-drift mechanism will be enabled
     *     or disabled. Not null.
     * @param wantsSync if true, RemoteControlClient instances which expose their playback position
     *     to the framework will regularly compare the estimated playback position with the actual
     *     position, and will update the IRemoteControlDisplay implementation whenever a drift is
     *     detected.
     */
public void remoteControlDisplayWantsPlaybackPositionSync(android.media.IRemoteControlDisplay rcd, boolean wantsSync) throws android.os.RemoteException;
public void startBluetoothSco(android.os.IBinder cb, int targetSdkVersion) throws android.os.RemoteException;
public void startBluetoothScoVirtualCall(android.os.IBinder cb) throws android.os.RemoteException;
public void stopBluetoothSco(android.os.IBinder cb) throws android.os.RemoteException;
public void forceVolumeControlStream(int streamType, android.os.IBinder cb) throws android.os.RemoteException;
public void setRingtonePlayer(android.media.IRingtonePlayer player) throws android.os.RemoteException;
public android.media.IRingtonePlayer getRingtonePlayer() throws android.os.RemoteException;
public int getMasterStreamType() throws android.os.RemoteException;
public void setWiredDeviceConnectionState(int device, int state, java.lang.String name) throws android.os.RemoteException;
public int setBluetoothA2dpDeviceConnectionState(android.bluetooth.BluetoothDevice device, int state, int profile) throws android.os.RemoteException;
public android.media.AudioRoutesInfo startWatchingRoutes(android.media.IAudioRoutesObserver observer) throws android.os.RemoteException;
public boolean isCameraSoundForced() throws android.os.RemoteException;
public void setVolumeController(android.media.IVolumeController controller) throws android.os.RemoteException;
public void notifyVolumeControllerVisible(android.media.IVolumeController controller, boolean visible) throws android.os.RemoteException;
public boolean isStreamAffectedByRingerMode(int streamType) throws android.os.RemoteException;
public void disableSafeMediaVolume() throws android.os.RemoteException;
public int setHdmiSystemAudioSupported(boolean on) throws android.os.RemoteException;
public boolean isHdmiSystemAudioSupported() throws android.os.RemoteException;
public boolean registerAudioPolicy(android.media.audiopolicy.AudioPolicyConfig policyConfig, android.os.IBinder cb) throws android.os.RemoteException;
public void unregisterAudioPolicyAsync(android.os.IBinder cb) throws android.os.RemoteException;
}
