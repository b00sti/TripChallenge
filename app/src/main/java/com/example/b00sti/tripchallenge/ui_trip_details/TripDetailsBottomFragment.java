package com.example.b00sti.tripchallenge.ui_trip_details;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.b00sti.tripchallenge.R;
import com.example.b00sti.tripchallenge.main.MainActivity;
import com.example.b00sti.tripchallenge.model.RealmChallenge;
import com.example.b00sti.tripchallenge.model.RealmHelper;
import com.example.b00sti.tripchallenge.model.RealmTrip;
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

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
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

    @AfterViews
    void fillChallengeAdapter() {
        realmChallenges = RealmHelper.getChallenges(tripId);
    }

    @AfterViews
    void init() {
        ctx = getActivity();
        RealmHelper.saveTrip(new RealmTrip());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        todoRV.setLayoutManager(mLayoutManager);
        placeTodoAdapter.setDataSet(realmChallenges);
        todoRV.setAdapter(placeTodoAdapter);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    private void notifyChallanges() {
        realmChallenges = RealmHelper.getChallenges(tripId);
        placeTodoAdapter.notifyDataSetChanged();
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

                RealmChallenge realmChallenge = new RealmChallenge(place);
                String id = realmChallenge.getId();
                RealmHelper.saveChallenge(realmChallenge);
                notifyChallanges();

//                Realm realm = Realm.getDefaultInstance();
//                RealmResults<RealmChallenge> realmChallenges =realm.where(RealmChallenge.class).findAll();
//                for (RealmChallenge challenge1 : realmChallenges) {
//                    CLog.d2(TAG, "onActivityResult: ", challenge1.getTitle(), "id", realmChallenge.getId());
//                }

                googlePlacesManager.getPhotoObservable(place)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .first()
                        .subscribe(bitmap -> {
                            Realm realm = Realm.getDefaultInstance();
                            RealmChallenge realmChallenge1 = RealmHelper.getChallenge(id);
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            realmChallenge1.setBitmap(stream.toByteArray());
                            RealmHelper.saveChallenge(realmChallenge1);
                            notifyChallanges();
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
