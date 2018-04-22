package com.tifone.learn.photoswall.utils;

import android.content.Context;

import com.tifone.learn.photoswall.data.PhotosLruCache;
import com.tifone.learn.photoswall.data.PhotosWallRepository;
import com.tifone.learn.photoswall.data.local.LocalPhotosResource;
import com.tifone.learn.photoswall.data.local.PhotosDatabase;
import com.tifone.learn.photoswall.data.remote.RemotePhotosResource;

/**
 * Created by tongkao.chen on 2018/3/15.
 */

public class Injection {
    public static PhotosWallRepository providePhotosWallRepository(Context context) {
        PhotosDatabase database = PhotosDatabase.getInstance(context);
        return PhotosWallRepository.getInstance(LocalPhotosResource.getInstance(database),
                RemotePhotosResource.getInstance(), PhotosLruCache.getInstance());

    }
}
