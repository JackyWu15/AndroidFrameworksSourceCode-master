/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: K:\\AndroidFrameworksSourceCode-master\\base\\media\\java\\android\\media\\tv\\ITvInputServiceCallback.aidl
 */
package android.media.tv;
/**
 * Helper interface for ITvInputService to allow the service to notify the
 * TvInputManagerService.
 * @hide
 */
public interface ITvInputServiceCallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.media.tv.ITvInputServiceCallback
{
private static final java.lang.String DESCRIPTOR = "android.media.tv.ITvInputServiceCallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.media.tv.ITvInputServiceCallback interface,
 * generating a proxy if needed.
 */
public static android.media.tv.ITvInputServiceCallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.media.tv.ITvInputServiceCallback))) {
return ((android.media.tv.ITvInputServiceCallback)iin);
}
return new android.media.tv.ITvInputServiceCallback.Stub.Proxy(obj);
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
case TRANSACTION_addHardwareTvInput:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
android.media.tv.TvInputInfo _arg1;
if ((0!=data.readInt())) {
_arg1 = android.media.tv.TvInputInfo.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
this.addHardwareTvInput(_arg0, _arg1);
return true;
}
case TRANSACTION_addHdmiTvInput:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
android.media.tv.TvInputInfo _arg1;
if ((0!=data.readInt())) {
_arg1 = android.media.tv.TvInputInfo.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
this.addHdmiTvInput(_arg0, _arg1);
return true;
}
case TRANSACTION_removeTvInput:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
this.removeTvInput(_arg0);
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements android.media.tv.ITvInputServiceCallback
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
@Override public void addHardwareTvInput(int deviceId, android.media.tv.TvInputInfo inputInfo) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(deviceId);
if ((inputInfo!=null)) {
_data.writeInt(1);
inputInfo.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_addHardwareTvInput, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void addHdmiTvInput(int id, android.media.tv.TvInputInfo inputInfo) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(id);
if ((inputInfo!=null)) {
_data.writeInt(1);
inputInfo.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_addHdmiTvInput, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void removeTvInput(java.lang.String inputId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(inputId);
mRemote.transact(Stub.TRANSACTION_removeTvInput, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_addHardwareTvInput = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_addHdmiTvInput = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_removeTvInput = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
public void addHardwareTvInput(int deviceId, android.media.tv.TvInputInfo inputInfo) throws android.os.RemoteException;
public void addHdmiTvInput(int id, android.media.tv.TvInputInfo inputInfo) throws android.os.RemoteException;
public void removeTvInput(java.lang.String inputId) throws android.os.RemoteException;
}
