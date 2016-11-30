package com.example.b00sti.tripchallenge.ui_signin;

import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.b00sti.tripchallenge.R;
import com.example.skeleton.android_utils.util.AnimUtils;
import com.example.skeleton.android_utils.util.ViewUtils;
import com.example.skeleton.ui.mvp_base.MvpFragment;
import com.example.skeleton.ui.progressbar.ProgressBarUtils;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by b00sti on 20.11.2016.
 */

@EFragment(R.layout.fragment_signin)
public class SignInFragment extends MvpFragment<SignInContract.Presenter> implements SignInContract.View {
    private static final String TAG = "SignInFragment";

    @ViewById(R.id.progressBar) ProgressBar progressBar;
    @ViewById(R.id.userIconImageView) ImageView userIconImageView;
    @ViewById(R.id.nickEditText) EditText nickEditText;
    @ViewById(R.id.emailEditText) EditText emailEditText;
    @ViewById(R.id.passwordEditText) EditText passwordEditText;
    @ViewById(R.id.nickEditTextL) TextInputLayout nickEditTextL;
    @ViewById(R.id.emailEditTextL) TextInputLayout emailEditTextL;
    @ViewById(R.id.passwordEditTextL) TextInputLayout passwordEditTextL;
    @ViewById(R.id.createAccountButton) AppCompatButton createAccountButton;
    @ViewById(R.id.loginTextView) TextView loginTextView;

    @Bean
    SignInPresenter presenter;

    public static Fragment newInstance() {
        return new SignInFragment_();
    }

    @Override
    public void onResume() {
        super.onResume();
        setEnterAnimation();
    }

    private void setEnterAnimation() {
        float init = 0.75f;
        int duration = 1000;
        userIconImageView.startAnimation(AnimUtils.enterScaleAnim(init, init, duration));
        emailEditTextL.startAnimation(AnimUtils.enterScaleAnim(init, init, duration));
        passwordEditTextL.startAnimation(AnimUtils.enterScaleAnim(init, init, duration));
        nickEditTextL.startAnimation(AnimUtils.enterScaleAnim(init, init, duration));
        createAccountButton.startAnimation(AnimUtils.enterScaleAnim(init, init, duration));
        loginTextView.startAnimation(AnimUtils.enterScaleAnim(init, init, duration));
    }

    @AfterInject
    public void injectContext() {
        ctx = getActivity();
    }

    @Click(R.id.createAccountButton)
    public void clickCreateAccount() {
        presenter.afterSignIn(emailEditText.getText().toString(), passwordEditText.getText().toString());
    }

    @Click(R.id.loginTextView)
    public void clickLogin() {
        presenter.afterLogIn();
    }

    @Override
    public void onDestroyView() {
        ViewUtils.hideKeyboard(ctx);
        super.onDestroyView();
    }

    @Override
    public void showProgressBar() {
        ProgressBarUtils.show(progressBar);
    }

    @Override
    public void hideProgressBar() {
        ProgressBarUtils.hide(progressBar);
    }

    @Override
    public void showNoConnection() {
        ViewUtils.showNoConnectionToast(ctx);
    }

    @Override
    protected SignInContract.Presenter setPresenterView() {
        presenter.setView(this);
        return presenter;
    }
}
