package com.example.skeleton.android_utils.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.skeleton.R;
import com.example.skeleton.ui.mvp_base.MvpFragment;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-10
 */

public class ExampleFragment extends MvpFragment<ExampleContract.Presenter> implements ExampleContract.View {

    private static final String TAG = "ExampleFragment";

    public static ExampleFragment newInstance() {
        return new ExampleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
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
