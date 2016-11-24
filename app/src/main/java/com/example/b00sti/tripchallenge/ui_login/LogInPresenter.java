package com.example.b00sti.tripchallenge.ui_login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;

import com.example.b00sti.tripchallenge.R;
import com.example.b00sti.tripchallenge.main.FragmentBuilder;
import com.example.b00sti.tripchallenge.utils.ActivityUtils;
import com.example.skeleton.android_utils.eventbus.SwitchDrawerFragmentEvent;
import com.example.skeleton.android_utils.firebase.FirebaseManager;
import com.example.skeleton.android_utils.navigation.drawer.DrawerUtils;
import com.example.skeleton.android_utils.util.ViewUtils;
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
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        //builder.setTitle("Reset Password");
        View view = View.inflate(ctx, R.layout.dialog_forgot_password, null);
        View view2 = View.inflate(ctx, R.layout.dialog_title, null);
        EditText input = (EditText) view.findViewById(R.id.dialog_editText);
        builder.setView(view);
        builder.setCustomTitle(view2);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String email = input.getText().toString();
                if (email.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                    builder.setMessage(R.string.login_error_message)
                            .setTitle(R.string.login_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog2 = builder.create();
                    dialog2.show();
                } else {
                    firebaseAuth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(ctx, task -> {
                                if (task.isSuccessful()) {
                                    ViewUtils.hideKeyboard(ctx);
                                    ViewUtils.showToast(ctx, "Check your email !");
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                                    builder.setMessage("Check your email for the link to reset your password")
                                            .setTitle("Password Request Send")
                                            .setPositiveButton(android.R.string.ok, null);
                                    AlertDialog dialog2 = builder.create();
                                    dialog2.show();
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                                    Exception exception = task.getException();
                                    String message = exception != null ? exception.getMessage() : "";
                                    builder.setMessage(message)
                                            .setTitle(R.string.login_error_title)
                                            .setPositiveButton(android.R.string.ok, null);
                                    AlertDialog dialog3 = builder.create();
                                    dialog3.show();
                                }
                            });
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);
        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(lightTextColor);
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(lightTextColor);

    }

    @Override
    public void afterNoAccount() {
        ActivityUtils.startInnerViewActivity(ctx, FragmentBuilder.SIGN_IN);
    }

    @Override
    public void afterLogIn(String email, String password) {

        email = email.trim();
        password = password.trim();

        if (email.isEmpty() || password.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setMessage(R.string.login_error_message)
                    .setTitle(R.string.login_error_title)
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(ctx, task -> {
                        if (task.isSuccessful()) {
                            Fragment targetFragment = fragmentBuilder.newFragment(FragmentBuilder.DASHBOARD);
                            EventBus.getDefault().post(new SwitchDrawerFragmentEvent(targetFragment, DrawerUtils.TAB_00));
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                            Exception exception = task.getException();
                            String message = exception != null ? exception.getMessage() : "";
                            builder.setMessage(message)
                                    .setTitle(R.string.login_error_title)
                                    .setPositiveButton(android.R.string.ok, null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });
        }

    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
    }

}
