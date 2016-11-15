package com.example.b00sti.tripchallenge.utils.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;

import com.example.b00sti.tripchallenge.App;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-15
 */

public class DeviceHelper {

    public static String getDensityInfoAsString(Context context) {
        int density = context.getResources().getDisplayMetrics().densityDpi;
        switch (density) {
            case DisplayMetrics.DENSITY_MEDIUM:
                return "MDPI";
            case DisplayMetrics.DENSITY_HIGH:
                return "HDPI";
            case DisplayMetrics.DENSITY_LOW:
                return "LDPI";
            case DisplayMetrics.DENSITY_XHIGH:
                return "XHDPI";
            case DisplayMetrics.DENSITY_TV:
                return "TV";
            case DisplayMetrics.DENSITY_XXHIGH:
                return "XXHDPI";
            case DisplayMetrics.DENSITY_XXXHIGH:
                return "XXXHDPI";
            default:
                return "Unknown";
        }
    }

    public static void logDevice(String TAG, Activity activity) {
        int screenSize = activity.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

        String screenSizeString;
        switch (screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                screenSizeString = "XLarge screen";
                break;
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                screenSizeString = "Large screen";
                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                screenSizeString = "Normal screen";
                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                screenSizeString = "Small screen";
                break;
            default:
                screenSizeString = "Screen size is neither xlarge, large, normal or small";
        }

        Log.d(TAG, "logDevice screen size: " + screenSizeString + ", density: " + getDensityInfoAsString(activity));
    }

    public static int getDensity(App app) {
        return app.getResources().getDisplayMetrics().densityDpi;
    }
}
