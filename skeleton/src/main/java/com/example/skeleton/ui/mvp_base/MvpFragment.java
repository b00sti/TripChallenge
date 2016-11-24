package com.example.skeleton.ui.mvp_base;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import org.androidannotations.annotations.EFragment;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-03
 */
@EFragment
public abstract class MvpFragment<P extends BasePresenter> extends Fragment {
    private static final String TAG = "MvpFragment";

    public P presenter;
    public Activity ctx;

    @SuppressWarnings("unchecked")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (presenter == null) {
            presenter = setPresenterView();
            Log.d(TAG, "onViewCreated: " + presenter + " created");
        }
        presenter.subscribe();
        Log.d(TAG, "onViewCreated: " + presenter + " subscribed");
    }

    @Override
    public void onDestroyView() {
        presenter.unsubscribe();
        Log.d(TAG, "onDestroyView: unsubscribed");
        super.onDestroyView();
    }

    protected abstract P setPresenterView();
}
