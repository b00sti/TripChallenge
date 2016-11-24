package com.example.b00sti.tripchallenge.ui_login;

import com.example.b00sti.tripchallenge.main.FragmentBuilder;
import com.example.b00sti.tripchallenge.utils.ActivityUtils;
import com.example.skeleton.android_utils.eventbus.EventBusManager;
import com.example.skeleton.ui.mvp_base.MvpPresenter;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by b00sti on 20.11.2016.
 */

//@EBean
public class LogInPresenter extends MvpPresenter<LogInContract.View> implements LogInContract.Presenter {

    /*    @Bean
        FragmentBuilder fragmentBuilder;
        @Bean
        FirebaseManager firebaseManager;*/
    private FirebaseAuth firebaseAuth;

    /*
        @AfterInject
        void init() {
            firebaseAuth = firebaseManager.getFirebaseAuth();
        }

     */
    @Override
    public void afterForgotPassword() {

    }

    @Override
    public void afterNoAccount() {
        ActivityUtils.startInnerViewActivity(view.getCtx(), FragmentBuilder.SIGN_IN);
    }

    @Override
    public void afterLogIn(String email, String password) {
/*

        email = email.trim();
        password = password.trim();

        if (email.isEmpty() || password.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getCtx());
            builder.setMessage(R.string.login_error_message)
                    .setTitle(R.string.login_error_title)
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(view.getCtx(), task -> {
                        if (task.isSuccessful()) {
                            Fragment targetFragment = fragmentBuilder.newFragment(FragmentBuilder.DASHBOARD);
                            EventBus.getDefault().post(new SwitchDrawerFragmentEvent(targetFragment, DrawerUtils.TAB_00));
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(view.getCtx());
                            builder.setMessage(task.getException().getMessage())
                                    .setTitle(R.string.login_error_title)
                                    .setPositiveButton(android.R.string.ok, null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });
        }
*/

    }

    @Override
    public void subscribe() {
        EventBusManager.register(view.getCtx());
    }

    @Override
    public void unsubscribe() {
        EventBusManager.register(view.getCtx());
    }

}
