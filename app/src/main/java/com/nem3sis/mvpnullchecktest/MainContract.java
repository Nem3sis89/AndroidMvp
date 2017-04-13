package com.nem3sis.mvpnullchecktest;

/**
 * Created by HW64 on 31/03/2017.
 */

public interface MainContract {

    interface View {
        void showMessage(String message);
    }

    interface Presenter {
        void onButtonClick();
        void getDateList();
    }
}
