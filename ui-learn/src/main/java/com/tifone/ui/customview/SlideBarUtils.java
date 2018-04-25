package com.tifone.ui.customview;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tongkao.chen on 2018/4/24.
 */

public class SlideBarUtils {
    private static final String PREF_KEY = "slide_bar_shared";
    private static final String INDEX_POSITION = "index_position";
    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
    }
    public static void setIndex(Context context, int position) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt(INDEX_POSITION, position);
        editor.apply();
    }
    public static int getIndex(Context context) {
        return getSharedPreferences(context).getInt(INDEX_POSITION, -1);
    }
}
