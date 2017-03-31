package com.nem3sis.mvpnullchecktest;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by HW64 on 31/03/2017.
 */

public class BackgroundTask {

    public interface TaskCallback {
        void onFinish();
    }

    private final int TIMER = 5000; //5s
    private Handler mMainHandler;
    private TaskCallback mCallback;

    public BackgroundTask(TaskCallback callback){
        mMainHandler = new Handler(Looper.getMainLooper()); //used to send result to main thread
        this.mCallback = callback;
    }

    public void startLongTask(){
        mMainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mCallback.onFinish();
                mCallback = null;
            }
        }, TIMER);
    }
}
