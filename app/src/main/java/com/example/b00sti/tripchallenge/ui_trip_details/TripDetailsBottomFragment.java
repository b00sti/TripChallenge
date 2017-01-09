package com.example.b00sti.tripchallenge.ui_trip_details;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.b00sti.tripchallenge.R;
import com.example.b00sti.tripchallenge.main.MainActivity;
import com.example.b00sti.tripchallenge.utils.helpers.GooglePlacesManager;
import com.example.skeleton.ui.mvp_base.MvpFragment;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Dominik (b00sti) Pawlik on 2017-01-09
 */

@EFragment(R.layout.fragment_trip_details_bottom)
public class TripDetailsBottomFragment extends MvpFragment<TripDetailsBottomContract.Presenter> implements TripDetailsBottomContract.View {
    private static final String TAG = "TripDetailsBottomFragme";

    @Bean
    TripDetailsBottomPresenter tripDetailsBottomPresenter;

    @Bean
    GooglePlacesManager googlePlacesManager;

    @Bean
    PlaceTodoAdapter placeTodoAdapter;

    List<PlaceToDoItem> places = new ArrayList<>();

    @ViewById(R.id.todoRV) RecyclerView todoRV;

    public static Fragment newInstance() {
        return new com.example.b00sti.tripchallenge.ui_trip_details.TripDetailsBottomFragment_();
    }

    @Click(R.id.connectB)
    void connect() {
        showPlacePicker();
    }

    @AfterViews
    void init() {
        ctx = getActivity();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, ctx);
                String toastMsg = String.format("Place: %s", place.getName());
                //mainPlaceTV.setText(place.getName());
                place.getRating();
                Toast.makeText(ctx, toastMsg, Toast.LENGTH_LONG).show();
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).setCollapsedTitleL(place.getName().toString());
                }
                final Bitmap[] bitmap2 = {null};

                googlePlacesManager.getPhotoObservable(place)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(bitmap -> {
                            if (bitmap2[0] == null) {
                                bitmap2[0] = bitmap;
                                places.add(new PlaceToDoItem(bitmap, place.getName().toString(), place.getRating()));
                                Log.d(TAG, "onActivityResult: " + places.size());
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                todoRV.setLayoutManager(mLayoutManager);
                                placeTodoAdapter.setDataSet(places);
                                todoRV.setAdapter(placeTodoAdapter);
                            }
                        });
            }
        }
    }

    private void showPlacePicker() {
        int PLACE_PICKER_REQUEST = 1;
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(ctx), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }
}
