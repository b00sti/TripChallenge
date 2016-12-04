package com.example.b00sti.tripchallenge.ui_log_in;

import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.b00sti.tripchallenge.R;
import com.example.skeleton.android_utils.util.AnimUtils;
import com.example.skeleton.android_utils.util.ViewUtils;
import com.example.skeleton.ui.mvp_base.MvpFragment;
import com.example.skeleton.ui.progressbar.ProgressBarUtils;
import com.facebook.login.widget.LoginButton;

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
    @ViewById(R.id.userIconIV) ImageView userIconIV;
    @ViewById(R.id.emailET) EditText emailET;
    @ViewById(R.id.passwordET) EditText passwordET;
    @ViewById(R.id.emailETL) TextInputLayout emailETL;
    @ViewById(R.id.passwordETL) TextInputLayout passwordETL;
    @ViewById(R.id.loginB) AppCompatButton loginB;
    @ViewById(R.id.bottomTV) TextView bottomTV;
    @ViewById(R.id.divider1V) View divider1V;
    @ViewById(R.id.divider2V) TextView divider2V;
    @ViewById(R.id.divider3V) View divider3V;
    @ViewById(R.id.topTV) TextView topTV;
    @ViewById(R.id.googleButtonL) LinearLayout signUpGoogle;
    @ViewById(R.id.facebookLoginB) LoginButton signUpFacebook;
    @ViewById(R.id.forgotPasswordTV) TextView forgotPasswordTV;

    @Bean
    LogInPresenter presenter;

    public static Fragment newInstance() {
        return new LogInFragment_();
    }

    @Click(R.id.loginButton)
    void clickedLogIn() {
        presenter.afterLogIn(emailET.getText().toString(), passwordET.getText().toString());
    }

    @Click(R.id.forgotPasswordTV)
    void clickedForgotPassword() {
        presenter.afterForgotPassword();
    }

    @Click(R.id.bottomTV)
    void clickedNoAccount() {
        presenter.afterNoAccount();
    }

    @Override
    protected LogInContract.Presenter setPresenterView() {
        presenter.setView(this);
        return new LogInPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        setEnterAnimation();
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

    private void setEnterAnimation() {
        userIconIV.startAnimation(getHandlingEdittextsFocus());
        emailETL.startAnimation(getAnim());
        passwordETL.startAnimation(getAnim());
        forgotPasswordTV.startAnimation(getAnim());
        loginB.startAnimation(getAnim());
        bottomTV.startAnimation(getAnim());
        divider1V.startAnimation(getAnim());
        divider2V.startAnimation(getAnim());
        divider3V.startAnimation(getAnim());
        topTV.startAnimation(getAnim());
        signUpGoogle.startAnimation(getAnim());
        signUpFacebook.startAnimation(getAnim());
    }

    private ScaleAnimation getHandlingEdittextsFocus() {
        ScaleAnimation animation = getAnim();
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                emailET.setFocusable(false);
                passwordET.setFocusable(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                emailET.setEnabled(true);
                passwordET.setEnabled(true);
                emailET.setFocusable(true);
                passwordET.setFocusable(true);
                emailET.setFocusableInTouchMode(true);
                passwordET.setFocusableInTouchMode(true);
                emailET.requestFocus();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        return animation;
    }

    private ScaleAnimation getAnim() {
        float init = 0.75f;
        int duration = 1000;
        return AnimUtils.enterScaleAnim(init, init, duration);
    }

}
