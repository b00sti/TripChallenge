package com.example.b00sti.tripchallenge.example;

import com.example.b00sti.tripchallenge.utils.mvp_base.MvpPresenter;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-10
 */

class ExamplePresenter extends MvpPresenter<ExampleContract.View> implements ExampleContract.Presenter {

    ExamplePresenter(ExampleContract.View view) {
        super(view);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
