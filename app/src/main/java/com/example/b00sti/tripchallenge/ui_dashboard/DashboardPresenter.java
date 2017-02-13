package com.example.b00sti.tripchallenge.ui_dashboard;


import android.app.Activity;
import android.util.Log;

import com.example.b00sti.tripchallenge.utils.helpers.GooglePlacesManager;
import com.example.skeleton.android_utils.util.ViewUtils;
import com.example.skeleton.ui.mvp_base.MvpPresenter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-10
 */

@EBean
class DashboardPresenter extends MvpPresenter<DashboardContract.View> implements DashboardContract.Presenter {
    private static final String TAG = "DashboardPresenter";

    @Bean
    GooglePlacesManager googlePlacesManager;

    @RootContext
    Activity activity;

    @Override
    public void refreshAllViews() {

    }

    @Override
    public void connect() {
        getCityNames();
    }


    private void getCityNames() {
        googlePlacesManager.getCurrentPlaces(activity).subscribe(tripCity -> {
            Log.d(TAG, "getCityNames: " + tripCity.getCityTitle());
            ViewUtils.showToast(activity, tripCity.getCityTitle());
        });

    }

    @Override
    public void subscribe() {
        googlePlacesManager.connect();
    }

    @Override
    public void unSubscribe() {
        googlePlacesManager.disconnect();
    }

}
