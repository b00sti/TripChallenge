package com.example.b00sti.tripchallenge.test;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.example.skeleton.ui.mvp_base.BasePresenter;

import org.androidannotations.annotations.EFragment;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-03
 */
@EFragment
public abstract class MvpFrag<P extends BasePresenter> extends Fragment {
    private static final String TAG = "MvpFrag";

    public P presenterInterface;

    @SuppressWarnings("unchecked")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (presenterInterface == null) {
            presenterInterface = createPresenter();
            Log.d(TAG, "onViewCreated: " + presenterInterface + " created");
        }
        presenterInterface.subscribe();
        Log.d(TAG, "onViewCreated: " + presenterInterface + " subscribed");
    }

    @Override
    public void onDestroyView() {
        presenterInterface.unsubscribe();
        Log.d(TAG, "onDestroyView: unsubscribed");
        super.onDestroyView();
    }

    protected abstract P createPresenter();
}
