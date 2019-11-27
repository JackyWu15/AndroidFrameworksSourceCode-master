/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: K:\\AndroidFrameworksSourceCode-master\\base\\media\\java\\android\\media\\tv\\ITvInputManagerCallback.aidl
 */
package android.media.tv;
/**
 * Interface to receive callbacks from ITvInputManager regardless of sessions.
 * @hide
 */
public interface ITvInputManagerCallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.media.tv.ITvInputManagerCallback
{
private static final java.lang.String DESCRIPTOR = "android.media.tv.ITvInputManagerCallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.media.tv.ITvInputManagerCallback interface,
 * generating a proxy if needed.
 */
public static android.media.tv.ITvInputManagerCallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.media.tv.ITvInputManagerCallback))) {
return ((android.media.tv.ITvInputManagerCallback)iin);
}
return new android.media.tv.ITvInputManagerCallback.Stub.Proxy(obj);
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
case TRANSACTION_onInputStateChanged:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
int _arg1;
_arg1 = data.readInt();
this.onInputStateChanged(_arg0, _arg1);
return true;
}
case TRANSACTION_onInputAdded:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
this.onInputAdded(_arg0);
return true;
}
case TRANSACTION_onInputRemoved:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
this.onInputRemoved(_arg0);
return true;
}
case TRANSACTION_onInputUpdated:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
this.onInputUpdated(_arg0);
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements android.media.tv.ITvInputManagerCallback
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
@Override public void onInputStateChanged(java.lang.String inputId, int state) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(inputId);
_data.writeInt(state);
mRemote.transact(Stub.TRANSACTION_onInputStateChanged, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onInputAdded(java.lang.String inputId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(inputId);
mRemote.transact(Stub.TRANSACTION_onInputAdded, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onInputRemoved(java.lang.String inputId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(inputId);
mRemote.transact(Stub.TRANSACTION_onInputRemoved, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onInputUpdated(java.lang.String inputId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(inputId);
mRemote.transact(Stub.TRANSACTION_onInputUpdated, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_onInputStateChanged = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_onInputAdded = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_onInputRemoved = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_onInputUpdated = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
}
public void onInputStateChanged(java.lang.String inputId, int state) throws android.os.RemoteException;
public void onInputAdded(java.lang.String inputId) throws android.os.RemoteException;
public void onInputRemoved(java.lang.String inputId) throws android.os.RemoteException;
public void onInputUpdated(java.lang.String inputId) throws android.os.RemoteException;
}
