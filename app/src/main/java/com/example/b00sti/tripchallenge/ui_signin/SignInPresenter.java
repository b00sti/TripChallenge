package com.example.b00sti.tripchallenge.ui_signin;

import com.example.skeleton.ui.mvp_base.MvpPresenter;

/**
 * Created by b00sti on 20.11.2016.
 */

public class SignInPresenter extends MvpPresenter<SignInContract.View> implements SignInContract.Presenter {

    public SignInPresenter(SignInContract.View view) {
        super(view);
    }

    @Override
    public void afterSignIn() {

    }

    @Override
    public void afterLogIn() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
