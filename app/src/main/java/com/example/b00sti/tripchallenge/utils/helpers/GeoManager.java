package com.example.b00sti.tripchallenge.utils.helpers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-12-06
 */

public class GeoManager {
    private static final String TAG = "GeoManager";

    public static Address getAddressFromLatLng(Context context, LatLng latLng) {
        Geocoder geoCoder = new Geocoder(context);
        List<Address> matches = null;
        try {
            matches = geoCoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
        } catch (IOException e) {
            Log.e(TAG, "getAddressFromLatLng: " + e.getMessage());
            e.printStackTrace();
        }
        Address bestMatch = null;
        if (matches != null) {
            bestMatch = matches.get(0);
        }
        return bestMatch;
    }

    public static String getCityName(Context context, LatLng latLng) {
        Address address = getAddressFromLatLng(context, latLng);
        String result = "";
        if (address != null) {
            result = address.getLocality();
        }
        return result;
    }
}
