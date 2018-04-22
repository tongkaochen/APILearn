package com.tifone.learn.photoswall.data.remote;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.tifone.learn.photoswall.data.IPhotosWallRepository;
import com.tifone.learn.photoswall.utils.AppExecutors;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tongkao.chen on 2018/3/13.
 */

public class RemotePhotosResource implements IPhotosWallRepository<Bitmap> {
    private static RemotePhotosResource INSTANCE;
    private static Object mObject = new Object();
    private AppExecutors mExecutors;
    private RemotePhotosResource() {
        mExecutors = new AppExecutors();
    }
    public static RemotePhotosResource getInstance() {
        if (INSTANCE == null) {
            synchronized (mObject) {
                if (INSTANCE == null) {
                    INSTANCE = new RemotePhotosResource();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getItem(final String key, final GetCallback<Bitmap> callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = downloadBitmap(key);
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
        mExecutors.networkIO().execute(runnable);
    }
    @Override
    public void addItem(final String key, Bitmap item) {


    }

    @Override
    public void cancelAll() {

    }

    @Override
    public void loadPhotos(LoadCallback callback) {

    }
    private Bitmap downloadBitmap(String imageUrl) {
        Bitmap bitmap = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(imageUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5 * 1000);
            connection.setReadTimeout(10 * 1000);
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

        }
        return bitmap;
    }
}
