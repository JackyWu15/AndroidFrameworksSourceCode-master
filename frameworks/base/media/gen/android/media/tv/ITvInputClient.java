/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: K:\\AndroidFrameworksSourceCode-master\\base\\media\\java\\android\\media\\tv\\ITvInputClient.aidl
 */
package android.media.tv;
/**
 * Interface a client of the ITvInputManager implements, to identify itself and receive information
 * about changes to the state of each TV input service.
 * @hide
 */
public interface ITvInputClient extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.media.tv.ITvInputClient
{
private static final java.lang.String DESCRIPTOR = "android.media.tv.ITvInputClient";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.media.tv.ITvInputClient interface,
 * generating a proxy if needed.
 */
public static android.media.tv.ITvInputClient asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.media.tv.ITvInputClient))) {
return ((android.media.tv.ITvInputClient)iin);
}
return new android.media.tv.ITvInputClient.Stub.Proxy(obj);
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
case TRANSACTION_onSessionCreated:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
android.os.IBinder _arg1;
_arg1 = data.readStrongBinder();
android.view.InputChannel _arg2;
if ((0!=data.readInt())) {
_arg2 = android.view.InputChannel.CREATOR.createFromParcel(data);
}
else {
_arg2 = null;
}
int _arg3;
_arg3 = data.readInt();
this.onSessionCreated(_arg0, _arg1, _arg2, _arg3);
return true;
}
case TRANSACTION_onSessionReleased:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
this.onSessionReleased(_arg0);
return true;
}
case TRANSACTION_onSessionEvent:
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
int _arg2;
_arg2 = data.readInt();
this.onSessionEvent(_arg0, _arg1, _arg2);
return true;
}
case TRANSACTION_onChannelRetuned:
{
data.enforceInterface(descriptor);
android.net.Uri _arg0;
if ((0!=data.readInt())) {
_arg0 = android.net.Uri.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
int _arg1;
_arg1 = data.readInt();
this.onChannelRetuned(_arg0, _arg1);
return true;
}
case TRANSACTION_onTracksChanged:
{
data.enforceInterface(descriptor);
java.util.List<android.media.tv.TvTrackInfo> _arg0;
_arg0 = data.createTypedArrayList(android.media.tv.TvTrackInfo.CREATOR);
int _arg1;
_arg1 = data.readInt();
this.onTracksChanged(_arg0, _arg1);
return true;
}
case TRANSACTION_onTrackSelected:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
java.lang.String _arg1;
_arg1 = data.readString();
int _arg2;
_arg2 = data.readInt();
this.onTrackSelected(_arg0, _arg1, _arg2);
return true;
}
case TRANSACTION_onVideoAvailable:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
this.onVideoAvailable(_arg0);
return true;
}
case TRANSACTION_onVideoUnavailable:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
this.onVideoUnavailable(_arg0, _arg1);
return true;
}
case TRANSACTION_onContentAllowed:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
this.onContentAllowed(_arg0);
return true;
}
case TRANSACTION_onContentBlocked:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
int _arg1;
_arg1 = data.readInt();
this.onContentBlocked(_arg0, _arg1);
return true;
}
case TRANSACTION_onLayoutSurface:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
int _arg3;
_arg3 = data.readInt();
int _arg4;
_arg4 = data.readInt();
this.onLayoutSurface(_arg0, _arg1, _arg2, _arg3, _arg4);
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements android.media.tv.ITvInputClient
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
@Override public void onSessionCreated(java.lang.String inputId, android.os.IBinder token, android.view.InputChannel channel, int seq) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(inputId);
_data.writeStrongBinder(token);
if ((channel!=null)) {
_data.writeInt(1);
channel.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeInt(seq);
mRemote.transact(Stub.TRANSACTION_onSessionCreated, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onSessionReleased(int seq) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(seq);
mRemote.transact(Stub.TRANSACTION_onSessionReleased, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onSessionEvent(java.lang.String name, android.os.Bundle args, int seq) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(name);
if ((args!=null)) {
_data.writeInt(1);
args.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeInt(seq);
mRemote.transact(Stub.TRANSACTION_onSessionEvent, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onChannelRetuned(android.net.Uri channelUri, int seq) throws android.os.RemoteException
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
_data.writeInt(seq);
mRemote.transact(Stub.TRANSACTION_onChannelRetuned, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onTracksChanged(java.util.List<android.media.tv.TvTrackInfo> tracks, int seq) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeTypedList(tracks);
_data.writeInt(seq);
mRemote.transact(Stub.TRANSACTION_onTracksChanged, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onTrackSelected(int type, java.lang.String trackId, int seq) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(type);
_data.writeString(trackId);
_data.writeInt(seq);
mRemote.transact(Stub.TRANSACTION_onTrackSelected, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onVideoAvailable(int seq) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(seq);
mRemote.transact(Stub.TRANSACTION_onVideoAvailable, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onVideoUnavailable(int reason, int seq) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(reason);
_data.writeInt(seq);
mRemote.transact(Stub.TRANSACTION_onVideoUnavailable, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onContentAllowed(int seq) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(seq);
mRemote.transact(Stub.TRANSACTION_onContentAllowed, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onContentBlocked(java.lang.String rating, int seq) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(rating);
_data.writeInt(seq);
mRemote.transact(Stub.TRANSACTION_onContentBlocked, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onLayoutSurface(int left, int top, int right, int bottom, int seq) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(left);
_data.writeInt(top);
_data.writeInt(right);
_data.writeInt(bottom);
_data.writeInt(seq);
mRemote.transact(Stub.TRANSACTION_onLayoutSurface, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_onSessionCreated = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_onSessionReleased = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_onSessionEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_onChannelRetuned = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_onTracksChanged = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_onTrackSelected = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_onVideoAvailable = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_onVideoUnavailable = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_onContentAllowed = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_onContentBlocked = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_onLayoutSurface = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
}
public void onSessionCreated(java.lang.String inputId, android.os.IBinder token, android.view.InputChannel channel, int seq) throws android.os.RemoteException;
public void onSessionReleased(int seq) throws android.os.RemoteException;
public void onSessionEvent(java.lang.String name, android.os.Bundle args, int seq) throws android.os.RemoteException;
public void onChannelRetuned(android.net.Uri channelUri, int seq) throws android.os.RemoteException;
public void onTracksChanged(java.util.List<android.media.tv.TvTrackInfo> tracks, int seq) throws android.os.RemoteException;
public void onTrackSelected(int type, java.lang.String trackId, int seq) throws android.os.RemoteException;
public void onVideoAvailable(int seq) throws android.os.RemoteException;
public void onVideoUnavailable(int reason, int seq) throws android.os.RemoteException;
public void onContentAllowed(int seq) throws android.os.RemoteException;
public void onContentBlocked(java.lang.String rating, int seq) throws android.os.RemoteException;
public void onLayoutSurface(int left, int top, int right, int bottom, int seq) throws android.os.RemoteException;
}
