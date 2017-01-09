package com.example.b00sti.tripchallenge.main;

import android.support.v4.app.Fragment;

import com.example.b00sti.tripchallenge.R;
import com.example.skeleton.android_utils.navigation.drawer.DrawerUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-18
 */

public class DrawerHelper {
    public static Fragment getFragmentForTab(FragmentBuilder fragmentBuilder, @DrawerUtils.DrawerTab int tab) {
        Fragment fragment = null;
        switch (tab) {
            case DrawerUtils.TAB_00:
                fragment = fragmentBuilder.newFragment(FragmentBuilder.TRIPS_BOTTOM);
                break;
            case DrawerUtils.TAB_01:
                fragment = fragmentBuilder.newFragment(FragmentBuilder.LOG_IN);
                break;
            case DrawerUtils.TAB_02:
                fragment = fragmentBuilder.newFragment(FragmentBuilder.SIGN_IN);
                break;
            case DrawerUtils.TAB_03:
                break;
            case DrawerUtils.TAB_04:
                break;
            case DrawerUtils.TAB_05:
                break;
            case DrawerUtils.TAB_06:
                break;
            case DrawerUtils.TAB_07:
                break;
            case DrawerUtils.TAB_08:
                break;
            case DrawerUtils.TAB_09:
                break;
            case DrawerUtils.TAB_10:
                break;
            case DrawerUtils.TAB_11:
                break;
            case DrawerUtils.TAB_12:
                break;
            case DrawerUtils.TAB_13:
                break;
            case DrawerUtils.TAB_14:
                break;
            case DrawerUtils.TAB_15:
                break;
            case DrawerUtils.TAB_NO:
                break;
            default:
        }
        return fragment;
    }

    public static Fragment getTopFragmentForTab(FragmentBuilder fragmentBuilder, @DrawerUtils.DrawerTab int tab) {
        Fragment fragment = null;
        switch (tab) {
            case DrawerUtils.TAB_00:
                fragment = fragmentBuilder.newFragment(FragmentBuilder.TRIPS_TOP);
                break;
            case DrawerUtils.TAB_01:
                fragment = fragmentBuilder.newFragment(FragmentBuilder.TRIPS_TOP);
                break;
            case DrawerUtils.TAB_02:
                fragment = fragmentBuilder.newFragment(FragmentBuilder.EMPTY);
                break;
            case DrawerUtils.TAB_03:
                break;
            case DrawerUtils.TAB_04:
                break;
            case DrawerUtils.TAB_05:
                break;
            case DrawerUtils.TAB_06:
                break;
            case DrawerUtils.TAB_07:
                break;
            case DrawerUtils.TAB_08:
                break;
            case DrawerUtils.TAB_09:
                break;
            case DrawerUtils.TAB_10:
                break;
            case DrawerUtils.TAB_11:
                break;
            case DrawerUtils.TAB_12:
                break;
            case DrawerUtils.TAB_13:
                break;
            case DrawerUtils.TAB_14:
                break;
            case DrawerUtils.TAB_15:
                break;
            case DrawerUtils.TAB_NO:
                break;
            default:
        }
        return fragment;
    }

    public static List<DrawerItem> getDrawerItems() {
        List<DrawerItem> drawerItems = new ArrayList<>();
        drawerItems.add(new DrawerItem(DrawerUtils.TAB_00, R.string.trips, R.drawable.common_google_signin_btn_icon_dark_focused));
        drawerItems.add(new DrawerItem(DrawerUtils.TAB_01, R.string.login_button_label, R.drawable.common_full_open_on_phone));
        drawerItems.add(new DrawerItem(DrawerUtils.TAB_02, R.string.sign_up_text, R.drawable.common_google_signin_btn_text_dark_disabled));
        return drawerItems;
    }
}
