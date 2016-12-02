package com.example.b00sti.tripchallenge.ui_sign_up;

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
 * Created by b00sti on 20.11.2016.
 */

@EFragment(R.layout.fragment_signin)
public class SignUpFragment extends MvpFragment<SignUpContract.Presenter> implements SignUpContract.View {
    private static final String TAG = "SignUpFragment";

    @ViewById(R.id.progressBar) ProgressBar progressBar;
    @ViewById(R.id.userIconImageView) ImageView userIconImageView;
    @ViewById(R.id.nickEditText) EditText nickEditText;
    @ViewById(R.id.emailEditText) EditText emailEditText;
    @ViewById(R.id.passwordEditText) EditText passwordEditText;
    @ViewById(R.id.nickEditTextL) TextInputLayout nickEditTextL;
    @ViewById(R.id.emailEditTextL) TextInputLayout emailEditTextL;
    @ViewById(R.id.passwordEditTextL) TextInputLayout passwordEditTextL;
    @ViewById(R.id.createAccountButton) AppCompatButton createAccountButton;
    @ViewById(R.id.bottomTextView) TextView loginTextView;
    @ViewById(R.id.divider1) View divider1;
    @ViewById(R.id.divider2) TextView divider2;
    @ViewById(R.id.divider3) View divider3;
    @ViewById(R.id.topTextView) TextView signUpWithTextView;
    @ViewById(R.id.googleButtonL) LinearLayout signUpGoogle;
    @ViewById(R.id.facebookLoginButton) LoginButton signUpFacebook;

    @Bean
    SignUpPresenter presenter;

    public static Fragment newInstance() {
        return new SignUpFragment_();
    }

    @Click(R.id.createAccountButton)
    public void clickCreateAccount() {
        presenter.afterSignIn(emailEditText.getText().toString(), passwordEditText.getText().toString());
    }

    @Click(R.id.bottomTextView)
    public void clickLogin() {
        presenter.afterLogIn();
    }

    @AfterInject
    public void injectContext() {
        ctx = getActivity();
    }

    @Override
    protected SignUpContract.Presenter setPresenterView() {
        presenter.setView(this);
        return presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        setEnterAnimation();

        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
        userIconImageView.startAnimation(getHandlingEdittextsFocus());
        emailEditTextL.startAnimation(getAnim());
        passwordEditTextL.startAnimation(getAnim());
        nickEditTextL.startAnimation(getAnim());
        createAccountButton.startAnimation(getAnim());
        loginTextView.startAnimation(getAnim());
        divider1.startAnimation(getAnim());
        divider2.startAnimation(getAnim());
        divider3.startAnimation(getAnim());
        signUpWithTextView.startAnimation(getAnim());
        signUpGoogle.startAnimation(getAnim());
        signUpFacebook.startAnimation(getAnim());
    }

    private ScaleAnimation getHandlingEdittextsFocus() {
        ScaleAnimation animation = getAnim();
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                nickEditText.setFocusable(false);
                emailEditText.setFocusable(false);
                passwordEditText.setFocusable(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                nickEditText.setEnabled(true);
                emailEditText.setEnabled(true);
                passwordEditText.setEnabled(true);
                nickEditText.setFocusable(true);
                emailEditText.setFocusable(true);
                passwordEditText.setFocusable(true);
                nickEditText.setFocusableInTouchMode(true);
                emailEditText.setFocusableInTouchMode(true);
                passwordEditText.setFocusableInTouchMode(true);
                nickEditText.requestFocus();
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