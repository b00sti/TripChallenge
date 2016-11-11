package com.example.b00sti.tripchallenge.ui_dashboard;

import com.example.b00sti.tripchallenge.mvp_base.BasePresenter;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-10
 */

public interface DashboardContract {

    interface View {
        void showProgressBar();
    }

    interface Presenter extends BasePresenter {
        void refreshData();
    }

}
