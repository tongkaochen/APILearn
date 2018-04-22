package com.tifone.learn.photoswall.utils;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by tongkao.chen on 2018/3/13.
 */

public class DiskIOThreadExecutor implements Executor {
    private Executor mExecutor;
    public DiskIOThreadExecutor() {
        mExecutor = Executors.newSingleThreadExecutor();
    }
    @Override
    public void execute(@NonNull Runnable command) {
        mExecutor.execute(command);
    }
}
