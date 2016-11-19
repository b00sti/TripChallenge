package com.example.skeleton.android_utils.navigation.drawer;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-03
 */

public class DrawerUtils {
    public static final int TAB_NO = -1;
    public static final int TAB_00 = 0;
    public static final int TAB_01 = 1;
    public static final int TAB_02 = 2;
    public static final int TAB_03 = 3;
    public static final int TAB_04 = 4;
    public static final int TAB_05 = 5;
    public static final int TAB_06 = 6;
    public static final int TAB_07 = 7;
    public static final int TAB_08 = 8;
    public static final int TAB_09 = 9;
    public static final int TAB_10 = 10;
    public static final int TAB_11 = 11;
    public static final int TAB_12 = 12;
    public static final int TAB_13 = 13;
    public static final int TAB_14 = 14;
    public static final int TAB_15 = 15;


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TAB_NO,
            TAB_00,
            TAB_01,
            TAB_02,
            TAB_03,
            TAB_04,
            TAB_05,
            TAB_06,
            TAB_07,
            TAB_08,
            TAB_09,
            TAB_10,
            TAB_11,
            TAB_12,
            TAB_13,
            TAB_14,
            TAB_15,
    })
    public @interface DrawerTab {
    }
}
