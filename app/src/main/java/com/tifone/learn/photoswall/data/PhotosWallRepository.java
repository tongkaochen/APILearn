package com.tifone.learn.photoswall.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by tongkao.chen on 2018/3/13.
 */

public class PhotosWallRepository implements IPhotosWallRepository<Bitmap> {

    private static PhotosWallRepository INSTANCE;
    private IPhotosWallRepository<Bitmap> mLocalResource;
    private IPhotosWallRepository<Bitmap> mRemoteResource;
    private IPhotosWallRepository<Bitmap> mLruCache;

    private PhotosWallRepository(IPhotosWallRepository localResource,
                                IPhotosWallRepository remoteResource,
                                IPhotosWallRepository lruCache) {
        mLocalResource = localResource;
        mRemoteResource = remoteResource;
        mLruCache = lruCache;
    }
    public static PhotosWallRepository getInstance(IPhotosWallRepository<Bitmap> localResource,
                                                   IPhotosWallRepository<Bitmap> remoteResource,
                                                   IPhotosWallRepository<Bitmap> lruCache) {
        if (INSTANCE == null) {
            INSTANCE = new PhotosWallRepository(localResource, remoteResource, lruCache);
        }
        return INSTANCE;
    }

    @Override
    public void getItem(final String key, final GetCallback<Bitmap> callback) {
        if (TextUtils.isEmpty(key)) {
            callback.noDataAvailable(key);
        }
        getPhotoFromLruCache(key, new GetCallback<Bitmap>() {
            @Override
            public void onDataGot(String key, Bitmap item) {
                callback.onDataGot(key, item);
            }

            @Override
            public void noDataAvailable(String key) {
                getPhotoFromLocalResource(key, new GetCallback<Bitmap>() {
                    @Override
                    public void onDataGot(String key, Bitmap item) {
                        callback.onDataGot(key, item);
                        mLruCache.addItem(key, item);
                    }

                    @Override
                    public void noDataAvailable(String key) {
                        getPhotoFromRemoteResource(key, new GetCallback<Bitmap>() {
                            @Override
                            public void onDataGot(String key, Bitmap item) {
                                callback.onDataGot(key, item);
                                mLruCache.addItem(key, item);
                                mLocalResource.addItem(key, item);
                            }

                            @Override
                            public void noDataAvailable(String key) {
                                callback.noDataAvailable(key);
                            }
                        });
                    }
                });
            }
        });
    }
    private void getPhotoFromLruCache(String key, GetCallback<Bitmap> callback) {
        Log.d("tifone", "getPhotoFromLruCache");
        mLruCache.getItem(key, callback);
    }
    private void getPhotoFromLocalResource(String key, GetCallback<Bitmap> callback) {
        Log.d("tifone", "getPhotoFromLocalResource");
        mLocalResource.getItem(key, callback);
    }
    private void getPhotoFromRemoteResource(String key, GetCallback<Bitmap> callback) {
        Log.d("tifone", "getPhotoFromRemoteResource");
        mRemoteResource.getItem(key, callback);
    }

    @Override
    public void addItem(String key, Bitmap item) {

    }

    @Override
    public void cancelAll() {

    }

    @Override
    public void loadPhotos(LoadCallback<Bitmap> callback) {

    }
}
