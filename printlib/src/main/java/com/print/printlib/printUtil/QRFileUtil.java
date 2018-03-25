package com.print.printlib.printUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.print.printlib.PrintPlan;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 二维码图片暂存
 */
public final class QRFileUtil {



    //==========================
    //文件缓存目录等处理
    //==========================
    public static boolean isExternalStorageRemovable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    public static boolean hasExternalCacheDir() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    public static File getExternalCacheDir() {
        File cacheFile = null;
        if (hasExternalCacheDir()) {
            cacheFile = PrintPlan.mContext.getExternalCacheDir();
        }
        if (cacheFile == null) {
            // Before Froyo we need to construct the external cache dir ourselves
            final String cacheDir = "/Android/data/" +  PrintPlan.mContext.getPackageName() + "/cache/";
            cacheFile = new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
        }
        return cacheFile;
    }

    public static String getDiskCacheDir(String... folders) {
        return getDiskCacheFile(folders) + File.separator;
    }

    public static File getDiskCacheFile(String... folders) {
        String cacheRootPath = isExternalStorageRemovable() ? getExternalCacheDir().getPath()
                :  PrintPlan.mContext.getCacheDir().getPath();
        if (folders != null) {
            StringBuilder temp = new StringBuilder(cacheRootPath);
            for (String s : folders) {
                temp.append(File.separator).append(s);
            }
            cacheRootPath = temp.toString();
        }
        File file = new File(cacheRootPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }


}
