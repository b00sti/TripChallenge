package com.example.b00sti.tripchallenge.test;

import com.example.b00sti.tripchallenge.ui_login.LogInContract;

import org.androidannotations.annotations.EBean;

/**
 * Created by b00sti on 22.11.2016.
 */

@EBean
public class Present extends MvpPres<LogInContract.View> implements LogInContract.Presenter {

    @Override
    public void setView(LogInContract.View view) {
        setViewToPresenter(view);
    }

    @Override
    public void afterLogIn(String email, String password) {

    }

    @Override
    public void afterForgotPassword() {

    }

    @Override
    public void afterNoAccount() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}


