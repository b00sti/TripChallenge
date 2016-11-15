package com.example.b00sti.tripchallenge.ui_dashboard;

import com.example.b00sti.tripchallenge.utils.util.mvp_base.BasePresenter;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-10
 */

interface DashboardContract {

    interface View {

        void showProgressBar();

        void hideProgressBar();

    }

    interface Presenter extends BasePresenter {

        void refreshAllViews();

    }

}
