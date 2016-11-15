package com.example.b00sti.tripchallenge.utils.helpers;

import android.graphics.Color;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-15
 */

public class ColorHelper {

    public static int parseColorFromString(String color) {
        int colorInt;
        if (color != null && !color.isEmpty() && !color.substring(0, 1).equals("#")) {
            colorInt = Color.parseColor("#" + color);
        } else {
            colorInt = Color.parseColor(color);
        }
        return colorInt;
    }
}
