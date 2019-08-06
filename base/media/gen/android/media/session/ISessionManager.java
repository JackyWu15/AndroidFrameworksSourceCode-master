/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: K:\\AndroidFrameworksSourceCode-master\\base\\media\\java\\android\\media\\session\\ISessionManager.aidl
 */
package android.media.session;
/**
 * Interface to the MediaSessionManagerService
 * @hide
 */
public interface ISessionManager extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.media.session.ISessionManager
{
private static final java.lang.String DESCRIPTOR = "android.media.session.ISessionManager";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.media.session.ISessionManager interface,
 * generating a proxy if needed.
 */
public static android.media.session.ISessionManager asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.media.session.ISessionManager))) {
return ((android.media.session.ISessionManager)iin);
}
return new android.media.session.ISessionManager.Stub.Proxy(obj);
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
case TRANSACTION_createSession:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
android.media.session.ISessionCallback _arg1;
_arg1 = android.media.session.ISessionCallback.Stub.asInterface(data.readStrongBinder());
java.lang.String _arg2;
_arg2 = data.readString();
int _arg3;
_arg3 = data.readInt();
android.media.session.ISession _result = this.createSession(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_getSessions:
{
data.enforceInterface(descriptor);
android.content.ComponentName _arg0;
if ((0!=data.readInt())) {
_arg0 = android.content.ComponentName.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
int _arg1;
_arg1 = data.readInt();
java.util.List<android.os.IBinder> _result = this.getSessions(_arg0, _arg1);
reply.writeNoException();
reply.writeBinderList(_result);
return true;
}
case TRANSACTION_dispatchMediaKeyEvent:
{
data.enforceInterface(descriptor);
android.view.KeyEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = android.view.KeyEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
boolean _arg1;
_arg1 = (0!=data.readInt());
this.dispatchMediaKeyEvent(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_dispatchAdjustVolume:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
this.dispatchAdjustVolume(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_addSessionsListener:
{
data.enforceInterface(descriptor);
android.media.session.IActiveSessionsListener _arg0;
_arg0 = android.media.session.IActiveSessionsListener.Stub.asInterface(data.readStrongBinder());
android.content.ComponentName _arg1;
if ((0!=data.readInt())) {
_arg1 = android.content.ComponentName.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
int _arg2;
_arg2 = data.readInt();
this.addSessionsListener(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_removeSessionsListener:
{
data.enforceInterface(descriptor);
android.media.session.IActiveSessionsListener _arg0;
_arg0 = android.media.session.IActiveSessionsListener.Stub.asInterface(data.readStrongBinder());
this.removeSessionsListener(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_setRemoteVolumeController:
{
data.enforceInterface(descriptor);
android.media.IRemoteVolumeController _arg0;
_arg0 = android.media.IRemoteVolumeController.Stub.asInterface(data.readStrongBinder());
this.setRemoteVolumeController(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_isGlobalPriorityActive:
{
data.enforceInterface(descriptor);
boolean _result = this.isGlobalPriorityActive();
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
private static class Proxy implements android.media.session.ISessionManager
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
@Override public android.media.session.ISession createSession(java.lang.String packageName, android.media.session.ISessionCallback cb, java.lang.String tag, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.media.session.ISession _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(packageName);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
_data.writeString(tag);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_createSession, _data, _reply, 0);
_reply.readException();
_result = android.media.session.ISession.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List<android.os.IBinder> getSessions(android.content.ComponentName compName, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<android.os.IBinder> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((compName!=null)) {
_data.writeInt(1);
compName.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_getSessions, _data, _reply, 0);
_reply.readException();
_result = _reply.createBinderArrayList();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void dispatchMediaKeyEvent(android.view.KeyEvent keyEvent, boolean needWakeLock) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((keyEvent!=null)) {
_data.writeInt(1);
keyEvent.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeInt(((needWakeLock)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_dispatchMediaKeyEvent, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void dispatchAdjustVolume(int suggestedStream, int delta, int flags) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(suggestedStream);
_data.writeInt(delta);
_data.writeInt(flags);
mRemote.transact(Stub.TRANSACTION_dispatchAdjustVolume, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void addSessionsListener(android.media.session.IActiveSessionsListener listener, android.content.ComponentName compName, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
if ((compName!=null)) {
_data.writeInt(1);
compName.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_addSessionsListener, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void removeSessionsListener(android.media.session.IActiveSessionsListener listener) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_removeSessionsListener, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// This is for the system volume UI only

@Override public void setRemoteVolumeController(android.media.IRemoteVolumeController rvc) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((rvc!=null))?(rvc.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_setRemoteVolumeController, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// For PhoneWindowManager to precheck media keys

@Override public boolean isGlobalPriorityActive() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isGlobalPriorityActive, _data, _reply, 0);
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
static final int TRANSACTION_createSession = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getSessions = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_dispatchMediaKeyEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_dispatchAdjustVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_addSessionsListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_removeSessionsListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_setRemoteVolumeController = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_isGlobalPriorityActive = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
}
public android.media.session.ISession createSession(java.lang.String packageName, android.media.session.ISessionCallback cb, java.lang.String tag, int userId) throws android.os.RemoteException;
public java.util.List<android.os.IBinder> getSessions(android.content.ComponentName compName, int userId) throws android.os.RemoteException;
public void dispatchMediaKeyEvent(android.view.KeyEvent keyEvent, boolean needWakeLock) throws android.os.RemoteException;
public void dispatchAdjustVolume(int suggestedStream, int delta, int flags) throws android.os.RemoteException;
public void addSessionsListener(android.media.session.IActiveSessionsListener listener, android.content.ComponentName compName, int userId) throws android.os.RemoteException;
public void removeSessionsListener(android.media.session.IActiveSessionsListener listener) throws android.os.RemoteException;
// This is for the system volume UI only

public void setRemoteVolumeController(android.media.IRemoteVolumeController rvc) throws android.os.RemoteException;
// For PhoneWindowManager to precheck media keys

public boolean isGlobalPriorityActive() throws android.os.RemoteException;
}
