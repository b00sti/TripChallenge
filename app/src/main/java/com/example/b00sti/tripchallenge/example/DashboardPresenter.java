package com.example.b00sti.tripchallenge.example;


import android.content.Context;

import com.example.skeleton.ui.mvp_base.MvpPresenter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-10
 */

@EBean
class DashboardPresenter extends MvpPresenter<DashboardContract.View> implements DashboardContract.Presenter {
    private static final String TAG = "DashboardPresenter";

    @RootContext
    Context context;

    @Override
    public void refreshAllViews() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

}
