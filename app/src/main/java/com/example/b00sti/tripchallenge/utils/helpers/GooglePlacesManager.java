package com.example.b00sti.tripchallenge.utils.helpers;

import android.app.Activity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by b00sti on 05.12.2016.
 */
@EBean(scope = EBean.Scope.Singleton)
public class GooglePlacesManager {

    @RootContext
    Activity ctx;
    private GoogleApiClient mGoogleApiClient;

    GooglePlacesManager() {
        init();
    }

    private void init() {
        mGoogleApiClient = new GoogleApiClient
                .Builder(ctx)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                //.enableAutoManage(ctx, this)
                .build();
    }

    public void connect() {
        if (!mGoogleApiClient.isConnected() && !mGoogleApiClient.isConnecting()) {
            mGoogleApiClient.connect();
        }
    }

    public void disconnect() {
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

}
