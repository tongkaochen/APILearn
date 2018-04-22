package com.tifone.learn.photoswall.data.local;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.tifone.learn.photoswall.data.IPhotosWallRepository;
import com.tifone.learn.photoswall.data.PhotoItem;
import com.tifone.learn.photoswall.utils.AppExecutors;
import com.tifone.learn.photoswall.utils.BitmapUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by tongkao.chen on 2018/3/13.
 */

public class LocalPhotosResource implements IPhotosWallRepository<Bitmap> {
    private static final String PATH_SAVE = "/photos_cache/";
    private static LocalPhotosResource INSTANCE;
    private static Object mObject = new Object();
    private AppExecutors mExecutors;
    private PhotosDao mPhotosDao;
    private LocalPhotosResource(PhotosDatabase database) {
        mExecutors = new AppExecutors();
        mPhotosDao = database.photosDao();
    }
    public static LocalPhotosResource getInstance(PhotosDatabase database) {
        if (INSTANCE == null) {
            synchronized (mObject) {
                INSTANCE = new LocalPhotosResource(database);
            }
        }
        return INSTANCE;
    }

    @Override
    public void getItem(final String key, final GetCallback<Bitmap> callback) {
        // get photo uri from database, and then get bitmap with specified uri
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final PhotoItem item = mPhotosDao.getPhotoItem(key);
                if (item == null) {
                    callback.noDataAvailable(key);
                    return;
                }
                String path = item.getPath();
                final Bitmap bitmap = BitmapUtils.getBitmapFromDiskWithSize(path, 90, 90);
                mExecutors.mainThread().execute(new Runnable() {
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
        mExecutors.diskIO().execute(runnable);
    }


    @Override
    public void addItem(final String key, final Bitmap bitmap) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                PhotoItem item = mPhotosDao.getPhotoItem(key);
                String savedPath = BitmapUtils.saveBitmapToFile(PATH_SAVE, bitmap, item);
                if (TextUtils.isEmpty(savedPath)) {
                    return;
                }
                File bitmap = new File(savedPath);
                String fileName = bitmap.getName();
                mPhotosDao.insertItem(new PhotoItem(key, savedPath, fileName));
            }
        };
        mExecutors.diskIO().execute(runnable);

    }

    @Override
    public void cancelAll() {

    }

    @Override
    public void loadPhotos(LoadCallback callback) {

    }
}
