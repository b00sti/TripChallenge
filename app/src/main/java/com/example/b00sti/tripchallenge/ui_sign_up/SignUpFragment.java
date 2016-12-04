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
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by b00sti on 20.11.2016.
 */

@EFragment(R.layout.fragment_sign_up)
public class SignUpFragment extends MvpFragment<SignUpContract.Presenter> implements SignUpContract.View {
    private static final String TAG = "SignUpFragment";

    @ViewById(R.id.progressBar) ProgressBar progressBar;
    @ViewById(R.id.userIconIV) ImageView userIconIV;
    @ViewById(R.id.nickET) EditText nickET;
    @ViewById(R.id.emailET) EditText emailET;
    @ViewById(R.id.passwordET) EditText passwordET;
    @ViewById(R.id.nickETL) TextInputLayout nickETL;
    @ViewById(R.id.emailETL) TextInputLayout emailETL;
    @ViewById(R.id.passwordETL) TextInputLayout passwordETL;
    @ViewById(R.id.createAccountB) AppCompatButton createAccountB;
    @ViewById(R.id.bottomTV) TextView bottomTV;
    @ViewById(R.id.divider1V) View divider1V;
    @ViewById(R.id.divider2V) TextView divider2V;
    @ViewById(R.id.divider3V) View divider3V;
    @ViewById(R.id.topTV) TextView topTV;
    @ViewById(R.id.googleButtonL) LinearLayout signUpGoogle;
    @ViewById(R.id.facebookLoginB) LoginButton signUpFacebook;

    @Bean
    SignUpPresenter presenter;

    public static Fragment newInstance() {
        return new SignUpFragment_();
    }

    @AfterViews
    void initViews() {
        topTV.setText(ctx.getResources().getString(R.string.sign_up_with));
        bottomTV.setText(ctx.getResources().getString(R.string.already_a_member_login));
    }

    @Click(R.id.createAccountB)
    public void clickCreateAccount() {
        presenter.afterSignIn(emailET.getText().toString(), passwordET.getText().toString());
    }

    @Click(R.id.bottomTV)
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
        nickETL.startAnimation(getAnim());
        createAccountB.startAnimation(getAnim());
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
                nickET.setFocusable(false);
                emailET.setFocusable(false);
                passwordET.setFocusable(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                nickET.setEnabled(true);
                emailET.setEnabled(true);
                passwordET.setEnabled(true);
                nickET.setFocusable(true);
                emailET.setFocusable(true);
                passwordET.setFocusable(true);
                nickET.setFocusableInTouchMode(true);
                emailET.setFocusableInTouchMode(true);
                passwordET.setFocusableInTouchMode(true);
                nickET.requestFocus();
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
