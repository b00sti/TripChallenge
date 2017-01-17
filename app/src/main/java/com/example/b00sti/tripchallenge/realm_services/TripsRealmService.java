package com.example.b00sti.tripchallenge.realm_services;

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

    private final Realm realm;

    public TripsRealmService() {
        realm = Realm.getDefaultInstance();
    }

    public void closeRealm() {
        if (!realm.isClosed()) {
            realm.close();
        }
    }

    public void addTripAsync(RealmTrip realmTrip, final OnTransactionCallback onTransactionCallback) {
        realm.executeTransactionAsync(realm1 -> realm1.copyToRealmOrUpdate(realmTrip), onTransactionCallback::onRealmSuccess, onTransactionCallback::onRealmError);
    }

    public void addChallangeAsync(RealmChallenge realmChallenge, final OnTransactionCallback onTransactionCallback) {
        realm.executeTransactionAsync(realm1 -> realm1.copyToRealmOrUpdate(realmChallenge), onTransactionCallback::onRealmSuccess, onTransactionCallback::onRealmError);
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

    public Observable<RealmChallenge> getChallangeAsyncAsObservable(String id) {
        return realm.where(RealmChallenge.class).equalTo("id", id).findFirstAsync().asObservable();
    }

    public RealmResults<RealmTrip> getTripsAsync() {
        return realm.where(RealmTrip.class).findAllAsync();
    }

    public RealmResults<RealmChallenge> getChallangesAsync() {
        return realm.where(RealmChallenge.class).findAllAsync();
    }

    public Observable<RealmResults<RealmTrip>> getTripsAsyncAsObservable() {
        return realm.where(RealmTrip.class).findAllAsync().asObservable();
    }

    public Observable<RealmResults<RealmChallenge>> getChallangesAsyncAsObservable() {
        return realm.where(RealmChallenge.class).findAllAsync().asObservable();
    }

    public interface OnTransactionCallback {
        void onRealmSuccess();

        void onRealmError(Throwable e);
    }
}
