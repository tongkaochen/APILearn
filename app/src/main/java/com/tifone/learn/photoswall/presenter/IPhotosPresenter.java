package com.tifone.learn.photoswall.presenter;

import android.graphics.Bitmap;

import com.tifone.learn.base.IBasePresenter;

import java.util.Set;

/**
 * Created by tongkao.chen on 2018/3/13.
 */

public interface IPhotosPresenter extends IBasePresenter {
    void addTask(String imageUrl);
    void removeTask(String imageUrl);
    void loadPhoto(String key);
    void setImage(String key, Bitmap bitmap);
}
