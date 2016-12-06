package com.example.b00sti.tripchallenge.model;

import com.google.android.gms.location.places.PlaceLikelihood;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-12-06
 */

public class TripCity {

    private PlaceLikelihood placeLikelihood;
    private String cityTitle;

    public TripCity(PlaceLikelihood placeLikelihood, String cityTitle) {
        this.placeLikelihood = placeLikelihood;
        this.cityTitle = cityTitle;
    }

    public PlaceLikelihood getPlaceLikelihood() {
        return placeLikelihood;
    }

    public void setPlaceLikelihood(PlaceLikelihood placeLikelihood) {
        this.placeLikelihood = placeLikelihood;
    }

    public String getCityTitle() {
        return cityTitle;
    }

    public void setCityTitle(String cityTitle) {
        this.cityTitle = cityTitle;
    }
}
