package com.example.b00sti.tripchallenge.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.b00sti.tripchallenge.main.InnerViewActivity_;
import com.example.skeleton.R;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-15
 */

public class ActivityUtils {
    private static final String TAG = "ActivityUtils";

    public static void startInnerViewActivity(@NonNull Activity context, int fragmentId) {
        Log.d(TAG, "startInnerViewActivity() called with: context = [" + context + "], fragmentId = [" + fragmentId + "]");
        checkNotNull(context);

        Intent intent = new Intent(context, InnerViewActivity_.class);
        intent.putExtra(context.getString(R.string.bundle_fragment), fragmentId);
        context.startActivityForResult(intent, -1);
    }
}
