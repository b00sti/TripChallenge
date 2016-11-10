package com.example.b00sti.tripchallenge.ui_dashboard;

import android.util.Log;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-10
 */

public class DashboardPresenter implements DashboardContract.Presenter {

    @Override
    public void subscribe(Object view) {
        Log.d(TAG, "subscribe: ");
    }

    @Override
    public void unsubscribe() {
        Log.d(TAG, "unsubscribe: ");
    }
}
