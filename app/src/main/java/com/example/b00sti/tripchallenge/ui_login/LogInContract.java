package com.example.b00sti.tripchallenge.ui_login;

import com.example.skeleton.ui.mvp_base.BasePresenter;

/**
 * Created by b00sti on 20.11.2016.
 */

public class LogInContract {

    public interface View {

        void showProgressBar();

        void hideProgressBar();

        void showNoConnection();

    }

    public interface Presenter extends BasePresenter {

        void afterLogIn(String email, String password);

        void afterForgotPassword();

        void afterNoAccount();

    }

}
