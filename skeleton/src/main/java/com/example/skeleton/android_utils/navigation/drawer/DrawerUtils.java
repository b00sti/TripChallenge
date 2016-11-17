package com.example.skeleton.android_utils.navigation.drawer;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-03
 */

public class DrawerUtils {
    public static final int TAB_NO = -1;
    public static final int TAB_0 = 0;
    public static final int TAB_1 = 1;
    public static final int TAB_2 = 2;
    public static final int TAB_3 = 3;
    public static final int TAB_4 = 4;
    public static final int TAB_5 = 5;
    public static final int TAB_6 = 6;
    public static final int TAB_7 = 7;
    public static final int TAB_8 = 8;
    public static final int TAB_9 = 9;
    public static final int TAB_10 = 10;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TAB_NO,
            TAB_0,
            TAB_1,
            TAB_2,
            TAB_3,
            TAB_4,
            TAB_5,
            TAB_6,
            TAB_7,
            TAB_8,
            TAB_9,
            TAB_10
    })
    public @interface DrawerTab {
    }
}
