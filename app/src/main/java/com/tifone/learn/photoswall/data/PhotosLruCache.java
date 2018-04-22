package com.tifone.learn.photoswall.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.tifone.learn.photoswall.utils.AppExecutors;

/**
 * Created by tongkao.chen on 2018/3/13.
 */

public class PhotosLruCache  implements IPhotosWallRepository<Bitmap>{
    private static PhotosLruCache INSTANCE = null;
    private LruCache<String, Bitmap> mCache;
    private AppExecutors mAppExecutors;

    private PhotosLruCache(int maxSize) {
        mCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount()/1024;
            }
        };
        mAppExecutors = new AppExecutors();

    }
    public static PhotosLruCache getInstance() {
        if (INSTANCE == null) {
            int maxSize = (int)Runtime.getRuntime().maxMemory();
            INSTANCE = new PhotosLruCache(maxSize);
        }
        return INSTANCE;
    }
    public Bitmap getPhotoByKey(String key) {
        return mCache.get(key);
    }

    @Override
    public void getItem(final String key, final GetCallback<Bitmap> callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = mCache.get(key);
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (bitmap == null) {
                            callback.noDataAvailable(key);
                        } else {
                            callback.onDataGot(key, bitmap);
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void addItem(final String key, final Bitmap bitmap) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (mCache.get(key) == null) {
                    mCache.put(key, bitmap);
                }
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void cancelAll() {

    }

    @Override
    public void loadPhotos(LoadCallback<Bitmap> callback) {
        // Ignore
    }


}
