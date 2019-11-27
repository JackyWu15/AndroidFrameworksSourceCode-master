/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: K:\\AndroidFrameworksSourceCode-master\\base\\media\\java\\android\\media\\tv\\ITvInputSession.aidl
 */
package android.media.tv;
/**
 * Sub-interface of ITvInputService which is created per session and has its own context.
 * @hide
 */
public interface ITvInputSession extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.media.tv.ITvInputSession
{
private static final java.lang.String DESCRIPTOR = "android.media.tv.ITvInputSession";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.media.tv.ITvInputSession interface,
 * generating a proxy if needed.
 */
public static android.media.tv.ITvInputSession asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.media.tv.ITvInputSession))) {
return ((android.media.tv.ITvInputSession)iin);
}
return new android.media.tv.ITvInputSession.Stub.Proxy(obj);
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
case TRANSACTION_release:
{
data.enforceInterface(descriptor);
this.release();
return true;
}
case TRANSACTION_setMain:
{
data.enforceInterface(descriptor);
boolean _arg0;
_arg0 = (0!=data.readInt());
this.setMain(_arg0);
return true;
}
case TRANSACTION_setSurface:
{
data.enforceInterface(descriptor);
android.view.Surface _arg0;
if ((0!=data.readInt())) {
_arg0 = android.view.Surface.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.setSurface(_arg0);
return true;
}
case TRANSACTION_dispatchSurfaceChanged:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
this.dispatchSurfaceChanged(_arg0, _arg1, _arg2);
return true;
}
case TRANSACTION_setVolume:
{
data.enforceInterface(descriptor);
float _arg0;
_arg0 = data.readFloat();
this.setVolume(_arg0);
return true;
}
case TRANSACTION_tune:
{
data.enforceInterface(descriptor);
android.net.Uri _arg0;
if ((0!=data.readInt())) {
_arg0 = android.net.Uri.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
android.os.Bundle _arg1;
if ((0!=data.readInt())) {
_arg1 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
this.tune(_arg0, _arg1);
return true;
}
case TRANSACTION_setCaptionEnabled:
{
data.enforceInterface(descriptor);
boolean _arg0;
_arg0 = (0!=data.readInt());
this.setCaptionEnabled(_arg0);
return true;
}
case TRANSACTION_selectTrack:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
java.lang.String _arg1;
_arg1 = data.readString();
this.selectTrack(_arg0, _arg1);
return true;
}
case TRANSACTION_appPrivateCommand:
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
this.appPrivateCommand(_arg0, _arg1);
return true;
}
case TRANSACTION_createOverlayView:
{
data.enforceInterface(descriptor);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
android.graphics.Rect _arg1;
if ((0!=data.readInt())) {
_arg1 = android.graphics.Rect.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
this.createOverlayView(_arg0, _arg1);
return true;
}
case TRANSACTION_relayoutOverlayView:
{
data.enforceInterface(descriptor);
android.graphics.Rect _arg0;
if ((0!=data.readInt())) {
_arg0 = android.graphics.Rect.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.relayoutOverlayView(_arg0);
return true;
}
case TRANSACTION_removeOverlayView:
{
data.enforceInterface(descriptor);
this.removeOverlayView();
return true;
}
case TRANSACTION_requestUnblockContent:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
this.requestUnblockContent(_arg0);
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements android.media.tv.ITvInputSession
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
@Override public void release() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_release, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void setMain(boolean isMain) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((isMain)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setMain, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void setSurface(android.view.Surface surface) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((surface!=null)) {
_data.writeInt(1);
surface.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_setSurface, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void dispatchSurfaceChanged(int format, int width, int height) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(format);
_data.writeInt(width);
_data.writeInt(height);
mRemote.transact(Stub.TRANSACTION_dispatchSurfaceChanged, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
// TODO: Remove this once it becomes irrelevant for applications to handle audio focus. The plan
// is to introduce some new concepts that will solve a number of problems in audio policy today.

@Override public void setVolume(float volume) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeFloat(volume);
mRemote.transact(Stub.TRANSACTION_setVolume, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void tune(android.net.Uri channelUri, android.os.Bundle params) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((channelUri!=null)) {
_data.writeInt(1);
channelUri.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
if ((params!=null)) {
_data.writeInt(1);
params.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_tune, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void setCaptionEnabled(boolean enabled) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((enabled)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setCaptionEnabled, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void selectTrack(int type, java.lang.String trackId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(type);
_data.writeString(trackId);
mRemote.transact(Stub.TRANSACTION_selectTrack, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void appPrivateCommand(java.lang.String action, android.os.Bundle data) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(action);
if ((data!=null)) {
_data.writeInt(1);
data.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_appPrivateCommand, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void createOverlayView(android.os.IBinder windowToken, android.graphics.Rect frame) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(windowToken);
if ((frame!=null)) {
_data.writeInt(1);
frame.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_createOverlayView, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void relayoutOverlayView(android.graphics.Rect frame) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((frame!=null)) {
_data.writeInt(1);
frame.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_relayoutOverlayView, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void removeOverlayView() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_removeOverlayView, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void requestUnblockContent(java.lang.String unblockedRating) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(unblockedRating);
mRemote.transact(Stub.TRANSACTION_requestUnblockContent, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_release = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_setMain = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_setSurface = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_dispatchSurfaceChanged = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_setVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_tune = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_setCaptionEnabled = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_selectTrack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_appPrivateCommand = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_createOverlayView = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_relayoutOverlayView = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_removeOverlayView = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_requestUnblockContent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
}
public void release() throws android.os.RemoteException;
public void setMain(boolean isMain) throws android.os.RemoteException;
public void setSurface(android.view.Surface surface) throws android.os.RemoteException;
public void dispatchSurfaceChanged(int format, int width, int height) throws android.os.RemoteException;
// TODO: Remove this once it becomes irrelevant for applications to handle audio focus. The plan
// is to introduce some new concepts that will solve a number of problems in audio policy today.

public void setVolume(float volume) throws android.os.RemoteException;
public void tune(android.net.Uri channelUri, android.os.Bundle params) throws android.os.RemoteException;
public void setCaptionEnabled(boolean enabled) throws android.os.RemoteException;
public void selectTrack(int type, java.lang.String trackId) throws android.os.RemoteException;
public void appPrivateCommand(java.lang.String action, android.os.Bundle data) throws android.os.RemoteException;
public void createOverlayView(android.os.IBinder windowToken, android.graphics.Rect frame) throws android.os.RemoteException;
public void relayoutOverlayView(android.graphics.Rect frame) throws android.os.RemoteException;
public void removeOverlayView() throws android.os.RemoteException;
public void requestUnblockContent(java.lang.String unblockedRating) throws android.os.RemoteException;
}
