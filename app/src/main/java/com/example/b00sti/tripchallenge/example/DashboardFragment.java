package com.example.b00sti.tripchallenge.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.b00sti.tripchallenge.R;
import com.example.b00sti.tripchallenge.ui_dashboard.DashboardFragment_;
import com.example.skeleton.ui.mvp_base.MvpFragment;

import org.androidannotations.annotations.EFragment;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-10
 */

@EFragment(R.layout.fragment_dashboard)
public class DashboardFragment extends MvpFragment<DashboardContract.Presenter> implements DashboardContract.View {

    private static final String TAG = "DashboardFragment";

    public static Fragment newInstance() {
        DashboardFragment dashboardFragment = new DashboardFragment_();
        return dashboardFragment;
    }

    @Override
    protected DashboardContract.Presenter createPresenter() {
        return new DashboardPresenter(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
