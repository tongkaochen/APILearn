package com.tifone.ui.glide;

import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.widget.ImageView;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Created by tongkao.chen on 2018/5/10.
 */
@GlideModule
public class ImageLoader extends AppGlideModule {
    public static void setupImage(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }
}
