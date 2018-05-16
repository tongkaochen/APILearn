package com.tifone.ui.glide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.tifone.ui.R;

/**
 * Created by tongkao.chen on 2018/5/10.
 */

public class GlideActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glide_activity_main_layout);
        ImageView imageView = findViewById(R.id.glide_iv);
        String url = "http://p9.pstatp.com/list/300x196/207c000e549a17910c1c.webp";
        ImageLoader.setupImage(this, url, imageView);
    }
}
