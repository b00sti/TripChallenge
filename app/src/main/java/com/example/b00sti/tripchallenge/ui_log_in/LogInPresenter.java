package com.example.b00sti.tripchallenge.ui_log_in;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.TextView;

import com.example.b00sti.tripchallenge.R;
import com.example.b00sti.tripchallenge.main.FragmentBuilder;
import com.example.b00sti.tripchallenge.ui_sign_up.SignUpActivity_;
import com.example.b00sti.tripchallenge.utils.ActivityUtils;
import com.example.b00sti.tripchallenge.utils.helpers.FastDialog;
import com.example.skeleton.android_utils.eventbus.SwitchDrawerFragmentEvent;
import com.example.skeleton.android_utils.firebase.FirebaseManager;
import com.example.skeleton.android_utils.navigation.drawer.DrawerUtils;
import com.example.skeleton.ui.mvp_base.MvpPresenter;
import com.google.firebase.auth.FirebaseAuth;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.res.ColorRes;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by b00sti on 20.11.2016
 */

@EBean
public class LogInPresenter extends MvpPresenter<LogInContract.View> implements LogInContract.Presenter {

    @Bean
    FragmentBuilder fragmentBuilder;

    @Bean
    FirebaseManager firebaseManager;

    @RootContext
    Activity ctx;

    @ColorRes(R.color.colorLightText)
    int lightTextColor;

    private FirebaseAuth firebaseAuth;

    @AfterInject
    void init() {
        firebaseAuth = firebaseManager.getFirebaseAuth();
    }

    @Override
    public void afterForgotPassword() {
        showForgotPasswordDialog();
    }

    private void showForgotPasswordDialog() {
        FastDialog forgotPasswordDialog = new FastDialog(ctx);
        forgotPasswordDialog.setTitle(R.string.reset_password);
        forgotPasswordDialog.setMessage(R.string.enter_email);
        forgotPasswordDialog.setMessageViewId(R.layout.dialog_forgot_password);
        EditText input = (EditText) forgotPasswordDialog.getMessageView().findViewById(R.id.dialog_editText);
        TextView message = (TextView) forgotPasswordDialog.getMessageView().findViewById(R.id.dialog_textView);
        message.setText(forgotPasswordDialog.getMessage());
        forgotPasswordDialog.setPositiveButtonListener((dialogInterface, i) -> {
            String email = input.getText().toString();
            if (email.isEmpty()) {
                FastDialog.create(ctx).show(R.string.login_error_title, R.string.login_error_message, FastDialog.emptyListener(), null);
            } else {
                view.showProgressBar();
                firebaseAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(ctx, task -> {
                            view.hideProgressBar();
                            if (task.isSuccessful()) {
                                FastDialog.create(ctx).show(ctx.getString(R.string.request_password_send), ctx.getString(R.string.check_email), FastDialog.emptyListener(), null);
                            } else {
                                Exception exception = task.getException();
                                String exceptionMessage = exception != null ? exception.getMessage() : "";
                                FastDialog.create(ctx).show(R.string.login_error_title, exceptionMessage, FastDialog.emptyListener(), null);
                            }
                        });
            }
        });
        forgotPasswordDialog.setNegativeButtonListener((dialogInterface, i) -> dialogInterface.cancel());
        forgotPasswordDialog.show();
    }

    @Override
    public void afterNoAccount() {
        ActivityUtils.startInnerViewActivity(ctx, FragmentBuilder.SIGN_IN, ctx.getString(R.string.fragment_title_sign_up), SignUpActivity_.class);
    }

    @Override
    public void afterLogIn(String email, String password) {
        email = email.trim();
        password = password.trim();

        if (email.isEmpty() || password.isEmpty()) {
            FastDialog.create(ctx).show(R.string.login_error_title, R.string.login_error_message, FastDialog.emptyListener(), null);
        } else {
            view.showProgressBar();
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(ctx, task -> {
                        view.hideProgressBar();
                        if (task.isSuccessful()) {
                            Fragment targetFragment = fragmentBuilder.newFragment(FragmentBuilder.DASHBOARD);
                            EventBus.getDefault().post(new SwitchDrawerFragmentEvent(targetFragment, DrawerUtils.TAB_00));
                        } else {
                            Exception exception = task.getException();
                            String exceptionMessage = exception != null ? exception.getMessage() : "";
                            FastDialog.create(ctx).show(R.string.login_error_title, exceptionMessage, FastDialog.emptyListener(), null);
                        }
                    });
        }

    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unSubscribe() {
    }

}
