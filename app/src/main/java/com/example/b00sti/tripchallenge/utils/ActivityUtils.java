package com.example.b00sti.tripchallenge.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.b00sti.tripchallenge.main.InnerViewActivity_;
import com.example.skeleton.R;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-15
 */

public class ActivityUtils {
    private static final String TAG = "ActivityUtils";

    public static void startInnerViewActivity(@NonNull Activity context, int fragmentId, String title) {
        checkNotNull(context);

        Intent intent = new Intent(context, InnerViewActivity_.class);
        intent.putExtra(context.getString(R.string.bundle_fragment), fragmentId);
        intent.putExtra("name", title);
        context.startActivityForResult(intent, -1);
    }

    public static void startInnerViewActivity(@NonNull Activity context, int fragmentId, String title, Class<?> cls) {
        checkNotNull(context);

        Intent intent = new Intent(context, cls);
        intent.putExtra(context.getString(R.string.bundle_fragment), fragmentId);
        intent.putExtra("name", title);
        context.startActivityForResult(intent, -1);
    }
}
