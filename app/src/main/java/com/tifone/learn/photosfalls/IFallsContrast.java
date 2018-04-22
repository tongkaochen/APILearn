package com.tifone.learn.photosfalls;

import android.graphics.Bitmap;

import com.tifone.learn.base.IBasePresenter;
import com.tifone.learn.base.IBaseView;

/**
 * Created by tongkao.chen on 2018/3/16.
 */

public interface IFallsContrast {

    interface IBaseFallsPresenter extends IBasePresenter {
        void getBitmap(String key);
        void addBitmap(String key, Bitmap bitmap);
        void cancelAll();
    }
    interface IBaseFallsView extends IBaseView<IBaseFallsPresenter> {
        void updateImage(String key, Bitmap bitmap);
    }
}
