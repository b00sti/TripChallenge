package com.example.b00sti.tripchallenge.ui_signin;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.b00sti.tripchallenge.R;
import com.example.skeleton.android_utils.util.ViewUtils;
import com.example.skeleton.ui.mvp_base.MvpFragment;

import org.androidannotations.annotations.AfterInject;
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
    @ViewById(R.id.createAccountButton) AppCompatButton createAccountButton;
    @ViewById(R.id.loginTextView) TextView loginTextView;

    Activity context;

    public static Fragment newInstance() {
        return new SignInFragment_();
    }

    @AfterInject
    public void injectContext() {
        context = getActivity();
    }

    @Override
    public void onDestroyView() {
        ViewUtils.hideKeyboard(context);
        super.onDestroyView();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showNoConnection() {
        ViewUtils.showNoConnectionToast(context);
    }

    @Override
    protected SignInContract.Presenter setPresenterView() {
        return new SignInPresenter(this);
    }
}
