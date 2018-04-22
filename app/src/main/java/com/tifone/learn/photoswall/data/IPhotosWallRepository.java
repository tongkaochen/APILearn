package com.tifone.learn.photoswall.data;

import android.content.Context;
import android.graphics.Bitmap;

import com.tifone.learn.base.IBaseDataResource;

import java.util.List;

/**
 * Created by tongkao.chen on 2018/3/13.
 */

public interface IPhotosWallRepository<T> extends IBaseDataResource {
    interface GetCallback<T> {
        void onDataGot(String key, T item);
        void noDataAvailable(String key);
    }
    interface LoadCallback<T> {
        void onDataLoaded(List<T> itmes);
        void noDataAvailabled();
    }
    void getItem(final String key, final GetCallback<T> callback);
    void addItem(final String key, final T item);
    void cancelAll();
    void loadPhotos(LoadCallback<T> callback);
}
