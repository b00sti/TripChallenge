package com.example.b00sti.tripchallenge.ui_login;

import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.b00sti.tripchallenge.R;
import com.example.skeleton.android_utils.util.ViewUtils;
import com.example.skeleton.ui.mvp_base.MvpFragment;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by b00sti on 20.11.2016
 */

@EFragment(R.layout.fragment_login)
public class LogInFragment extends MvpFragment<LogInContract.Presenter> implements LogInContract.View {
    private static final String TAG = "LogInFragment";

    @ViewById(R.id.progressBar) ProgressBar progressBar;
    @ViewById(R.id.userIconImageView) ImageView userIconImageView;
    @ViewById(R.id.emailEditText) EditText emailEditText;
    @ViewById(R.id.passwordEditText) EditText passwordEditText;
    @ViewById(R.id.loginButton) AppCompatButton loginButton;
    @ViewById(R.id.forgotPasswordTextView) TextView forgotPasswordTextView;
    @ViewById(R.id.createAccountTextView) TextView createAccountTextView;

    @Bean
    LogInPresenter presenter;

    public static Fragment newInstance() {
        return new LogInFragment_();
    }

    @AfterInject
    public void injectContext() {
        ctx = getActivity();
    }

    @Override
    public void onDestroyView() {
        ViewUtils.hideKeyboard(ctx);
        super.onDestroyView();
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
    protected LogInContract.Presenter setPresenterView() {
        presenter.setView(this);
        return new LogInPresenter();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showNoConnection() {
        ViewUtils.showNoConnectionToast(ctx);
    }

}
