package com.tifone.learn.utils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * Created by tongkao.chen on 2018/3/15.
 */

public class ActivityUtils {
    public static void addFragmentToActivity(FragmentManager fm, Fragment fragment, int resourceId) {
        if (fm == null || fragment == null) {
            return;
        }
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(resourceId, fragment);
        transaction.commit();
    }
}
