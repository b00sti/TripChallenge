package com.example.b00sti.tripchallenge.ui_trip_details;

import com.example.skeleton.ui.mvp_base.BasePresenter;

/**
 * Created by Dominik (b00sti) Pawlik on 2017-01-09
 */

public class TripDetailsBottomContract {

    interface View {

        void showProgressBar();

        void hideProgressBar();

    }

    interface Presenter extends BasePresenter {

        void refreshAllViews();

        void connect();

    }

}
