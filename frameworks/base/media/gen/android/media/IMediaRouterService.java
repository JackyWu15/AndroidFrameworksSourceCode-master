/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: K:\\AndroidFrameworksSourceCode-master\\base\\media\\java\\android\\media\\IMediaRouterService.aidl
 */
package android.media;
/**
 * {@hide}
 */
public interface IMediaRouterService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.media.IMediaRouterService
{
private static final java.lang.String DESCRIPTOR = "android.media.IMediaRouterService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.media.IMediaRouterService interface,
 * generating a proxy if needed.
 */
public static android.media.IMediaRouterService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.media.IMediaRouterService))) {
return ((android.media.IMediaRouterService)iin);
}
return new android.media.IMediaRouterService.Stub.Proxy(obj);
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
case TRANSACTION_registerClientAsUser:
{
data.enforceInterface(descriptor);
android.media.IMediaRouterClient _arg0;
_arg0 = android.media.IMediaRouterClient.Stub.asInterface(data.readStrongBinder());
java.lang.String _arg1;
_arg1 = data.readString();
int _arg2;
_arg2 = data.readInt();
this.registerClientAsUser(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_unregisterClient:
{
data.enforceInterface(descriptor);
android.media.IMediaRouterClient _arg0;
_arg0 = android.media.IMediaRouterClient.Stub.asInterface(data.readStrongBinder());
this.unregisterClient(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getState:
{
data.enforceInterface(descriptor);
android.media.IMediaRouterClient _arg0;
_arg0 = android.media.IMediaRouterClient.Stub.asInterface(data.readStrongBinder());
android.media.MediaRouterClientState _result = this.getState(_arg0);
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
case TRANSACTION_setDiscoveryRequest:
{
data.enforceInterface(descriptor);
android.media.IMediaRouterClient _arg0;
_arg0 = android.media.IMediaRouterClient.Stub.asInterface(data.readStrongBinder());
int _arg1;
_arg1 = data.readInt();
boolean _arg2;
_arg2 = (0!=data.readInt());
this.setDiscoveryRequest(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_setSelectedRoute:
{
data.enforceInterface(descriptor);
android.media.IMediaRouterClient _arg0;
_arg0 = android.media.IMediaRouterClient.Stub.asInterface(data.readStrongBinder());
java.lang.String _arg1;
_arg1 = data.readString();
boolean _arg2;
_arg2 = (0!=data.readInt());
this.setSelectedRoute(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_requestSetVolume:
{
data.enforceInterface(descriptor);
android.media.IMediaRouterClient _arg0;
_arg0 = android.media.IMediaRouterClient.Stub.asInterface(data.readStrongBinder());
java.lang.String _arg1;
_arg1 = data.readString();
int _arg2;
_arg2 = data.readInt();
this.requestSetVolume(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_requestUpdateVolume:
{
data.enforceInterface(descriptor);
android.media.IMediaRouterClient _arg0;
_arg0 = android.media.IMediaRouterClient.Stub.asInterface(data.readStrongBinder());
java.lang.String _arg1;
_arg1 = data.readString();
int _arg2;
_arg2 = data.readInt();
this.requestUpdateVolume(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements android.media.IMediaRouterService
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
@Override public void registerClientAsUser(android.media.IMediaRouterClient client, java.lang.String packageName, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((client!=null))?(client.asBinder()):(null)));
_data.writeString(packageName);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_registerClientAsUser, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void unregisterClient(android.media.IMediaRouterClient client) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((client!=null))?(client.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterClient, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public android.media.MediaRouterClientState getState(android.media.IMediaRouterClient client) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.media.MediaRouterClientState _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((client!=null))?(client.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_getState, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = android.media.MediaRouterClientState.CREATOR.createFromParcel(_reply);
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
@Override public void setDiscoveryRequest(android.media.IMediaRouterClient client, int routeTypes, boolean activeScan) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((client!=null))?(client.asBinder()):(null)));
_data.writeInt(routeTypes);
_data.writeInt(((activeScan)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setDiscoveryRequest, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void setSelectedRoute(android.media.IMediaRouterClient client, java.lang.String routeId, boolean explicit) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((client!=null))?(client.asBinder()):(null)));
_data.writeString(routeId);
_data.writeInt(((explicit)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setSelectedRoute, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void requestSetVolume(android.media.IMediaRouterClient client, java.lang.String routeId, int volume) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((client!=null))?(client.asBinder()):(null)));
_data.writeString(routeId);
_data.writeInt(volume);
mRemote.transact(Stub.TRANSACTION_requestSetVolume, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void requestUpdateVolume(android.media.IMediaRouterClient client, java.lang.String routeId, int direction) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((client!=null))?(client.asBinder()):(null)));
_data.writeString(routeId);
_data.writeInt(direction);
mRemote.transact(Stub.TRANSACTION_requestUpdateVolume, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_registerClientAsUser = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_unregisterClient = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getState = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_setDiscoveryRequest = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_setSelectedRoute = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_requestSetVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_requestUpdateVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
}
public void registerClientAsUser(android.media.IMediaRouterClient client, java.lang.String packageName, int userId) throws android.os.RemoteException;
public void unregisterClient(android.media.IMediaRouterClient client) throws android.os.RemoteException;
public android.media.MediaRouterClientState getState(android.media.IMediaRouterClient client) throws android.os.RemoteException;
public void setDiscoveryRequest(android.media.IMediaRouterClient client, int routeTypes, boolean activeScan) throws android.os.RemoteException;
public void setSelectedRoute(android.media.IMediaRouterClient client, java.lang.String routeId, boolean explicit) throws android.os.RemoteException;
public void requestSetVolume(android.media.IMediaRouterClient client, java.lang.String routeId, int volume) throws android.os.RemoteException;
public void requestUpdateVolume(android.media.IMediaRouterClient client, java.lang.String routeId, int direction) throws android.os.RemoteException;
}
