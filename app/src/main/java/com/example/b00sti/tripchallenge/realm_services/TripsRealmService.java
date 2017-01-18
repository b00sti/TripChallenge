package com.example.b00sti.tripchallenge.realm_services;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.b00sti.tripchallenge.model.RealmChallenge;
import com.example.b00sti.tripchallenge.model.RealmTrip;

import org.androidannotations.annotations.EBean;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;


/**
 * Created by Dominik (b00sti) Pawlik on 2017-01-17
 */

@EBean
public class TripsRealmService {
    private static final String TAG = "TripsRealmService";

    private final Realm realm;

    public TripsRealmService() {
        realm = Realm.getDefaultInstance();
        Log.d(TAG, "TripsRealmService: " + realm);
    }

    public Realm getRealm() {
        return realm;
    }

    public void makaTransaction(onTransaction transaction) {
        realm.beginTransaction();
        transaction.onTransaction();
        realm.commitTransaction();
        transaction.afterTransaction();
    }

    public void closeRealm() {
        if (!realm.isClosed()) {
            realm.close();
        }
    }

    public void addTripAsync(@NonNull RealmTrip realmTrip, @Nullable final OnTransactionCallback onTransactionCallback) {
        if (onTransactionCallback != null) {
            realm.executeTransactionAsync(realm1 -> realm1.copyToRealmOrUpdate(realmTrip), onTransactionCallback::onRealmSuccess, onTransactionCallback::onRealmError);
        } else {
            realm.executeTransactionAsync(realm1 -> realm1.copyToRealmOrUpdate(realmTrip));
        }

    }

    public void addChallangeAsync(@Nullable RealmChallenge realmChallenge, @Nullable final OnTransactionCallback onTransactionCallback) {
        if (onTransactionCallback != null) {
            realm.executeTransactionAsync(realm1 -> realm1.copyToRealmOrUpdate(realmChallenge), onTransactionCallback::onRealmSuccess, onTransactionCallback::onRealmError);
        } else {
            realm.executeTransactionAsync(realm1 -> realm1.copyToRealmOrUpdate(realmChallenge));
        }
    }

    public RealmTrip getTripAsync(int id) {
        return realm.where(RealmTrip.class).equalTo("id", id).findFirstAsync();
    }

    public Observable<RealmTrip> getTripAsyncAsObservable(int id) {
        return realm.where(RealmTrip.class).equalTo("id", id).findFirstAsync().asObservable();
    }

    public RealmChallenge getChallangeAsync(String id) {
        return realm.where(RealmChallenge.class).equalTo("id", id).findFirstAsync();
    }

    public Observable<RealmChallenge> getChallangeAsObservable(String id) {
        return realm.where(RealmChallenge.class).equalTo("id", id).findFirst().asObservable();
    }

    public RealmResults<RealmTrip> getTripsAsync() {
        return realm.where(RealmTrip.class).findAllAsync();
    }

    public RealmResults<RealmChallenge> getChallanges(int tripId) {
        return realm.where(RealmChallenge.class).equalTo("tripId", tripId).findAll();
    }

    public Observable<RealmResults<RealmTrip>> getTripsAsObservable() {
        return realm.where(RealmTrip.class).findAll().asObservable();
    }

    public Observable<RealmResults<RealmChallenge>> getChallangesAsyncAsObservable(int tripId) {
        return realm.where(RealmChallenge.class).equalTo("tripId", tripId).findAllAsync().asObservable();
    }

    public interface onTransaction {
        void onTransaction();

        void afterTransaction();
    }

    public interface OnTransactionCallback {
        void onRealmSuccess();

        void onRealmError(Throwable e);
    }
}

