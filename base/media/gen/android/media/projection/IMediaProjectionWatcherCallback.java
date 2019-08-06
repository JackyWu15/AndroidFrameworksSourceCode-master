/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: K:\\AndroidFrameworksSourceCode-master\\base\\media\\java\\android\\media\\projection\\IMediaProjectionWatcherCallback.aidl
 */
package android.media.projection;
/** {@hide} */
public interface IMediaProjectionWatcherCallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.media.projection.IMediaProjectionWatcherCallback
{
private static final java.lang.String DESCRIPTOR = "android.media.projection.IMediaProjectionWatcherCallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.media.projection.IMediaProjectionWatcherCallback interface,
 * generating a proxy if needed.
 */
public static android.media.projection.IMediaProjectionWatcherCallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.media.projection.IMediaProjectionWatcherCallback))) {
return ((android.media.projection.IMediaProjectionWatcherCallback)iin);
}
return new android.media.projection.IMediaProjectionWatcherCallback.Stub.Proxy(obj);
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
case TRANSACTION_onStart:
{
data.enforceInterface(descriptor);
android.media.projection.MediaProjectionInfo _arg0;
if ((0!=data.readInt())) {
_arg0 = android.media.projection.MediaProjectionInfo.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onStart(_arg0);
return true;
}
case TRANSACTION_onStop:
{
data.enforceInterface(descriptor);
android.media.projection.MediaProjectionInfo _arg0;
if ((0!=data.readInt())) {
_arg0 = android.media.projection.MediaProjectionInfo.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onStop(_arg0);
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements android.media.projection.IMediaProjectionWatcherCallback
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
@Override public void onStart(android.media.projection.MediaProjectionInfo info) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((info!=null)) {
_data.writeInt(1);
info.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onStart, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onStop(android.media.projection.MediaProjectionInfo info) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((info!=null)) {
_data.writeInt(1);
info.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onStop, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_onStart = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_onStop = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
public void onStart(android.media.projection.MediaProjectionInfo info) throws android.os.RemoteException;
public void onStop(android.media.projection.MediaProjectionInfo info) throws android.os.RemoteException;
}
