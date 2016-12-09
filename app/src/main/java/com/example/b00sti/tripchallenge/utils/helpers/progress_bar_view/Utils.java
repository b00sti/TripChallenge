package com.example.b00sti.tripchallenge.utils.helpers.progress_bar_view;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-12-09
 */

import android.content.res.Resources;

public final class Utils {

    private Utils() {
    }

    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public static float sp2px(Resources resources, float sp) {
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }
}
