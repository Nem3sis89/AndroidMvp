package com.nem3sis.mvpnullchecktest;

/**
 * Created by HW64 on 31/03/2017.
 */

public class MainPresenter extends MainPresenterNullCheck implements MainContract.Presenter {

    private final String message = "OK";

    @Override
    public void onButtonClick() {
        BackgroundTask task = new BackgroundTask(new BackgroundTask.TaskCallback() {
            @Override
            public void onFinish() {
                getView().showToast(message);
            }
        });
        task.startLongTask();
    }


}
