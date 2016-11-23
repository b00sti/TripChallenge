package com.example.b00sti.tripchallenge.test;

import android.content.Context;

import com.example.b00sti.tripchallenge.ui_login.LogInContract;
import com.example.skeleton.android_utils.util.ViewUtils;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by b00sti on 22.11.2016.
 */

@EBean
public class Present extends MvpPres<LogInContract.View> implements LogInContract.Presenter {

    @RootContext
    Context context;

    @Override
    public void afterLogIn(String email, String password) {
        view.showProgressBar();
        ViewUtils.showToast(context, "after login email: " + email + " pass: " + password);
        view.hideProgressBar();
    }

    @Override
    public void afterForgotPassword() {
        ViewUtils.showToast(context, "after forgot password");
    }

    @Override
    public void afterNoAccount() {
        ViewUtils.showToast(context, "after no account");
    }

    @Override
    public void subscribe() {
        ViewUtils.showToast(context, "Subscribed");
    }

    @Override
    public void unsubscribe() {
        ViewUtils.showToast(context, "Unsubscribed");
    }
}


