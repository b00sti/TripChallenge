package com.example.b00sti.tripchallenge.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.b00sti.tripchallenge.R;
import com.example.b00sti.tripchallenge.utils.mvp_base.MvpFragment;

import org.androidannotations.annotations.EFragment;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-10
 */

@EFragment(R.layout.fragment_dashboard)
public class ExampleFragment extends MvpFragment<ExampleContract.Presenter> implements ExampleContract.View {

    private static final String TAG = "ExampleFragment";

    public static ExampleFragment newInstance() {
        return new ExampleFragment_();
    }

    @Override
    protected ExampleContract.Presenter createPresenter() {
        return new ExamplePresenter(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
