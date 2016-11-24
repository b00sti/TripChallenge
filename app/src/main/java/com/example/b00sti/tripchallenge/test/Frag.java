package com.example.b00sti.tripchallenge.test;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.b00sti.tripchallenge.R;
import com.example.b00sti.tripchallenge.ui_login.LogInContract;
import com.example.skeleton.android_utils.util.ViewUtils;
import com.example.skeleton.ui.mvp_base.MvpFragment;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by b00sti on 22.11.2016
 */

@EFragment(R.layout.fragment_login)
public class Frag extends MvpFragment<LogInContract.Presenter> implements LogInContract.View {

    @ViewById(R.id.progressBar) ProgressBar progressBar;
    @ViewById(R.id.userIconImageView) ImageView userIconImageView;
    @ViewById(R.id.emailEditText) EditText emailEditText;
    @ViewById(R.id.passwordEditText) EditText passwordEditText;
    @ViewById(R.id.loginButton) AppCompatButton loginButton;
    @ViewById(R.id.forgotPasswordTextView) TextView forgotPasswordTextView;
    @ViewById(R.id.createAccountTextView) TextView createAccountTextView;

    @Bean
    Present present;

    public static Fragment newInstance() {
        return new Frag_();
    }

    @Click(R.id.loginButton)
    void clickedLogIn() {
        presenter.afterLogIn(emailEditText.getText().toString(), passwordEditText.getText().toString());
    }

    @Click(R.id.forgotPasswordTextView)
    void clickedForgotPassword() {
        presenter.afterForgotPassword();
    }

    @Click(R.id.createAccountTextView)
    void clickedNoAccount() {
        presenter.afterNoAccount();
    }

    @Override
    public void showProgressBar() {
        ViewUtils.showToast(getActivity(), "Show progress");
    }

    @Override
    public void hideProgressBar() {
        ViewUtils.showToast(getActivity(), "Hide progress");
    }

    @Override
    public void showNoConnection() {

    }

    @Override
    public Activity getCtx() {
        return null;
    }

    @Override
    protected LogInContract.Presenter setPresenterView() {
        present.setView(this);
        return present;
    }
}
