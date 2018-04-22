package com.tifone.learn.photoswall;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.AttributeSet;
import android.view.View;

import com.tifone.learn.R;

import java.net.URL;

/**
 * Created by tongkao.chen on 2018/3/15.
 */

public class PhotosPermissionActivity extends Activity{
    private static final String[] REQUIRE_PERMISSIONS = {
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.READ_EXTERNAL_STORAGE"
    };
    private static final int PERMISSION_REQUEST_CODE = 1000;
    private static final int ACTIVITY_REQUEST_CODE = 1001;
    private AlertDialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permission = ActivityCompat.checkSelfPermission(this, REQUIRE_PERMISSIONS[0]);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                showDialogToAllowPermissions();
            } else {
                permissionGranted();
            }
        } else {
            permissionGranted();
        }
    }
    private void showDialogToAllowPermissions() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.permission_dialog_title)
                .setMessage(String.format(getString(R.string.permission_dialog_summary), REQUIRE_PERMISSIONS[0]))
                .setPositiveButton(R.string.permission_dialog_button_allow, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startRequestPermission();
                    }
                })
                .setNegativeButton(R.string.permission_dialog_button_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setCancelable(false).show();
    }
    private void startRequestPermission() {
        ActivityCompat.requestPermissions(this, REQUIRE_PERMISSIONS, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            int index = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                boolean granted = false;
                for (Integer result : grantResults) {
                    index++;
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        granted = true;
                        continue;
                    }
                    granted = false;
                    break;
                }
                if (!granted) {
                    showDialogToGoSettings(index);
                } else {
                    permissionGranted();
                }
            }
        }
    }

    private void showDialogToGoSettings(int index) {
        if (index - 1 < 0 || REQUIRE_PERMISSIONS.length < index) {
            index = 0;
        }
        mDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.permission_allow_manually_title)
                .setMessage(String.format(getString(R.string.permission_allow_manually_summary),
                        REQUIRE_PERMISSIONS[index]))
                .setPositiveButton(R.string.permission_allow_manually_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        goToSettings();
                    }
                })
                .setNegativeButton(R.string.permission_dialog_button_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setCancelable(false).show();
    }
    private void goToSettings() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_REQUEST_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(this, REQUIRE_PERMISSIONS[0]) == PackageManager.PERMISSION_GRANTED) {
                    if (mDialog != null && mDialog.isShowing()) {
                        mDialog.dismiss();
                    }
                    permissionGranted();
                } else {
                    showDialogToGoSettings(0);
                }

            }
        }
    }
    private void permissionGranted() {
        Intent intent = new Intent();
        intent.setClass(this, PhotosWallActivity.class);
        startActivity(intent);
        finish();
    }
}
