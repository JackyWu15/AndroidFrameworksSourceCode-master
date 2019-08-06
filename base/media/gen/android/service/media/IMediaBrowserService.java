/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: K:\\AndroidFrameworksSourceCode-master\\base\\media\\java\\android\\service\\media\\IMediaBrowserService.aidl
 */
package android.service.media;
/**
 * Media API allows clients to browse through hierarchy of a user鈥檚 media collection,
 * playback a specific media entry and interact with the now playing queue.
 * @hide
 */
public interface IMediaBrowserService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.service.media.IMediaBrowserService
{
private static final java.lang.String DESCRIPTOR = "android.service.media.IMediaBrowserService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.service.media.IMediaBrowserService interface,
 * generating a proxy if needed.
 */
public static android.service.media.IMediaBrowserService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.service.media.IMediaBrowserService))) {
return ((android.service.media.IMediaBrowserService)iin);
}
return new android.service.media.IMediaBrowserService.Stub.Proxy(obj);
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
case TRANSACTION_connect:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
android.os.Bundle _arg1;
if ((0!=data.readInt())) {
_arg1 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
android.service.media.IMediaBrowserServiceCallbacks _arg2;
_arg2 = android.service.media.IMediaBrowserServiceCallbacks.Stub.asInterface(data.readStrongBinder());
this.connect(_arg0, _arg1, _arg2);
return true;
}
case TRANSACTION_disconnect:
{
data.enforceInterface(descriptor);
android.service.media.IMediaBrowserServiceCallbacks _arg0;
_arg0 = android.service.media.IMediaBrowserServiceCallbacks.Stub.asInterface(data.readStrongBinder());
this.disconnect(_arg0);
return true;
}
case TRANSACTION_addSubscription:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
android.service.media.IMediaBrowserServiceCallbacks _arg1;
_arg1 = android.service.media.IMediaBrowserServiceCallbacks.Stub.asInterface(data.readStrongBinder());
this.addSubscription(_arg0, _arg1);
return true;
}
case TRANSACTION_removeSubscription:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
android.service.media.IMediaBrowserServiceCallbacks _arg1;
_arg1 = android.service.media.IMediaBrowserServiceCallbacks.Stub.asInterface(data.readStrongBinder());
this.removeSubscription(_arg0, _arg1);
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements android.service.media.IMediaBrowserService
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
@Override public void connect(java.lang.String pkg, android.os.Bundle rootHints, android.service.media.IMediaBrowserServiceCallbacks callbacks) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(pkg);
if ((rootHints!=null)) {
_data.writeInt(1);
rootHints.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeStrongBinder((((callbacks!=null))?(callbacks.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_connect, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void disconnect(android.service.media.IMediaBrowserServiceCallbacks callbacks) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callbacks!=null))?(callbacks.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_disconnect, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void addSubscription(java.lang.String uri, android.service.media.IMediaBrowserServiceCallbacks callbacks) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(uri);
_data.writeStrongBinder((((callbacks!=null))?(callbacks.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_addSubscription, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void removeSubscription(java.lang.String uri, android.service.media.IMediaBrowserServiceCallbacks callbacks) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(uri);
_data.writeStrongBinder((((callbacks!=null))?(callbacks.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_removeSubscription, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_connect = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_disconnect = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_addSubscription = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_removeSubscription = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
}
public void connect(java.lang.String pkg, android.os.Bundle rootHints, android.service.media.IMediaBrowserServiceCallbacks callbacks) throws android.os.RemoteException;
public void disconnect(android.service.media.IMediaBrowserServiceCallbacks callbacks) throws android.os.RemoteException;
public void addSubscription(java.lang.String uri, android.service.media.IMediaBrowserServiceCallbacks callbacks) throws android.os.RemoteException;
public void removeSubscription(java.lang.String uri, android.service.media.IMediaBrowserServiceCallbacks callbacks) throws android.os.RemoteException;
}
