package com.nem3sis.mvpnullchecktest;

/**
 * Created by HW64 on 31/03/2017.
 */

public interface MainContract {

    interface View {
        void showToast(String message);
    }

    interface Presenter {
        void onButtonClick();
    }
}
