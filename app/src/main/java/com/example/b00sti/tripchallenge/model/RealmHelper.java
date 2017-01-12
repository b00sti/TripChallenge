package com.example.b00sti.tripchallenge.model;

import com.example.skeleton.android_utils.util.CLog;

import java.util.List;

import io.realm.Realm;

/**
 * Created by Dominik (b00sti) Pawlik on 2017-01-12
 */

public class RealmHelper {
    private static final String TAG = "RealmHelper";

    public static List<Challenge> getChallenges(int tripId) {
        Realm realm = Realm.getDefaultInstance();
        List<Challenge> all = realm.where(Challenge.class).equalTo("tripId", tripId).findAll();
        List<Challenge> challenges = realm.copyFromRealm(all);
        realm.close();

        CLog.d(TAG, "getChallenges: size", challenges.size());

        return challenges;
    }

    public static Challenge getChallenge(String id) {
        Realm realm = Realm.getDefaultInstance();
        Challenge realmCopy = realm.where(Challenge.class).equalTo("id", id).findFirst();
        Challenge challenge = realm.copyFromRealm(realmCopy);
        realm.close();

        return challenge;
    }

    public static void saveChallenge(Challenge challenge) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(challenge);
        realm.commitTransaction();
        realm.close();
    }

    public static List<Challenge> getTrips() {
        Realm realm = Realm.getDefaultInstance();
        List<Challenge> trips = realm.where(Challenge.class).findAll();
        realm.close();

        CLog.d(TAG, "getTrips: size", trips.size());

        return trips;
    }

    public static void saveTrip(Trip trip) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(trip);
        realm.commitTransaction();
        realm.close();
    }

    public static int getChallengeNextKey() {
        Realm realm = Realm.getDefaultInstance();
        int result;
        if (realm.where(Trip.class).max("id") != null) {
            result = realm.where(Trip.class).max("id").intValue() + 1;
        } else {
            result = 0;
        }
        realm.close();
        return result;
    }
}
