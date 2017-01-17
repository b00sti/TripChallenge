package com.example.b00sti.tripchallenge.model;

import com.example.skeleton.android_utils.util.CLog;

import java.util.List;

import io.realm.Realm;

/**
 * Created by Dominik (b00sti) Pawlik on 2017-01-12
 */

public class RealmHelper {
    private static final String TAG = "RealmHelper";

    public static List<RealmChallenge> getChallenges(int tripId) {
        Realm realm = Realm.getDefaultInstance();
        List<RealmChallenge> all = realm.where(RealmChallenge.class).equalTo("tripId", tripId).findAll();
        List<RealmChallenge> realmChallenges = realm.copyFromRealm(all);
        realm.close();

        CLog.d(TAG, "getChallenges: size", realmChallenges.size());

        return realmChallenges;
    }

    public static RealmChallenge getChallenge(String id) {
        Realm realm = Realm.getDefaultInstance();
        RealmChallenge realmCopy = realm.where(RealmChallenge.class).equalTo("id", id).findFirst();
        RealmChallenge realmChallenge = realm.copyFromRealm(realmCopy);
        realm.close();

        return realmChallenge;
    }

    public static void saveChallenge(RealmChallenge realmChallenge) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(realmChallenge);
        realm.commitTransaction();
        realm.close();
    }

    public static List<RealmChallenge> getTrips() {
        Realm realm = Realm.getDefaultInstance();
        List<RealmChallenge> trips = realm.where(RealmChallenge.class).findAll();
        realm.close();

        CLog.d(TAG, "getTrips: size", trips.size());

        return trips;
    }

    public static void saveTrip(RealmTrip realmTrip) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(realmTrip);
        realm.commitTransaction();
        realm.close();
    }

    public static int getChallengeNextKey() {
        Realm realm = Realm.getDefaultInstance();
        int result;
        if (realm.where(RealmTrip.class).max("id") != null) {
            result = realm.where(RealmTrip.class).max("id").intValue() + 1;
        } else {
            result = 0;
        }
        realm.close();
        return result;
    }
}
