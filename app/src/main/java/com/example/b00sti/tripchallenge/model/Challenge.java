package com.example.b00sti.tripchallenge.model;

import com.google.android.gms.location.places.Place;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Dominik (b00sti) Pawlik on 2017-01-12
 */

public class Challenge extends RealmObject {
    private int tripId;

    @PrimaryKey
    private String id;
    private String title;
    private double lat;
    private double lng;
    private float rating;
    private byte[] bitmap;


    public Challenge() {
    }

    public Challenge(Place place) {
        this.id = place.getId();
        this.title = place.getName().toString();
        this.lat = place.getLatLng().latitude;
        this.lng = place.getLatLng().longitude;
        this.rating = place.getRating();
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public byte[] getBitmap() {
        return bitmap;
    }

    public void setBitmap(byte[] bitmap) {
        this.bitmap = bitmap;
    }
}
