package com.example.b00sti.tripchallenge.ui_dashboard;


import com.example.skeleton.ui.mvp_base.setView;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-10
 */

class DashboardPresenter extends setView<DashboardContract.View> implements DashboardContract.Presenter {
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
