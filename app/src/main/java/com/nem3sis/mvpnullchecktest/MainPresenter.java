package com.nem3sis.mvpnullchecktest;

/**
 * Created by HW64 on 31/03/2017.
 */

public class MainPresenter extends MainPresenterNullCheck implements MainContract.Presenter {

    private final String message = "OK";
    private final String TAG = MainPresenter.class.getSimpleName();

    @Override
    public void onButtonClick() {
        MainDataStore.getInstance().get(new MainDataStore.MainDataStoreCallback() {
            @Override
            public void onFinishLoad(String message) {
                getView().showToast(message);
            }
        });
    }

    @Override
    protected void onDetachView() {
        super.onDetachView();
        MainDataStore.getInstance().unregister();
    }

    //    @Override
//    public void onButtonClick() {
//        BackgroundTask task = new BackgroundTask(new BackgroundTask.TaskCallback() {
//            @Override
//            public void onFinish() {
//                getView().showToast(message);
//            }
//        });
//        task.startLongTask();
//    }




}
