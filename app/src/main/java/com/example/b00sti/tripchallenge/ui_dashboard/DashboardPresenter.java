package com.example.b00sti.tripchallenge.ui_dashboard;

import com.example.b00sti.tripchallenge.utils.ui.mvp_base.MvpPresenter;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-10
 */

class DashboardPresenter extends MvpPresenter<DashboardContract.View> implements DashboardContract.Presenter {
    private static final String TAG = "DashboardPresenter";

    DashboardPresenter(DashboardContract.View view) {
        super(view);
    }

    @Override
    public void refreshAllViews() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
