package com.nem3sis.mvpnullchecktest;

import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Giorgio on 01/04/2017.
 */

public class MainDataStore {

    public interface MainDataStoreCallback {
        void onCurrentDate(String date);
        void onListUpdated(List<String> dateList);
    }

    private static MainDataStore mInstance;
    private boolean isRunning = false;
    private String currentDate;
    private List<String> dateList;
    private MainDataStoreCallback callback;

    private final String TAG = MainDataStore.class.getSimpleName();

    private MainDataStore(){
        this.dateList = new ArrayList<>();
    }

    public static MainDataStore getInstance(){
        if(mInstance == null){
            mInstance = new MainDataStore();
        }
        return mInstance;
    }

    public void register(MainDataStoreCallback callback){
        this.callback = callback;
    }

    public void unregister(){
        Log.d(TAG, "unregister: unregistering clients..");
        this.callback = null;
    }

    public void get(MainDataStoreCallback callback){
        this.callback = callback;
        Log.d(TAG, "get: client "+ callback.toString() + " registered");
        if(!TextUtils.isEmpty(currentDate)){
            Log.d(TAG, "get: returning cached result");
            sendResult();
        } else {
            Log.d(TAG, "get: cache not found, fetching new data");
            getCurrentDate();
        }
    }

    public void getDateList(MainDataStoreCallback callback){
        this.callback = callback;
        Log.d(TAG, "getDateList() called");
        if(!dateList.isEmpty()){
            Log.d(TAG, "getDateList: returning list");
            sendDateList();
        } else {
            Log.d(TAG, "getDateList: list is empty!");
        }
    }

    public void fetch(MainDataStoreCallback callback){
        this.callback = callback;
        getCurrentDate();
    }

    private void sendResult() {
        if(callback != null){
            Log.d(TAG, "sendResult: returning result to client " + callback.toString());
            callback.onCurrentDate(currentDate);
            callback.onListUpdated(dateList);
//            currentDate = null; //invalidate cache
        }
    }

    private void sendDateList(){
        if(callback != null){
            callback.onListUpdated(dateList);
        }
    }

    private void getCurrentDate(){
        if(isRunning)
            return;
        Log.d(TAG, "getCurrentDate: no running task found, creating new one..");
        isRunning = true;
        BackgroundTask task = new BackgroundTask(new BackgroundTask.TaskCallback() {
            @Override
            public void onFinish() {
                isRunning = false;
//                String formattedDate = SimpleDateFormat.getDateInstance().format(currentDate);
                currentDate = new Date(System.currentTimeMillis()).toString();
                dateList.add(currentDate);
                sendResult();
            }
        });
        task.startLongTask();
    }

}
