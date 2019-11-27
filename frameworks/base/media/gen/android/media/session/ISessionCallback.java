/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: K:\\AndroidFrameworksSourceCode-master\\base\\media\\java\\android\\media\\session\\ISessionCallback.aidl
 */
package android.media.session;
/**
 * @hide
 */
public interface ISessionCallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.media.session.ISessionCallback
{
private static final java.lang.String DESCRIPTOR = "android.media.session.ISessionCallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.media.session.ISessionCallback interface,
 * generating a proxy if needed.
 */
public static android.media.session.ISessionCallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.media.session.ISessionCallback))) {
return ((android.media.session.ISessionCallback)iin);
}
return new android.media.session.ISessionCallback.Stub.Proxy(obj);
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
case TRANSACTION_onCommand:
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
android.os.ResultReceiver _arg2;
if ((0!=data.readInt())) {
_arg2 = android.os.ResultReceiver.CREATOR.createFromParcel(data);
}
else {
_arg2 = null;
}
this.onCommand(_arg0, _arg1, _arg2);
return true;
}
case TRANSACTION_onMediaButton:
{
data.enforceInterface(descriptor);
android.content.Intent _arg0;
if ((0!=data.readInt())) {
_arg0 = android.content.Intent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
int _arg1;
_arg1 = data.readInt();
android.os.ResultReceiver _arg2;
if ((0!=data.readInt())) {
_arg2 = android.os.ResultReceiver.CREATOR.createFromParcel(data);
}
else {
_arg2 = null;
}
this.onMediaButton(_arg0, _arg1, _arg2);
return true;
}
case TRANSACTION_onPlay:
{
data.enforceInterface(descriptor);
this.onPlay();
return true;
}
case TRANSACTION_onPlayFromMediaId:
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
this.onPlayFromMediaId(_arg0, _arg1);
return true;
}
case TRANSACTION_onPlayFromSearch:
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
this.onPlayFromSearch(_arg0, _arg1);
return true;
}
case TRANSACTION_onSkipToTrack:
{
data.enforceInterface(descriptor);
long _arg0;
_arg0 = data.readLong();
this.onSkipToTrack(_arg0);
return true;
}
case TRANSACTION_onPause:
{
data.enforceInterface(descriptor);
this.onPause();
return true;
}
case TRANSACTION_onStop:
{
data.enforceInterface(descriptor);
this.onStop();
return true;
}
case TRANSACTION_onNext:
{
data.enforceInterface(descriptor);
this.onNext();
return true;
}
case TRANSACTION_onPrevious:
{
data.enforceInterface(descriptor);
this.onPrevious();
return true;
}
case TRANSACTION_onFastForward:
{
data.enforceInterface(descriptor);
this.onFastForward();
return true;
}
case TRANSACTION_onRewind:
{
data.enforceInterface(descriptor);
this.onRewind();
return true;
}
case TRANSACTION_onSeekTo:
{
data.enforceInterface(descriptor);
long _arg0;
_arg0 = data.readLong();
this.onSeekTo(_arg0);
return true;
}
case TRANSACTION_onRate:
{
data.enforceInterface(descriptor);
android.media.Rating _arg0;
if ((0!=data.readInt())) {
_arg0 = android.media.Rating.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onRate(_arg0);
return true;
}
case TRANSACTION_onCustomAction:
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
this.onCustomAction(_arg0, _arg1);
return true;
}
case TRANSACTION_onAdjustVolume:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
this.onAdjustVolume(_arg0);
return true;
}
case TRANSACTION_onSetVolumeTo:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
this.onSetVolumeTo(_arg0);
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements android.media.session.ISessionCallback
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
@Override public void onCommand(java.lang.String command, android.os.Bundle args, android.os.ResultReceiver cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(command);
if ((args!=null)) {
_data.writeInt(1);
args.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
if ((cb!=null)) {
_data.writeInt(1);
cb.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onCommand, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onMediaButton(android.content.Intent mediaButtonIntent, int sequenceNumber, android.os.ResultReceiver cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((mediaButtonIntent!=null)) {
_data.writeInt(1);
mediaButtonIntent.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeInt(sequenceNumber);
if ((cb!=null)) {
_data.writeInt(1);
cb.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onMediaButton, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
// These callbacks are for the TransportPerformer

@Override public void onPlay() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onPlay, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onPlayFromMediaId(java.lang.String uri, android.os.Bundle extras) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(uri);
if ((extras!=null)) {
_data.writeInt(1);
extras.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onPlayFromMediaId, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onPlayFromSearch(java.lang.String query, android.os.Bundle extras) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(query);
if ((extras!=null)) {
_data.writeInt(1);
extras.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onPlayFromSearch, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onSkipToTrack(long id) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(id);
mRemote.transact(Stub.TRANSACTION_onSkipToTrack, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onPause() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onPause, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onStop() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onStop, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onNext() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onNext, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onPrevious() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onPrevious, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onFastForward() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onFastForward, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onRewind() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onRewind, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onSeekTo(long pos) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(pos);
mRemote.transact(Stub.TRANSACTION_onSeekTo, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onRate(android.media.Rating rating) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((rating!=null)) {
_data.writeInt(1);
rating.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onRate, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onCustomAction(java.lang.String action, android.os.Bundle args) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(action);
if ((args!=null)) {
_data.writeInt(1);
args.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onCustomAction, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
// These callbacks are for volume handling

@Override public void onAdjustVolume(int direction) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(direction);
mRemote.transact(Stub.TRANSACTION_onAdjustVolume, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onSetVolumeTo(int value) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(value);
mRemote.transact(Stub.TRANSACTION_onSetVolumeTo, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_onCommand = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_onMediaButton = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_onPlay = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_onPlayFromMediaId = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_onPlayFromSearch = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_onSkipToTrack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_onPause = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_onStop = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_onNext = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_onPrevious = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_onFastForward = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_onRewind = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_onSeekTo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
static final int TRANSACTION_onRate = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
static final int TRANSACTION_onCustomAction = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
static final int TRANSACTION_onAdjustVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
static final int TRANSACTION_onSetVolumeTo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16);
}
public void onCommand(java.lang.String command, android.os.Bundle args, android.os.ResultReceiver cb) throws android.os.RemoteException;
public void onMediaButton(android.content.Intent mediaButtonIntent, int sequenceNumber, android.os.ResultReceiver cb) throws android.os.RemoteException;
// These callbacks are for the TransportPerformer

public void onPlay() throws android.os.RemoteException;
public void onPlayFromMediaId(java.lang.String uri, android.os.Bundle extras) throws android.os.RemoteException;
public void onPlayFromSearch(java.lang.String query, android.os.Bundle extras) throws android.os.RemoteException;
public void onSkipToTrack(long id) throws android.os.RemoteException;
public void onPause() throws android.os.RemoteException;
public void onStop() throws android.os.RemoteException;
public void onNext() throws android.os.RemoteException;
public void onPrevious() throws android.os.RemoteException;
public void onFastForward() throws android.os.RemoteException;
public void onRewind() throws android.os.RemoteException;
public void onSeekTo(long pos) throws android.os.RemoteException;
public void onRate(android.media.Rating rating) throws android.os.RemoteException;
public void onCustomAction(java.lang.String action, android.os.Bundle args) throws android.os.RemoteException;
// These callbacks are for volume handling

public void onAdjustVolume(int direction) throws android.os.RemoteException;
public void onSetVolumeTo(int value) throws android.os.RemoteException;
}
