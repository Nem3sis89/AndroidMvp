package com.nem3sis.mvpnullchecktest;

import android.util.Log;

/**
 * Created by HW64 on 31/03/2017.
 */

public abstract class MainPresenterNullCheck {
    private MainContract.View mView;
    private MainContract.View mDummyView;
    private String TAG = MainPresenterNullCheck.class.getSimpleName();

    protected MainPresenterNullCheck(){
        this.mDummyView = new MainContract.View() {
            @Override
            public void showToast(String message) {
                //do nothing
            }
        };
    }

    protected void onAttachView(MainContract.View view){
        setView(view);
    }

    protected void onDetachView(){
        mView = null;
    }

    protected void setView(MainContract.View view) {
        this.mView = view;
    }

    protected MainContract.View getView(){
        if(mView == null){
            Log.e(TAG, "getView: view is null, returning dummy view.");
            return mDummyView;
        } else {
            return mView;
        }
    }

}
