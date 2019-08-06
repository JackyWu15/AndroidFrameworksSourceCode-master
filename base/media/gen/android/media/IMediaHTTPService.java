/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: K:\\AndroidFrameworksSourceCode-master\\base\\media\\java\\android\\media\\IMediaHTTPService.aidl
 */
package android.media;
/** MUST STAY IN SYNC WITH NATIVE CODE at libmedia/IMediaHTTPService.{cpp,h} *//** @hide */
public interface IMediaHTTPService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.media.IMediaHTTPService
{
private static final java.lang.String DESCRIPTOR = "android.media.IMediaHTTPService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.media.IMediaHTTPService interface,
 * generating a proxy if needed.
 */
public static android.media.IMediaHTTPService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.media.IMediaHTTPService))) {
return ((android.media.IMediaHTTPService)iin);
}
return new android.media.IMediaHTTPService.Stub.Proxy(obj);
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
case TRANSACTION_makeHTTPConnection:
{
data.enforceInterface(descriptor);
android.media.IMediaHTTPConnection _result = this.makeHTTPConnection();
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements android.media.IMediaHTTPService
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
@Override public android.media.IMediaHTTPConnection makeHTTPConnection() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.media.IMediaHTTPConnection _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_makeHTTPConnection, _data, _reply, 0);
_reply.readException();
_result = android.media.IMediaHTTPConnection.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_makeHTTPConnection = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public android.media.IMediaHTTPConnection makeHTTPConnection() throws android.os.RemoteException;
}
