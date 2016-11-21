package com.example.b00sti.tripchallenge.ui_login;

import com.example.skeleton.ui.mvp_base.BasePresenter;

/**
 * Created by b00sti on 20.11.2016.
 */

public class LogInContract {

    interface View {

        void showProgressBar();

        void hideProgressBar();

        void showNoConnection();

    }

    interface Presenter extends BasePresenter {

        void afterLogIn();

        void afterForgotPassword();

        void afterNoAccount();

    }

}
