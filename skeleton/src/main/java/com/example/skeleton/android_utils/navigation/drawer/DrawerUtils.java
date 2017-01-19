package com.example.skeleton.android_utils.navigation.drawer;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

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

    @NonNull
    @DrawerUtils.DrawerTab
    public static int getDrawerTab(int position1) {
        @DrawerUtils.DrawerTab int tabToSelect;

        switch (position1) {
            case DrawerUtils.TAB_00:
                tabToSelect = DrawerUtils.TAB_00;
                break;
            case DrawerUtils.TAB_01:
                tabToSelect = DrawerUtils.TAB_01;
                break;
            case DrawerUtils.TAB_02:
                tabToSelect = DrawerUtils.TAB_02;
                break;
            case DrawerUtils.TAB_03:
                tabToSelect = DrawerUtils.TAB_03;
                break;
            case DrawerUtils.TAB_04:
                tabToSelect = DrawerUtils.TAB_04;
                break;
            case DrawerUtils.TAB_05:
                tabToSelect = DrawerUtils.TAB_05;
                break;
            case DrawerUtils.TAB_06:
                tabToSelect = DrawerUtils.TAB_06;
                break;
            case DrawerUtils.TAB_07:
                tabToSelect = DrawerUtils.TAB_07;
                break;
            case DrawerUtils.TAB_08:
                tabToSelect = DrawerUtils.TAB_08;
                break;
            case DrawerUtils.TAB_09:
                tabToSelect = DrawerUtils.TAB_09;
                break;
            case DrawerUtils.TAB_10:
                tabToSelect = DrawerUtils.TAB_10;
                break;
            case DrawerUtils.TAB_11:
                tabToSelect = DrawerUtils.TAB_11;
                break;
            case DrawerUtils.TAB_12:
                tabToSelect = DrawerUtils.TAB_12;
                break;
            case DrawerUtils.TAB_13:
                tabToSelect = DrawerUtils.TAB_13;
                break;
            case DrawerUtils.TAB_14:
                tabToSelect = DrawerUtils.TAB_14;
                break;
            case DrawerUtils.TAB_15:
                tabToSelect = DrawerUtils.TAB_15;
                break;
            case DrawerUtils.TAB_NO:
                tabToSelect = DrawerUtils.TAB_NO;
                break;
            default:
                tabToSelect = DrawerUtils.TAB_NO;
        }

        return tabToSelect;
    }


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
