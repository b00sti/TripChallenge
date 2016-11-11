package com.example.b00sti.tripchallenge.mvp_base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import static com.google.android.gms.internal.zzs.TAG;


/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-03
 */
public abstract class MvpFragment<P extends BasePresenter> extends Fragment {

    public P presenter;

    @SuppressWarnings("unchecked")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
        if (presenter == null) {
            presenter = createPresenter();
        }
        presenter.subscribe(this);
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: ");
        presenter.unsubscribe();
        super.onDestroyView();
    }

    protected abstract P createPresenter();
}
