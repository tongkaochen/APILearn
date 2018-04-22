package com.tifone.learn.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;

/**
 * Created by tongkao.chen on 2018/3/15.
 */

public class PermissionUtils {
    public static void verifyPermissions(Activity activity, String targetPermission, String[] permissionGroup, int requireCode) {
        if (TextUtils.isEmpty(targetPermission) || activity == null) {
            return;
        }
        int permissions = ActivityCompat.checkSelfPermission(activity, targetPermission);
        if (permissions != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, permissionGroup, requireCode);
        }
    }
}
