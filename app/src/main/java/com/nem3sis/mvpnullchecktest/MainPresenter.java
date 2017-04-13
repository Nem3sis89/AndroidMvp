package com.nem3sis.mvpnullchecktest;

import java.util.List;

/**
 * Created by HW64 on 31/03/2017.
 */

public class MainPresenter extends MainPresenterNullCheck implements MainContract.Presenter, MainDataStore.MainDataStoreCallback {


    private final String TAG = MainPresenter.class.getSimpleName();

    @Override
    public void onButtonClick() {
        MainDataStore.getInstance().fetchData(this);
    }

    @Override
    public void getDateList() {
        MainDataStore.getInstance().getDateList(this);
    }


    @Override
    protected void onDetachView() {
        super.onDetachView();
        MainDataStore.getInstance().unregister();
    }

    @Override
    protected void onAttachView(MainContract.View view) {
        super.onAttachView(view);
        MainDataStore.getInstance().getData(this); // load data or cache
    }

    @Override
    public void onCurrentDate(String date) {
        getView().showMessage(date);
    }

    @Override
    public void onListUpdated(List<String> dateList) {

    }
}
