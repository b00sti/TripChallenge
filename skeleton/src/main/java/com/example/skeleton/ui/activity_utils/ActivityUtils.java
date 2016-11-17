package com.example.skeleton.ui.activity_utils;

import android.app.Activity;
import android.content.Intent;

import com.example.skeleton.R;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-15
 */

public class ActivityUtils {
    private static final String TAG = "ActivityUtils";

    public static void startInnerViewActivity(Activity context, int fragmentId) {
        Intent intent = new Intent(context, InnerViewActivity.class);
        intent.putExtra(context.getString(R.string.bundle_fragment), fragmentId);
        context.startActivityForResult(intent, -1);
    }
}
