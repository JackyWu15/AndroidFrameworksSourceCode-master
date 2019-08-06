/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: K:\\AndroidFrameworksSourceCode-master\\base\\media\\java\\android\\media\\IRemoteControlClient.aidl
 */
package android.media;
/**
 * @hide
 * Interface registered by AudioManager to notify a source of remote control information
 * that information is requested to be displayed on the remote control (through
 * IRemoteControlDisplay).
 * {@see AudioManager#registerRemoteControlClient(RemoteControlClient)}.
 */
public interface IRemoteControlClient extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.media.IRemoteControlClient
{
private static final java.lang.String DESCRIPTOR = "android.media.IRemoteControlClient";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.media.IRemoteControlClient interface,
 * generating a proxy if needed.
 */
public static android.media.IRemoteControlClient asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.media.IRemoteControlClient))) {
return ((android.media.IRemoteControlClient)iin);
}
return new android.media.IRemoteControlClient.Stub.Proxy(obj);
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
case TRANSACTION_onInformationRequested:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
this.onInformationRequested(_arg0, _arg1);
return true;
}
case TRANSACTION_informationRequestForDisplay:
{
data.enforceInterface(descriptor);
android.media.IRemoteControlDisplay _arg0;
_arg0 = android.media.IRemoteControlDisplay.Stub.asInterface(data.readStrongBinder());
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
this.informationRequestForDisplay(_arg0, _arg1, _arg2);
return true;
}
case TRANSACTION_setCurrentClientGenerationId:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
this.setCurrentClientGenerationId(_arg0);
return true;
}
case TRANSACTION_plugRemoteControlDisplay:
{
data.enforceInterface(descriptor);
android.media.IRemoteControlDisplay _arg0;
_arg0 = android.media.IRemoteControlDisplay.Stub.asInterface(data.readStrongBinder());
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
this.plugRemoteControlDisplay(_arg0, _arg1, _arg2);
return true;
}
case TRANSACTION_unplugRemoteControlDisplay:
{
data.enforceInterface(descriptor);
android.media.IRemoteControlDisplay _arg0;
_arg0 = android.media.IRemoteControlDisplay.Stub.asInterface(data.readStrongBinder());
this.unplugRemoteControlDisplay(_arg0);
return true;
}
case TRANSACTION_setBitmapSizeForDisplay:
{
data.enforceInterface(descriptor);
android.media.IRemoteControlDisplay _arg0;
_arg0 = android.media.IRemoteControlDisplay.Stub.asInterface(data.readStrongBinder());
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
this.setBitmapSizeForDisplay(_arg0, _arg1, _arg2);
return true;
}
case TRANSACTION_setWantsSyncForDisplay:
{
data.enforceInterface(descriptor);
android.media.IRemoteControlDisplay _arg0;
_arg0 = android.media.IRemoteControlDisplay.Stub.asInterface(data.readStrongBinder());
boolean _arg1;
_arg1 = (0!=data.readInt());
this.setWantsSyncForDisplay(_arg0, _arg1);
return true;
}
case TRANSACTION_enableRemoteControlDisplay:
{
data.enforceInterface(descriptor);
android.media.IRemoteControlDisplay _arg0;
_arg0 = android.media.IRemoteControlDisplay.Stub.asInterface(data.readStrongBinder());
boolean _arg1;
_arg1 = (0!=data.readInt());
this.enableRemoteControlDisplay(_arg0, _arg1);
return true;
}
case TRANSACTION_seekTo:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
long _arg1;
_arg1 = data.readLong();
this.seekTo(_arg0, _arg1);
return true;
}
case TRANSACTION_updateMetadata:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
android.media.Rating _arg2;
if ((0!=data.readInt())) {
_arg2 = android.media.Rating.CREATOR.createFromParcel(data);
}
else {
_arg2 = null;
}
this.updateMetadata(_arg0, _arg1, _arg2);
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements android.media.IRemoteControlClient
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
/**
     * Notifies a remote control client that information for the given generation ID is
     * requested. If the flags contains
     * {@link RemoteControlClient#FLAG_INFORMATION_REQUESTED_ALBUM_ART} then the width and height
     *   parameters are valid.
     * @param generationId
     * @param infoFlags
     * FIXME: is infoFlags required? since the RCC pushes info, this might always be called
     *        with RC_INFO_ALL
     */
@Override public void onInformationRequested(int generationId, int infoFlags) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(generationId);
_data.writeInt(infoFlags);
mRemote.transact(Stub.TRANSACTION_onInformationRequested, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
/**
     * Notifies a remote control client that information for the given generation ID is
     * requested for the given IRemoteControlDisplay alone.
     * @param rcd the display to which current info should be sent
     */
@Override public void informationRequestForDisplay(android.media.IRemoteControlDisplay rcd, int w, int h) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((rcd!=null))?(rcd.asBinder()):(null)));
_data.writeInt(w);
_data.writeInt(h);
mRemote.transact(Stub.TRANSACTION_informationRequestForDisplay, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
/**
     * Sets the generation counter of the current client that is displayed on the remote control.
     */
@Override public void setCurrentClientGenerationId(int clientGeneration) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(clientGeneration);
mRemote.transact(Stub.TRANSACTION_setCurrentClientGenerationId, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void plugRemoteControlDisplay(android.media.IRemoteControlDisplay rcd, int w, int h) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((rcd!=null))?(rcd.asBinder()):(null)));
_data.writeInt(w);
_data.writeInt(h);
mRemote.transact(Stub.TRANSACTION_plugRemoteControlDisplay, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unplugRemoteControlDisplay(android.media.IRemoteControlDisplay rcd) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((rcd!=null))?(rcd.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unplugRemoteControlDisplay, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void setBitmapSizeForDisplay(android.media.IRemoteControlDisplay rcd, int w, int h) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((rcd!=null))?(rcd.asBinder()):(null)));
_data.writeInt(w);
_data.writeInt(h);
mRemote.transact(Stub.TRANSACTION_setBitmapSizeForDisplay, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void setWantsSyncForDisplay(android.media.IRemoteControlDisplay rcd, boolean wantsSync) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((rcd!=null))?(rcd.asBinder()):(null)));
_data.writeInt(((wantsSync)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setWantsSyncForDisplay, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void enableRemoteControlDisplay(android.media.IRemoteControlDisplay rcd, boolean enabled) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((rcd!=null))?(rcd.asBinder()):(null)));
_data.writeInt(((enabled)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_enableRemoteControlDisplay, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void seekTo(int clientGeneration, long timeMs) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(clientGeneration);
_data.writeLong(timeMs);
mRemote.transact(Stub.TRANSACTION_seekTo, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void updateMetadata(int clientGeneration, int key, android.media.Rating value) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(clientGeneration);
_data.writeInt(key);
if ((value!=null)) {
_data.writeInt(1);
value.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_updateMetadata, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_onInformationRequested = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_informationRequestForDisplay = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_setCurrentClientGenerationId = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_plugRemoteControlDisplay = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_unplugRemoteControlDisplay = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_setBitmapSizeForDisplay = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_setWantsSyncForDisplay = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_enableRemoteControlDisplay = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_seekTo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_updateMetadata = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
}
/**
     * Notifies a remote control client that information for the given generation ID is
     * requested. If the flags contains
     * {@link RemoteControlClient#FLAG_INFORMATION_REQUESTED_ALBUM_ART} then the width and height
     *   parameters are valid.
     * @param generationId
     * @param infoFlags
     * FIXME: is infoFlags required? since the RCC pushes info, this might always be called
     *        with RC_INFO_ALL
     */
public void onInformationRequested(int generationId, int infoFlags) throws android.os.RemoteException;
/**
     * Notifies a remote control client that information for the given generation ID is
     * requested for the given IRemoteControlDisplay alone.
     * @param rcd the display to which current info should be sent
     */
public void informationRequestForDisplay(android.media.IRemoteControlDisplay rcd, int w, int h) throws android.os.RemoteException;
/**
     * Sets the generation counter of the current client that is displayed on the remote control.
     */
public void setCurrentClientGenerationId(int clientGeneration) throws android.os.RemoteException;
public void plugRemoteControlDisplay(android.media.IRemoteControlDisplay rcd, int w, int h) throws android.os.RemoteException;
public void unplugRemoteControlDisplay(android.media.IRemoteControlDisplay rcd) throws android.os.RemoteException;
public void setBitmapSizeForDisplay(android.media.IRemoteControlDisplay rcd, int w, int h) throws android.os.RemoteException;
public void setWantsSyncForDisplay(android.media.IRemoteControlDisplay rcd, boolean wantsSync) throws android.os.RemoteException;
public void enableRemoteControlDisplay(android.media.IRemoteControlDisplay rcd, boolean enabled) throws android.os.RemoteException;
public void seekTo(int clientGeneration, long timeMs) throws android.os.RemoteException;
public void updateMetadata(int clientGeneration, int key, android.media.Rating value) throws android.os.RemoteException;
}
