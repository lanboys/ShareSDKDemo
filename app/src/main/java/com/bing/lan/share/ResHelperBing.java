// package com.bing.lan.share;
//
// import android.content.ContentValues;
// import android.content.Context;
// import android.database.Cursor;
// import android.graphics.Point;
// import android.media.MediaScannerConnection;
// import android.net.Uri;
// import android.os.Build;
// import android.os.Bundle;
// import android.provider.MediaStore;
// import android.text.TextUtils;
// import android.util.DisplayMetrics;
// import android.util.Log;
// import android.view.Display;
// import android.view.WindowManager;
//
// import com.mob.tools.MobLog;
// import com.mob.tools.network.KVPair;
// import com.mob.tools.utils.Data;
// import com.mob.tools.utils.DeviceHelper;
// import com.mob.tools.utils.ResHelper;
//
// import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileOutputStream;
// import java.io.ObjectInputStream;
// import java.io.ObjectOutputStream;
// import java.lang.reflect.Method;
// import java.net.URL;
// import java.net.URLDecoder;
// import java.text.ParsePosition;
// import java.text.SimpleDateFormat;
// import java.util.ArrayList;
// import java.util.Calendar;
// import java.util.Date;
// import java.util.Iterator;
// import java.util.zip.GZIPInputStream;
// import java.util.zip.GZIPOutputStream;
//
// /**
//  * @author 蓝兵
//  * @email lan_bing2013@163.com
//  * @time 2017/7/10  23:36
//  */
// public class ResHelperBing {
//
//     private static float density;
//     private static int deviceWidth;
//     private static Object rp;
//     private static Uri mediaUri;
//
//     public ResHelperBing() {
//     }
//
//     public static int dipToPx(Context context, int dip) {
//         if (density <= 0.0F) {
//             density = context.getResources().getDisplayMetrics().density;
//         }
//
//         return (int) ((float) dip * density + 0.5F);
//     }
//
//     public static int pxToDip(Context context, int px) {
//         if (density <= 0.0F) {
//             density = context.getResources().getDisplayMetrics().density;
//         }
//
//         return (int) ((float) px / density + 0.5F);
//     }
//
//     public static int designToDevice(Context context, int designScreenWidth, int designPx) {
//         if (deviceWidth == 0) {
//             int[] scrSize = getScreenSize(context);
//             deviceWidth = scrSize[0] < scrSize[1] ? scrSize[0] : scrSize[1];
//         }
//
//         return (int) ((float) designPx * (float) deviceWidth / (float) designScreenWidth + 0.5F);
//     }
//
//     public static int designToDevice(Context context, float designScreenDensity, int designPx) {
//         if (density <= 0.0F) {
//             density = context.getResources().getDisplayMetrics().density;
//         }
//
//         return (int) ((float) designPx * density / designScreenDensity + 0.5F);
//     }
//
//     public static int[] getScreenSize(Context context) {
//         WindowManager windowManager;
//         try {
//             windowManager = (WindowManager) context.getSystemService("window");
//         } catch (Throwable var6) {
//             MobLog.getInstance().w(var6);
//             windowManager = null;
//         }
//
//         if (windowManager == null) {
//             return new int[]{0, 0};
//         } else {
//             Display display = windowManager.getDefaultDisplay();
//             if (Build.VERSION.SDK_INT < 13) {
//                 DisplayMetrics t1 = new DisplayMetrics();
//                 display.getMetrics(t1);
//                 return new int[]{t1.widthPixels, t1.heightPixels};
//             } else {
//                 try {
//                     Point t = new Point();
//                     Method method = display.getClass().getMethod("getRealSize", new Class[]{Point.class});
//                     method.setAccessible(true);
//                     method.invoke(display, new Object[]{t});
//                     return new int[]{t.x, t.y};
//                 } catch (Throwable var5) {
//                     MobLog.getInstance().w(var5);
//                     return new int[]{0, 0};
//                 }
//             }
//         }
//     }
//
//     public static int getScreenWidth(Context context) {
//         return getScreenSize(context)[0];
//     }
//
//     public static int getScreenHeight(Context context) {
//         return getScreenSize(context)[1];
//     }
//
//     public static void setResourceProvider(Object rp) {
//         try {
//             Method t = rp.getClass().getMethod("getResId", new Class[]{Context.class, String.class, String.class});
//             if (t != null) {
//                 ResHelperBing.rp = rp;
//             }
//         } catch (Throwable var2) {
//             MobLog.getInstance().d(var2);
//         }
//     }
//
//     public static int getResId(Context context, String resType, String resName) {
//         int resId = 0;
//         if (context != null && !TextUtils.isEmpty(resType) && !TextUtils.isEmpty(resName)) {
//             if (rp != null) {
//                 try {
//                     Method pck = rp.getClass().getMethod("getResId", new Class[]{Context.class, String.class, String.class});
//                     pck.setAccessible(true);
//                     resId = ((Integer) pck.invoke(rp, new Object[]{context, resType, resName})).intValue();
//                 } catch (Throwable var5) {
//                     MobLog.getInstance().d(var5);
//                 }
//             }
//
//             if (resId <= 0) {
//                 String pck1 = context.getPackageName();
//                 if (TextUtils.isEmpty(pck1)) {
//                     return resId;
//                 }
//
//                 if (resId <= 0) {
//                     resId = context.getResources().getIdentifier(resName, resType, pck1);
//                     if (resId <= 0) {
//                         resId = context.getResources().getIdentifier(resName.toLowerCase(), resType, pck1);
//                     }
//                 }
//
//                 if (resId <= 0) {
//                     Log.w("MobTools", "failed to parse " + resType + " resource \"" + resName + "\"");
//                 }
//             }
//
//             return resId;
//         } else {
//             return resId;
//         }
//     }
//
//     public static int getBitmapRes(Context context, String resName) {
//         int resId = getResId(context, "drawable", resName);
//         if (resId <= 0) {
//             resId = getResId(context, "mipmap", resName);
//         }
//
//         return resId;
//     }
//
//     public static int getStringRes(Context context, String resName) {
//         return getResId(context, "string", resName);
//     }
//
//     public static int getStringArrayRes(Context context, String resName) {
//         return getResId(context, "array", resName);
//     }
//
//     public static int getLayoutRes(Context context, String resName) {
//         return getResId(context, "layout", resName);
//     }
//
//     public static int getStyleRes(Context context, String resName) {
//         return getResId(context, "style", resName);
//     }
//
//     public static int getIdRes(Context context, String resName) {
//         return getResId(context, "id", resName);
//     }
//
//     public static int getColorRes(Context context, String resName) {
//         return getResId(context, "color", resName);
//     }
//
//     public static int getRawRes(Context context, String resName) {
//         return getResId(context, "raw", resName);
//     }
//
//     public static int getPluralsRes(Context context, String resName) {
//         return getResId(context, "plurals", resName);
//     }
//
//     public static int getAnimRes(Context context, String resName) {
//         return getResId(context, "anim", resName);
//     }
//
//     public static String getCacheRoot(Context context) {
//         String appDir = context.getFilesDir().getAbsolutePath() + "/Mob/";
//         DeviceHelper helper = DeviceHelper.getInstance(context);
//         if (helper.getSdcardState()) {
//             appDir = helper.getSdcardPath() + "/Mob/";
//         }
//
//         File file = new File(appDir);
//         if (!file.exists()) {
//             file.mkdirs();
//         }
//
//         return appDir;
//     }
//
//     public static String getCachePath(Context context, String category) {
//         String appDir = context.getFilesDir().getAbsolutePath() + "/Mob/cache/";
//         DeviceHelper helper = DeviceHelper.getInstance(context);
//
//         try {
//             if (helper.getSdcardState()) {
//                 appDir = helper.getSdcardPath() + "/Mob/" + helper.getPackageName() + "/cache/";
//             }
//         } catch (Throwable var5) {
//             MobLog.getInstance().d(var5);
//         }
//
//         if (!TextUtils.isEmpty(category)) {
//             appDir = appDir + category + "/";
//         }
//
//         File file = new File(appDir);
//         if (!file.exists()) {
//             file.mkdirs();
//         }
//
//         return appDir;
//     }
//
//     public static String getImageCachePath(Context context) {
//         return getCachePath(context, "images");
//     }
//
//     public static void clearCache(Context context) throws Throwable {
//         String path = getCachePath(context, (String) null);
//         File cacheFolder = new File(path);
//         deleteFileAndFolder(cacheFolder);
//     }
//
//     public static void deleteFilesInFolder(File folder) throws Throwable {
//         if (folder != null && folder.exists()) {
//             if (folder.isFile()) {
//                 folder.delete();
//             } else {
//                 String[] names = folder.list();
//                 if (names != null && names.length > 0) {
//                     String[] var2 = names;
//                     int var3 = names.length;
//
//                     for (int var4 = 0; var4 < var3; ++var4) {
//                         String name = var2[var4];
//                         File f = new File(folder, name);
//                         if (f.isDirectory()) {
//                             deleteFilesInFolder(f);
//                         } else {
//                             f.delete();
//                         }
//                     }
//                 }
//             }
//         }
//     }
//
//     public static void deleteFileAndFolder(File folder) throws Throwable {
//         if (folder != null && folder.exists()) {
//             if (folder.isFile()) {
//                 folder.delete();
//             } else {
//                 String[] names = folder.list();
//                 if (names != null && names.length > 0) {
//                     String[] var2 = names;
//                     int var3 = names.length;
//
//                     for (int var4 = 0; var4 < var3; ++var4) {
//                         String name = var2[var4];
//                         File f = new File(folder, name);
//                         if (f.isDirectory()) {
//                             deleteFileAndFolder(f);
//                         } else {
//                             f.delete();
//                         }
//                     }
//
//                     folder.delete();
//                 } else {
//                     folder.delete();
//                 }
//             }
//         }
//     }
//
//     public static String toWordText(String text, int lenInWord) {
//         char[] cText = text.toCharArray();
//         int count = lenInWord * 2;
//         StringBuilder sb = new StringBuilder();
//         char[] var5 = cText;
//         int var6 = cText.length;
//
//         for (int var7 = 0; var7 < var6; ++var7) {
//             char ch = var5[var7];
//             count -= ch < 256 ? 1 : 2;
//             if (count < 0) {
//                 return sb.toString();
//             }
//
//             sb.append(ch);
//         }
//
//         return sb.toString();
//     }
//
//     public static int getTextLengthInWord(String text) {
//         char[] cText = text == null ? new char[0] : text.toCharArray();
//         int count = 0;
//
//         for (int i = 0; i < cText.length; ++i) {
//             count += cText[i] < 256 ? 1 : 2;
//         }
//
//         return count;
//     }
//
//     public static long strToDate(String strDate) {
//         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//         ParsePosition pos = new ParsePosition(0);
//         Date strtodate = formatter.parse(strDate, pos);
//         return strtodate.getTime();
//     }
//
//     public static long dateStrToLong(String strDate) {
//         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//         ParsePosition pos = new ParsePosition(0);
//         Date strtodate = formatter.parse(strDate, pos);
//         return strtodate.getTime();
//     }
//
//     public static Date longToDate(long time) {
//         Calendar cal = Calendar.getInstance();
//         cal.setTimeInMillis(time);
//         return cal.getTime();
//     }
//
//     public static String longToTime(long time, int level) {
//         String format = "yyyy-MM-dd kk:mm:ss";
//         switch (level) {
//             case 1:
//                 format = "yyyy";
//                 break;
//             case 2:
//                 format = "yyyy-MM";
//             case 3:
//             case 4:
//             case 6:
//             case 7:
//             case 8:
//             case 9:
//             case 11:
//             default:
//                 break;
//             case 5:
//                 format = "yyyy-MM-dd";
//                 break;
//             case 10:
//                 format = "yyyy-MM-dd kk";
//                 break;
//             case 12:
//                 format = "yyyy-MM-dd kk:mm";
//         }
//
//         SimpleDateFormat sdf = new SimpleDateFormat(format);
//         return sdf.format(Long.valueOf(time));
//     }
//
//     public static long dateToLong(String date) {
//         try {
//             Date t = new Date(date);
//             Calendar cal = Calendar.getInstance();
//             cal.setTime(t);
//             return cal.getTimeInMillis();
//         } catch (Throwable var3) {
//             MobLog.getInstance().w(var3);
//             return 0L;
//         }
//     }
//
//     public static int[] covertTimeInYears(long time) {
//         long delta = System.currentTimeMillis() - time;
//         if (delta <= 0L) {
//             return new int[]{0, 0};
//         } else {
//             delta /= 1000L;
//             if (delta < 60L) {
//                 return new int[]{(int) delta, 0};
//             } else {
//                 delta /= 60L;
//                 if (delta < 60L) {
//                     return new int[]{(int) delta, 1};
//                 } else {
//                     delta /= 60L;
//                     if (delta < 24L) {
//                         return new int[]{(int) delta, 2};
//                     } else {
//                         delta /= 24L;
//                         if (delta < 30L) {
//                             return new int[]{(int) delta, 3};
//                         } else {
//                             delta /= 30L;
//                             if (delta < 12L) {
//                                 return new int[]{(int) delta, 4};
//                             } else {
//                                 delta /= 12L;
//                                 return new int[]{(int) delta, 5};
//                             }
//                         }
//                     }
//                 }
//             }
//         }
//     }
//
//     public static Uri pathToContentUri(Context context, String imagePath) {
//         Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=? ", new String[]{imagePath}, (String) null);
//         if (cursor != null && cursor.moveToFirst()) {
//             int imageFile1 = cursor.getInt(cursor.getColumnIndex("_id"));
//             Uri values1 = Uri.parse("content://media/external/images/media");
//             return Uri.withAppendedPath(values1, "" + imageFile1);
//         } else {
//             File imageFile = new File(imagePath);
//             if (imageFile.exists()) {
//                 ContentValues values = new ContentValues();
//                 values.put("_data", imagePath);
//                 Uri baseUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//                 return context.getContentResolver().insert(baseUri, values);
//             } else {
//                 return null;
//             }
//         }
//     }
//
//     public static String contentUriToPath(Context context, Uri uri) {
//         if (uri == null) {
//             return null;
//         } else if ((new File(uri.getPath())).exists()) {
//             return uri.getPath();
//         } else {
//             String path = null;
//
//             try {
//                 Cursor t = null;
//                 if (Build.VERSION.SDK_INT >= 19) {
//                     Class clzDocumentsContract = Class.forName("android.provider.DocumentsContract");
//                     Method isDocumentUri = clzDocumentsContract.getMethod("isDocumentUri", new Class[]{Context.class, Uri.class});
//                     isDocumentUri.setAccessible(true);
//                     if (Boolean.TRUE.equals(isDocumentUri.invoke((Object) null, new Object[]{context, uri}))) {
//                         Method getDocumentId = clzDocumentsContract.getMethod("getDocumentId", new Class[]{Uri.class});
//                         getDocumentId.setAccessible(true);
//                         String wholeID = String.valueOf(getDocumentId.invoke((Object) null, new Object[]{uri}));
//                         String id = wholeID.split(":")[1];
//                         String[] column = new String[]{"_data"};
//                         String sel = "_id=?";
//                         String[] args = new String[]{id};
//                         t = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, column, sel, args, (String) null);
//                     }
//                 }
//
//                 if (t == null) {
//                     t = context.getContentResolver().query(uri, (String[]) null, (String) null, (String[]) null, (String) null);
//                 }
//
//                 if (t != null) {
//                     if (t.moveToFirst()) {
//                         path = t.getString(t.getColumnIndex("_data"));
//                     }
//
//                     t.close();
//                 }
//             } catch (Throwable var12) {
//                 MobLog.getInstance().w(var12);
//                 path = null;
//             }
//
//             return path;
//         }
//     }
//
//     public static Uri videoPathToContentUri(Context context, String videoPath) {
//         Cursor cursor = context.getContentResolver().query(android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=? ", new String[]{videoPath}, (String) null);
//         if (cursor != null && cursor.moveToFirst()) {
//             int videoFile1 = cursor.getInt(cursor.getColumnIndex("_id"));
//             Uri values1 = Uri.parse("content://media/external/video/media");
//             return Uri.withAppendedPath(values1, "" + videoFile1);
//         } else {
//             File videoFile = new File(videoPath);
//             if (videoFile.exists()) {
//                 ContentValues values = new ContentValues();
//                 values.put("_data", videoPath);
//                 Uri baseUri = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
//                 return context.getContentResolver().insert(baseUri, values);
//             } else {
//                 return null;
//             }
//         }
//     }
//
//     public static synchronized Uri getMediaUri(Context context, String filePath, String mimeType) {
//         final Object object = new Object();
//         Uri result = null;
//         mediaUri = null;
//         MediaScannerConnection.scanFile(context, new String[]{filePath}, new String[]{mimeType}, new MediaScannerConnection.OnScanCompletedListener() {
//             public void onScanCompleted(String path, Uri uri) {
//                 ResHelperBing.mediaUri = uri;
//                 Object var3 = object;
//                 synchronized (object) {
//                     object.notifyAll();
//                 }
//             }
//         });
//
//         try {
//             if (mediaUri == null) {
//                 synchronized (object) {
//                     object.wait(10000L);
//                 }
//             }
//         } catch (InterruptedException var8) {
//             ;
//         }
//
//         result = mediaUri;
//         mediaUri = null;
//         return result;
//     }
//
//     public static String encodeUrl(Bundle parameters) {
//         if (parameters == null) {
//             return "";
//         } else {
//             StringBuilder sb = new StringBuilder();
//             boolean first = true;
//
//             String key;
//             Object value;
//             for (Iterator var3 = parameters.keySet().iterator(); var3.hasNext(); sb.append(Data.urlEncode(key) + "=" + Data.urlEncode(String.valueOf(value)))) {
//                 key = (String) var3.next();
//                 value = parameters.get(key);
//                 if (value == null) {
//                     value = "";
//                 }
//
//                 if (first) {
//                     first = false;
//                 } else {
//                     sb.append("&");
//                 }
//             }
//
//             return sb.toString();
//         }
//     }
//
//     public static String encodeUrl(ArrayList<KVPair<String>> values) {
//         if (values == null) {
//             return null;
//         } else {
//             StringBuilder sb = new StringBuilder();
//             int i = 0;
//             Iterator var3 = values.iterator();
//
//             while (var3.hasNext()) {
//                 KVPair pair = (KVPair) var3.next();
//                 if (i > 0) {
//                     sb.append('&');
//                 }
//
//                 String key = pair.name;
//                 String value = (String) pair.value;
//                 if (key != null) {
//                     if (value == null) {
//                         value = "";
//                     }
//
//                     sb.append(Data.urlEncode(key) + "=" + Data.urlEncode(value));
//                     ++i;
//                 }
//             }
//
//             return sb.toString();
//         }
//     }
//
//     public static Bundle urlToBundle(String url) {
//         int index = url.indexOf("://");
//         if (index >= 0) {
//             url = "http://" + url.substring(index + 1);
//         } else {
//             url = "http://" + url;
//         }
//
//         try {
//             URL e = new URL(url);
//             Bundle b = decodeUrl(e.getQuery());
//             b.putAll(decodeUrl(e.getRef()));
//             return b;
//         } catch (Throwable var4) {
//             MobLog.getInstance().w(var4);
//             return new Bundle();
//         }
//     }
//
//     public static Bundle decodeUrl(String s) {
//         Bundle params = new Bundle();
//         if (s != null) {
//             String[] array = s.split("&");
//             String[] var3 = array;
//             int var4 = array.length;
//
//             for (int var5 = 0; var5 < var4; ++var5) {
//                 String parameter = var3[var5];
//                 String[] v = parameter.split("=");
//                 if (v.length >= 2 && v[1] != null) {
//                     params.putString(URLDecoder.decode(v[0]), URLDecoder.decode(v[1]));
//                 } else {
//                     params.putString(URLDecoder.decode(v[0]), "");
//                 }
//             }
//         }
//
//         return params;
//     }
//
//     public static int parseInt(String string) throws Throwable {
//         return parseInt(string, 10);
//     }
//
//     public static int parseInt(String string, int radix) throws Throwable {
//         return Integer.parseInt(string, radix);
//     }
//
//     public static long parseLong(String string) throws Throwable {
//         return parseLong(string, 10);
//     }
//
//     public static long parseLong(String string, int radix) throws Throwable {
//         return Long.parseLong(string, radix);
//     }
//
//     public static String toString(Object obj) {
//         return obj == null ? "" : obj.toString();
//     }
//
//     public static <T> T forceCast(Object obj) {
//         return forceCast(obj, (Object) null);
//     }
//
//     public static <T> T forceCast(Object obj, T defValue) {
//         if (obj == null) {
//             return defValue;
//         } else {
//             if (obj instanceof Byte) {
//                 byte value = ((Byte) obj).byteValue();
//                 if (defValue instanceof Boolean) {
//                     return Boolean.valueOf(value != 0);
//                 }
//
//                 if (defValue instanceof Short) {
//                     return Short.valueOf((short) value);
//                 }
//
//                 if (defValue instanceof Character) {
//                     return Character.valueOf((char) value);
//                 }
//
//                 if (defValue instanceof Integer) {
//                     return Integer.valueOf(value);
//                 }
//
//                 if (defValue instanceof Float) {
//                     return Float.valueOf((float) value);
//                 }
//
//                 if (defValue instanceof Long) {
//                     return Long.valueOf((long) value);
//                 }
//
//                 if (defValue instanceof Double) {
//                     return Double.valueOf((double) value);
//                 }
//             } else if (obj instanceof Character) {
//                 char value1 = ((Character) obj).charValue();
//                 if (defValue instanceof Byte) {
//                     return Byte.valueOf((byte) value1);
//                 }
//
//                 if (defValue instanceof Boolean) {
//                     return Boolean.valueOf(value1 != 0);
//                 }
//
//                 if (defValue instanceof Short) {
//                     return Short.valueOf((short) value1);
//                 }
//
//                 if (defValue instanceof Integer) {
//                     return Integer.valueOf(value1);
//                 }
//
//                 if (defValue instanceof Float) {
//                     return Float.valueOf((float) value1);
//                 }
//
//                 if (defValue instanceof Long) {
//                     return Long.valueOf((long) value1);
//                 }
//
//                 if (defValue instanceof Double) {
//                     return Double.valueOf((double) value1);
//                 }
//             } else if (obj instanceof Short) {
//                 short value2 = ((Short) obj).shortValue();
//                 if (defValue instanceof Byte) {
//                     return Byte.valueOf((byte) value2);
//                 }
//
//                 if (defValue instanceof Boolean) {
//                     return Boolean.valueOf(value2 != 0);
//                 }
//
//                 if (defValue instanceof Character) {
//                     return Character.valueOf((char) value2);
//                 }
//
//                 if (defValue instanceof Integer) {
//                     return Integer.valueOf(value2);
//                 }
//
//                 if (defValue instanceof Float) {
//                     return Float.valueOf((float) value2);
//                 }
//
//                 if (defValue instanceof Long) {
//                     return Long.valueOf((long) value2);
//                 }
//
//                 if (defValue instanceof Double) {
//                     return Double.valueOf((double) value2);
//                 }
//             } else if (obj instanceof Integer) {
//                 int value3 = ((Integer) obj).intValue();
//                 if (defValue instanceof Byte) {
//                     return Byte.valueOf((byte) value3);
//                 }
//
//                 if (defValue instanceof Boolean) {
//                     return Boolean.valueOf(value3 != 0);
//                 }
//
//                 if (defValue instanceof Character) {
//                     return Character.valueOf((char) value3);
//                 }
//
//                 if (defValue instanceof Short) {
//                     return Short.valueOf((short) value3);
//                 }
//
//                 if (defValue instanceof Float) {
//                     return Float.valueOf((float) value3);
//                 }
//
//                 if (defValue instanceof Long) {
//                     return Long.valueOf((long) value3);
//                 }
//
//                 if (defValue instanceof Double) {
//                     return Double.valueOf((double) value3);
//                 }
//             } else if (obj instanceof Float) {
//                 float value4 = ((Float) obj).floatValue();
//                 if (defValue instanceof Byte) {
//                     return Byte.valueOf((byte) ((int) value4));
//                 }
//
//                 if (defValue instanceof Boolean) {
//                     return Boolean.valueOf(value4 != 0.0F);
//                 }
//
//                 if (defValue instanceof Character) {
//                     return Character.valueOf((char) ((int) value4));
//                 }
//
//                 if (defValue instanceof Short) {
//                     return Short.valueOf((short) ((int) value4));
//                 }
//
//                 if (defValue instanceof Integer) {
//                     return Integer.valueOf((int) value4);
//                 }
//
//                 if (defValue instanceof Long) {
//                     return Long.valueOf((long) value4);
//                 }
//
//                 if (defValue instanceof Double) {
//                     return Double.valueOf((double) value4);
//                 }
//             } else if (obj instanceof Long) {
//                 long value5 = ((Long) obj).longValue();
//                 if (defValue instanceof Byte) {
//                     return Byte.valueOf((byte) ((int) value5));
//                 }
//
//                 if (defValue instanceof Boolean) {
//                     return Boolean.valueOf(value5 != 0L);
//                 }
//
//                 if (defValue instanceof Character) {
//                     return Character.valueOf((char) ((int) value5));
//                 }
//
//                 if (defValue instanceof Short) {
//                     return Short.valueOf((short) ((int) value5));
//                 }
//
//                 if (defValue instanceof Integer) {
//                     return Integer.valueOf((int) value5);
//                 }
//
//                 if (defValue instanceof Float) {
//                     return Float.valueOf((float) value5);
//                 }
//
//                 if (defValue instanceof Double) {
//                     return Double.valueOf((double) value5);
//                 }
//             } else if (obj instanceof Double) {
//                 double value6 = ((Double) obj).doubleValue();
//                 if (defValue instanceof Byte) {
//                     return Byte.valueOf((byte) ((int) value6));
//                 }
//
//                 if (defValue instanceof Boolean) {
//                     return Boolean.valueOf(value6 != 0.0D);
//                 }
//
//                 if (defValue instanceof Character) {
//                     return Character.valueOf((char) ((int) value6));
//                 }
//
//                 if (defValue instanceof Short) {
//                     return Short.valueOf((short) ((int) value6));
//                 }
//
//                 if (defValue instanceof Integer) {
//                     return Integer.valueOf((int) value6);
//                 }
//
//                 if (defValue instanceof Float) {
//                     return Float.valueOf((float) value6);
//                 }
//
//                 if (defValue instanceof Long) {
//                     return Long.valueOf((long) value6);
//                 }
//             }
//
//             try {
//                 return obj;
//             } catch (Throwable var4) {
//                 return defValue;
//             }
//         }
//     }
//
//     public static boolean isLeapYear(int year) {
//         return year % 400 == 0 || year % 4 == 0 && year % 100 != 0;
//     }
//
//     public static boolean copyFile(String fromFilePath, String toFilePath) {
//         if (!TextUtils.isEmpty(fromFilePath) && !TextUtils.isEmpty(toFilePath)) {
//             if (!(new File(fromFilePath)).exists()) {
//                 return false;
//             } else {
//                 try {
//                     FileInputStream e = new FileInputStream(fromFilePath);
//                     FileOutputStream fosto = new FileOutputStream(toFilePath);
//                     copyFile(e, fosto);
//                     return true;
//                 } catch (Throwable var4) {
//                     return false;
//                 }
//             }
//         } else {
//             return false;
//         }
//     }
//
//     public static void copyFile(FileInputStream src, FileOutputStream dst) throws Throwable {
//         byte[] buf = new byte[65536];
//
//         for (int len = src.read(buf); len > 0; len = src.read(buf)) {
//             dst.write(buf, 0, len);
//         }
//
//         src.close();
//         dst.close();
//     }
//
//     public static long getFileSize(String path) throws Throwable {
//         if (TextUtils.isEmpty(path)) {
//             return 0L;
//         } else {
//             File file = new File(path);
//             return getFileSize(file);
//         }
//     }
//
//     public static long getFileSize(File file) throws Throwable {
//         if (!file.exists()) {
//             return 0L;
//         } else if (!file.isDirectory()) {
//             return file.length();
//         } else {
//             String[] names = file.list();
//             int size = 0;
//
//             for (int i = 0; i < names.length; ++i) {
//                 File f = new File(file, names[i]);
//                 size = (int) ((long) size + getFileSize(f));
//             }
//
//             return (long) size;
//         }
//     }
//
//     public static boolean saveObjectToFile(String filePath, Object object) {
//         if (!TextUtils.isEmpty(filePath)) {
//             File cacheFile = null;
//
//             try {
//                 cacheFile = new File(filePath);
//                 if (cacheFile.exists()) {
//                     cacheFile.delete();
//                 }
//
//                 if (!cacheFile.getParentFile().exists()) {
//                     cacheFile.getParentFile().mkdirs();
//                 }
//
//                 cacheFile.createNewFile();
//             } catch (Throwable var6) {
//                 var6.printStackTrace();
//                 cacheFile = null;
//             }
//
//             if (cacheFile != null) {
//                 try {
//                     FileOutputStream t = new FileOutputStream(cacheFile);
//                     GZIPOutputStream gzos = new GZIPOutputStream(t);
//                     ObjectOutputStream oos = new ObjectOutputStream(gzos);
//                     oos.writeObject(object);
//                     oos.flush();
//                     oos.close();
//                     return true;
//                 } catch (Throwable var7) {
//                     var7.printStackTrace();
//                 }
//             }
//         }
//
//         return false;
//     }
//
//     public static Object readObjectFromFile(String filePath) {
//         if (!TextUtils.isEmpty(filePath)) {
//             File cacheFile = null;
//
//             try {
//                 cacheFile = new File(filePath);
//                 if (!cacheFile.exists()) {
//                     cacheFile = null;
//                 }
//             } catch (Throwable var6) {
//                 var6.printStackTrace();
//                 cacheFile = null;
//             }
//
//             if (cacheFile != null) {
//                 try {
//                     FileInputStream t = new FileInputStream(cacheFile);
//                     GZIPInputStream gzis = new GZIPInputStream(t);
//                     ObjectInputStream ois = new ObjectInputStream(gzis);
//                     Object object = ois.readObject();
//                     ois.close();
//                     return object;
//                 } catch (Throwable var7) {
//                     var7.printStackTrace();
//                 }
//             }
//         }
//
//         return null;
//     }
// }
