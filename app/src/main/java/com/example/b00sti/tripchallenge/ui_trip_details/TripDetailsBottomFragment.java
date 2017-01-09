package com.example.b00sti.tripchallenge.ui_trip_details;

import android.support.v4.app.Fragment;

import com.example.b00sti.tripchallenge.R;
import com.example.skeleton.ui.mvp_base.MvpFragment;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * Created by Dominik (b00sti) Pawlik on 2017-01-09
 */

@EFragment(R.layout.fragment_trip_details_bottom)
public class TripDetailsBottomFragment extends MvpFragment<TripDetailsBottomContract.Presenter> implements TripDetailsBottomContract.View {

    @Bean
    TripDetailsBottomPresenter tripDetailsBottomPresenter;

    public static Fragment newInstance() {
        return new com.example.b00sti.tripchallenge.ui_trip_details.TripDetailsBottomFragment_();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    protected TripDetailsBottomContract.Presenter setPresenterView() {
        tripDetailsBottomPresenter.setView(this);
        return tripDetailsBottomPresenter;
    }

}
