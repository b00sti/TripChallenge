package com.example.b00sti.tripchallenge.example;

import android.support.v4.app.Fragment;

import com.example.b00sti.tripchallenge.R;
import com.example.skeleton.ui.mvp_base.MvpFragment;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-10
 */

@EFragment(R.layout.fragment_dashboard)
public class DashboardFragment extends MvpFragment<DashboardContract.Presenter> implements DashboardContract.View {
    private static final String TAG = "DashboardFragment";

    @Bean
    DashboardPresenter presenter;

    public static Fragment newInstance() {
        return new DashboardFragment_();
    }

    @Override
    protected DashboardContract.Presenter setPresenterView() {
        presenter.setView(this);
        return presenter;
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
