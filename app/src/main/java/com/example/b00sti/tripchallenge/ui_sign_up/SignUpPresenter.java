package com.example.b00sti.tripchallenge.ui_sign_up;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.example.b00sti.tripchallenge.R;
import com.example.b00sti.tripchallenge.main.FragmentBuilder;
import com.example.b00sti.tripchallenge.ui_log_in.LogInActivity_;
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
import org.greenrobot.eventbus.EventBus;

/**
 * Created by b00sti on 20.11.2016
 */

@EBean
public class SignUpPresenter extends MvpPresenter<SignUpContract.View> implements SignUpContract.Presenter {

    @Bean
    FragmentBuilder fragmentBuilder;

    @Bean
    FirebaseManager firebaseManager;

    @RootContext
    Activity ctx;

    private FirebaseAuth firebaseAuth;

    @AfterInject
    void init() {
        firebaseAuth = firebaseManager.getFirebaseAuth();
    }

    @Override
    public void afterSignIn(String email, String password) {
        password = password.trim();
        email = email.trim();

        if (password.isEmpty() || email.isEmpty()) {
            FastDialog.create(ctx).show(R.string.signup_error_message, R.string.signup_error_title, FastDialog.emptyListener(), null);
        } else {
            view.showProgressBar();
            firebaseAuth.createUserWithEmailAndPassword(email, password)
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
    public void afterLogIn() {
        ActivityUtils.startInnerViewActivity(ctx, FragmentBuilder.LOG_IN, ctx.getString(R.string.fragment_title_login), LogInActivity_.class);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

}
