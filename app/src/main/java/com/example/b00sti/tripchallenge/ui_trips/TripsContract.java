package com.example.b00sti.tripchallenge.ui_trips;

import com.example.skeleton.ui.mvp_base.BasePresenter;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-10
 */

public class TripsContract {

    interface View {

        void showProgressBar();

        void hideProgressBar();

    }

    interface Presenter extends BasePresenter {

        void refreshAllViews();

        void connect();

    }

}
