package com.tanya.dvtweatherapp.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Handles some threading
 * I'll use RxJava later on, this is not clean!
 */
public class AppExecutors {

    private static AppExecutors instance;

    public static AppExecutors getInstance(){
        if(instance == null){
            instance = new AppExecutors();
        }
        return instance;
    }

    private final Executor mDiskIO = Executors.newSingleThreadExecutor();

    private final Executor mMainThreadExecutor = new MainThreadExecutor();


    public Executor diskIO(){
        return mDiskIO;
    }

    public Executor mainThread(){
        return mMainThreadExecutor;
    }

    private static class MainThreadExecutor implements Executor{

        private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
