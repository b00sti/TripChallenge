package com.example.b00sti.tripchallenge.ui_dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.b00sti.tripchallenge.R;
import com.example.b00sti.tripchallenge.mvp_base.MvpFragment;

import org.androidannotations.annotations.EFragment;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-10
 */

@EFragment(R.layout.fragment_dashboard)
public class DashboardFragment extends MvpFragment<DashboardContract.Presenter> implements DashboardContract.View {
    private static final String TAG = "DashboardFragment";

    public static DashboardFragment newInstance() {
        Log.d(TAG, "newInstance: ");
        return new DashboardFragment_();
    }

    @Override
    protected DashboardContract.Presenter createPresenter() {
        Log.d(TAG, "createPresenter: ");
        return new DashboardPresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
