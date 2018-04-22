package com.tifone.ui.surfaceview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by tongkao.chen on 2018/4/2.
 */

public class SurfaceViewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CustomSurfaceView(this));

    }
}
