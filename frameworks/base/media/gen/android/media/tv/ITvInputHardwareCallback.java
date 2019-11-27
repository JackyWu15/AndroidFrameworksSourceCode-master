/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: K:\\AndroidFrameworksSourceCode-master\\base\\media\\java\\android\\media\\tv\\ITvInputHardwareCallback.aidl
 */
package android.media.tv;
/**
 * @hide
 */
public interface ITvInputHardwareCallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.media.tv.ITvInputHardwareCallback
{
private static final java.lang.String DESCRIPTOR = "android.media.tv.ITvInputHardwareCallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.media.tv.ITvInputHardwareCallback interface,
 * generating a proxy if needed.
 */
public static android.media.tv.ITvInputHardwareCallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.media.tv.ITvInputHardwareCallback))) {
return ((android.media.tv.ITvInputHardwareCallback)iin);
}
return new android.media.tv.ITvInputHardwareCallback.Stub.Proxy(obj);
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
case TRANSACTION_onReleased:
{
data.enforceInterface(descriptor);
this.onReleased();
return true;
}
case TRANSACTION_onStreamConfigChanged:
{
data.enforceInterface(descriptor);
android.media.tv.TvStreamConfig[] _arg0;
_arg0 = data.createTypedArray(android.media.tv.TvStreamConfig.CREATOR);
this.onStreamConfigChanged(_arg0);
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements android.media.tv.ITvInputHardwareCallback
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
@Override public void onReleased() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onReleased, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onStreamConfigChanged(android.media.tv.TvStreamConfig[] configs) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeTypedArray(configs, 0);
mRemote.transact(Stub.TRANSACTION_onStreamConfigChanged, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_onReleased = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_onStreamConfigChanged = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
public void onReleased() throws android.os.RemoteException;
public void onStreamConfigChanged(android.media.tv.TvStreamConfig[] configs) throws android.os.RemoteException;
}
