package com.tifone.learn.photoswall.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

/**
 * Created by tongkao.chen on 2018/3/13.
 */

public class MainThreadExecutor implements Executor {
    /**
     * Let command run on MainThread
     */
    private Handler mainHandler = new Handler(Looper.getMainLooper());
    @Override
    public void execute(@NonNull Runnable command) {
        mainHandler.post(command);
    }
}
