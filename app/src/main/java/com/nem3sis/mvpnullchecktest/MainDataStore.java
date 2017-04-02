package com.nem3sis.mvpnullchecktest;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by Giorgio on 01/04/2017.
 */

public class MainDataStore {

    public interface MainDataStoreCallback {
        void onFinishLoad(String message);
    }

    private static MainDataStore mInstance;
    private boolean isRunning = false;
    private String messageResult;
    private MainDataStoreCallback callback;

    private final String TAG = MainDataStore.class.getSimpleName();

    private MainDataStore(){}

    public static MainDataStore getInstance(){
        if(mInstance == null){
            mInstance = new MainDataStore();
        }
        return mInstance;
    }

    public void unregister(){
        Log.d(TAG, "unregister: unregistering clients..");
        this.callback = null;
    }

    public void get(MainDataStoreCallback callback){
        this.callback = callback;
        Log.d(TAG, "get: client "+ callback.toString() + " registered");
        if(!TextUtils.isEmpty(messageResult)){
            Log.d(TAG, "get: returning cached result");
            sendResult();
        } else {
            Log.d(TAG, "get: cache not found, fetching new data");
            doLongTask();
        }
    }

    public void fetch(MainDataStoreCallback callback){
        this.callback = callback;
        doLongTask();
    }

    private void sendResult() {
        if(callback != null){
            Log.d(TAG, "sendResult: returning result to client " + callback.toString());
            callback.onFinishLoad(messageResult);
            unregister();
        }
    }

    private void doLongTask(){
        if(isRunning)
            return;
        Log.d(TAG, "doLongTask: no running task found, creating new one..");
        isRunning = true;
        BackgroundTask task = new BackgroundTask(new BackgroundTask.TaskCallback() {
            @Override
            public void onFinish() {
                isRunning = false;
                messageResult = "OK";
                    sendResult();
            }
        });
        task.startLongTask();
    }

}
