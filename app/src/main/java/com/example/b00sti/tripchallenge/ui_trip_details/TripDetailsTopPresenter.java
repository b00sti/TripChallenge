package com.example.b00sti.tripchallenge.ui_trip_details;


import android.app.Activity;

import com.example.b00sti.tripchallenge.utils.helpers.GooglePlacesManager;
import com.example.skeleton.ui.mvp_base.MvpPresenter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-10
 */

@EBean
class TripDetailsTopPresenter extends MvpPresenter<TripDetailsTopContract.View> implements TripDetailsTopContract.Presenter {
    private static final String TAG = "TripDetailsTopPresenter";

    @Bean
    GooglePlacesManager googlePlacesManager;

    @RootContext
    Activity activity;

    @Override
    public void refreshAllViews() {

    }

    @Override
    public void connect() {

    }


    private void getCityNames() {


    }

    @Override
    public void subscribe() {
        googlePlacesManager.connect();
    }

    @Override
    public void unsubscribe() {
        googlePlacesManager.disconnect();
    }

}
