/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: K:\\AndroidFrameworksSourceCode-master\\base\\media\\java\\android\\media\\IVolumeController.aidl
 */
package android.media;
/**
 * AIDL for the AudioService to report interesting events to a volume control
 * dialog in another process.
 * @hide
 */
public interface IVolumeController extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.media.IVolumeController
{
private static final java.lang.String DESCRIPTOR = "android.media.IVolumeController";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.media.IVolumeController interface,
 * generating a proxy if needed.
 */
public static android.media.IVolumeController asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.media.IVolumeController))) {
return ((android.media.IVolumeController)iin);
}
return new android.media.IVolumeController.Stub.Proxy(obj);
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
case TRANSACTION_displaySafeVolumeWarning:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
this.displaySafeVolumeWarning(_arg0);
return true;
}
case TRANSACTION_volumeChanged:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
this.volumeChanged(_arg0, _arg1);
return true;
}
case TRANSACTION_masterVolumeChanged:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
this.masterVolumeChanged(_arg0);
return true;
}
case TRANSACTION_masterMuteChanged:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
this.masterMuteChanged(_arg0);
return true;
}
case TRANSACTION_setLayoutDirection:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
this.setLayoutDirection(_arg0);
return true;
}
case TRANSACTION_dismiss:
{
data.enforceInterface(descriptor);
this.dismiss();
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements android.media.IVolumeController
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
@Override public void displaySafeVolumeWarning(int flags) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(flags);
mRemote.transact(Stub.TRANSACTION_displaySafeVolumeWarning, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void volumeChanged(int streamType, int flags) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(streamType);
_data.writeInt(flags);
mRemote.transact(Stub.TRANSACTION_volumeChanged, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void masterVolumeChanged(int flags) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(flags);
mRemote.transact(Stub.TRANSACTION_masterVolumeChanged, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void masterMuteChanged(int flags) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(flags);
mRemote.transact(Stub.TRANSACTION_masterMuteChanged, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void setLayoutDirection(int layoutDirection) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(layoutDirection);
mRemote.transact(Stub.TRANSACTION_setLayoutDirection, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void dismiss() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_dismiss, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_displaySafeVolumeWarning = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_volumeChanged = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_masterVolumeChanged = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_masterMuteChanged = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_setLayoutDirection = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_dismiss = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
}
public void displaySafeVolumeWarning(int flags) throws android.os.RemoteException;
public void volumeChanged(int streamType, int flags) throws android.os.RemoteException;
public void masterVolumeChanged(int flags) throws android.os.RemoteException;
public void masterMuteChanged(int flags) throws android.os.RemoteException;
public void setLayoutDirection(int layoutDirection) throws android.os.RemoteException;
public void dismiss() throws android.os.RemoteException;
}
