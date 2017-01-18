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
import com.example.b00sti.tripchallenge.model.RealmChallenge;
import com.example.b00sti.tripchallenge.model.RealmTrip;
import com.example.b00sti.tripchallenge.realm_services.TripsRealmService;
import com.example.b00sti.tripchallenge.utils.helpers.GooglePlacesManager;
import com.example.skeleton.ui.mvp_base.MvpFragment;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.io.ByteArrayOutputStream;
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

    @Bean
    TripsRealmService tripsRealmService;

    List<RealmChallenge> realmChallenges = new ArrayList<>();

    @ViewById(R.id.todoRV) RecyclerView todoRV;

    int tripId = 0;

    public static Fragment newInstance() {
        return new com.example.b00sti.tripchallenge.ui_trip_details.TripDetailsBottomFragment_();
    }

    @Click(R.id.connectB)
    void connect() {
        showPlacePicker();
    }
/*
    @AfterViews
    void fillChallengeAdapter() {
      realmChallenges = RealmHelper.getChallenges(tripId);
    }*/

    @AfterViews
    void init() {
        ctx = getActivity();

        //RealmHelper.saveTrip(new RealmTrip());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        todoRV.setLayoutManager(mLayoutManager);
        placeTodoAdapter.setDataSet(realmChallenges);
        todoRV.setAdapter(placeTodoAdapter);
    }

    @AfterInject
    void initR() {
        tripsRealmService.addTripAsync(new RealmTrip(), null);
        realmChallenges = tripsRealmService.getChallanges(tripId);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @UiThread
    void notifyChallanges() {
        //realmChallenges = RealmHelper.getChallenges(tripId);
        //realmChallenges = tripsRealmService.getChallanges(tripId);
        Log.d(TAG, "notifyChallanges: " + realmChallenges.size());
        //placeTodoAdapter.notifyDataSetChanged();
        placeTodoAdapter.setDataSet(realmChallenges);
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
                Toast.makeText(ctx, toastMsg, Toast.LENGTH_LONG).show();
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).setCollapsedTitleL(place.getName().toString());
                }

                RealmChallenge realmChallenge = new RealmChallenge(place, tripId);
                String id = realmChallenge.getId();
                //RealmHelper.saveChallenge(realmChallenge);
                tripsRealmService.addChallangeAsync(realmChallenge, new TripsRealmService.OnTransactionCallback() {
                    @Override
                    public void onRealmSuccess() {
                        notifyChallanges();
                    }

                    @Override
                    public void onRealmError(Throwable e) {

                    }
                });

//                Realm realm = Realm.getDefaultInstance();
//                RealmResults<RealmChallenge> realmChallenges =realm.where(RealmChallenge.class).findAll();
//                for (RealmChallenge challenge1 : realmChallenges) {
//                    CLog.d2(TAG, "onActivityResult: ", challenge1.getTitle(), "id", realmChallenge.getId());
//                }

                googlePlacesManager.getPhotoObservable(place)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .take(1)
                        .subscribe(bitmap -> {
                            Log.d(TAG, "onActivityResult: " + "bitmap dupa` ");
                            //Realm realm = Realm.getDefaultInstance();
                            //RealmChallenge realmChallenge1 = RealmHelper.getChallenge(id);
                            tripsRealmService.getChallangeAsObservable(id)
                                    .take(1)
                                    .subscribe(realmChallenge1 -> {
                                        Log.d(TAG, "onActivityResult: bitmap tylek");
                                        tripsRealmService.makaTransaction(new TripsRealmService.onTransaction() {
                                            @Override
                                            public void onTransaction() {
                                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                                bitmap.compress(Bitmap.CompressFormat.JPEG, 10, stream);
                                                realmChallenge1.setBitmap(stream.toByteArray());
                                            }

                                            @Override
                                            public void afterTransaction() {
                                                Toast.makeText(ctx, "Bitmap downloaded!", Toast.LENGTH_SHORT).show();
                                                notifyChallanges();
                                            }
                                        });
                                    });


                            //RealmHelper.saveChallenge(realmChallenge1);


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