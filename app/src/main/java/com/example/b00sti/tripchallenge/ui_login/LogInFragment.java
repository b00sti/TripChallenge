package com.example.b00sti.tripchallenge.ui_login;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b00sti.tripchallenge.R;
import com.example.skeleton.ui.mvp_base.MvpFragment;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

/**
 * Created by b00sti on 20.11.2016
 */

@EFragment(R.layout.fragment_login)
public class LogInFragment extends MvpFragment<LogInContract.Presenter> implements LogInContract.View {
    private static final String TAG = "LogInFragment";

    @RootContext Context context;

    @ViewById(R.id.progressBar) ProgressBar progressBar;
    @ViewById(R.id.userIconImageView) ImageView userIconImageView;
    @ViewById(R.id.emailEditText) EditText emailEditText;
    @ViewById(R.id.passwordEditText) EditText passwordEditText;
    @ViewById(R.id.showPasswordCheckBox) CheckBox showPasswordCheckBox;
    @ViewById(R.id.loginImageView) ImageView loginImageView;
    @ViewById(R.id.forgotPasswordTextView) TextView forgotPasswordTextView;

    public static Fragment newInstance() {
        return (Fragment) new LogInFragment_();
    }

    @Override
    protected LogInContract.Presenter createPresenter() {
        return new LogInPresenter(this);
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
        Toast.makeText(context, "Connection Error", Toast.LENGTH_LONG).show();
    }

}
