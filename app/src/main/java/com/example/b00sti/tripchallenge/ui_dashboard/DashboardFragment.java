package com.example.b00sti.tripchallenge.ui_dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.b00sti.tripchallenge.R;
import com.example.b00sti.tripchallenge.utils.ui.mvp_base.MvpFragment;

import org.androidannotations.annotations.EFragment;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-10
 */

@EFragment(R.layout.fragment_dashboard)
public class DashboardFragment extends MvpFragment<DashboardContract.Presenter> implements DashboardContract.View {

    private static final String TAG = "DashboardFragment";

    public static DashboardFragment newInstance() {
        return new DashboardFragment_();
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
