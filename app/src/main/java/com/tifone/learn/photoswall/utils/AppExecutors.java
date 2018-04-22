package com.tifone.learn.photoswall.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by tongkao.chen on 2018/3/13.
 */

public class AppExecutors {
    private static final int THREAD_COUNT = 3;
    private final Executor diskIO;
    private final Executor networkIO;
    private final Executor mainThread;
    AppExecutors (Executor diskIO, Executor networkIO, Executor mainThread) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }
    public AppExecutors() {
        this(new DiskIOThreadExecutor(), Executors.newFixedThreadPool(THREAD_COUNT),
                new MainThreadExecutor());
    }
    public Executor diskIO() {
        return diskIO;
    }
    public Executor networkIO() {
        return networkIO;
    }
    public Executor mainThread() {
        return mainThread;
    }
}
