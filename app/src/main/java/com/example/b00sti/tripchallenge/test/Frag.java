package com.example.b00sti.tripchallenge.test;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.example.b00sti.tripchallenge.R;
import com.example.b00sti.tripchallenge.ui_login.LogInContract;
import com.example.skeleton.ui.mvp_base.MvpFragment;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * Created by b00sti on 22.11.2016.
 */

@EFragment(R.layout.fragment_login)
public class Frag extends MvpFragment<LogInContract.Presenter> implements LogInContract.View {

    @Bean
    Present present;

    public static Fragment newInstance() {
        return new Frag_();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showNoConnection() {

    }

    @Override
    public Activity getCtx() {
        return null;
    }

    @Override
    protected LogInContract.Presenter createPresenter() {
        present.setView(this);
        return present;
    }
}
