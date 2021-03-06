package com.example.b00sti.tripchallenge.utils.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.example.b00sti.tripchallenge.model.TripCity;
import com.example.skeleton.android_utils.util.CLog;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.Places;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by b00sti on 05.12.2016.
 */
@EBean(scope = EBean.Scope.Singleton)
public class GooglePlacesManager {
    private static final String TAG = "GooglePlacesManager";

    @RootContext
    Context ctx;

    public GoogleApiClient getmGoogleApiClient() {
        return mGoogleApiClient;
    }

    public void setmGoogleApiClient(GoogleApiClient mGoogleApiClient) {
        this.mGoogleApiClient = mGoogleApiClient;
    }

    private GoogleApiClient mGoogleApiClient;

    GooglePlacesManager() {
    }

    @AfterInject
    void init() {
        mGoogleApiClient = new GoogleApiClient
                .Builder(ctx)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                //.enableAutoManage(ctx, this)
                .build();
    }

    public void connect() {
        if (!mGoogleApiClient.isConnected() && !mGoogleApiClient.isConnecting()) {
            mGoogleApiClient.connect();
        }
    }

    public void disconnect() {
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    public Observable<Bitmap> getPhotoObservable(Place place) {
        return Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                Bitmap image = null;

                PlacePhotoMetadataResult result = Places.GeoDataApi.getPlacePhotos(mGoogleApiClient, place.getId()).await();

                if (result.getStatus().isSuccess()) {
                    PlacePhotoMetadataBuffer photoMetadataBuffer = result.getPhotoMetadata();

                    for (PlacePhotoMetadata placePhotoMetadata : photoMetadataBuffer) {
                        image = placePhotoMetadata.getPhoto(mGoogleApiClient).await()
                                .getBitmap();
                        subscriber.onNext(image);
                    }
/*
                    PlacePhotoMetadata photo = photoMetadataBuffer.get(0);
                    image = photo.getPhoto(mGoogleApiClient).await()
                            .getBitmap();
                    CharSequence attribution = photo.getAttributions();
                    CLog.d(TAG, "getPhoto: attribution ", attribution.toString())*/;
                }
                //subscriber.onNext(image);
            }
        });
    }

    private boolean isPermissionGranted(@NonNull Activity activity) {
        if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(activity, perms, 0);
            return false;
        }
        return true;
    }

    public Observable<TripCity> getCurrentPlaces(@NonNull Activity activity) {
        Log.d(TAG, "getCurrentPlaces() called with: activity = [" + activity + "]");
        final Observable[] observable = {Observable.empty()};
        if (isPermissionGranted(activity)) {
            CLog.d(TAG, "getCurrentPlaces isPermissionGranted ", isPermissionGranted(activity));
            try {
                PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi.getCurrentPlace(mGoogleApiClient, null);
                result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
                    @Override
                    public void onResult(@NonNull PlaceLikelihoodBuffer likelyPlaces) {
                        Log.d(TAG, "onResult: " + likelyPlaces.getCount());
                        Log.d(TAG, "getCurrentPlaces: size " + likelyPlaces.getCount());
                        for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                            Log.i(TAG, String.format("Place '%s' has likelihood: %g", placeLikelihood.getPlace().getName(), placeLikelihood.getLikelihood()));
                            TripCity tripCity = new TripCity(placeLikelihood, GeoManager.getCityName(ctx, placeLikelihood.getPlace().getLatLng()));
                            Log.d(TAG, "title: " + tripCity.getCityTitle());

                            observable[0] = Observable.just(tripCity);

                        }
                        likelyPlaces.release();
                    }
                });
            } catch (SecurityException e) {
                Log.e(TAG, "getCurrentPlaces: " + e.getMessage());
            }

        }
        return observable[0];
    }
}
