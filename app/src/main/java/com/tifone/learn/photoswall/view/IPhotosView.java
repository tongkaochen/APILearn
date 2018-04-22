package com.tifone.learn.photoswall.view;

import android.graphics.Bitmap;

import com.tifone.learn.base.IBaseView;
import com.tifone.learn.photoswall.presenter.IPhotosPresenter;

/**
 * Created by tongkao.chen on 2018/3/13.
 */

public interface IPhotosView extends IBaseView<IPhotosPresenter> {
    void setImageViewByTag(String tag, Bitmap bitmap);
}
