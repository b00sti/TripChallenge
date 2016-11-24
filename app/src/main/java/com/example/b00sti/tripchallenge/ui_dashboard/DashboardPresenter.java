package com.example.b00sti.tripchallenge.ui_dashboard;


import com.example.skeleton.ui.mvp_base.MvpPresenter;

import org.androidannotations.annotations.EBean;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-10
 */

@EBean
class DashboardPresenter extends MvpPresenter<DashboardContract.View> implements DashboardContract.Presenter {
    private static final String TAG = "DashboardPresenter";

    @Override
    public void refreshAllViews() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void setView() {
        setPresenterView(view);
    }
}
