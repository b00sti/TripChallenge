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
            case DrawerUtils.TAB_0:
                fragment = fragmentBuilder.newFragment(FragmentBuilder.DASHBOARD);
                break;
            case DrawerUtils.TAB_1:
                fragment = fragmentBuilder.newFragment(FragmentBuilder.DASHBOARD);
                break;
            case DrawerUtils.TAB_2:
                fragment = fragmentBuilder.newFragment(FragmentBuilder.DASHBOARD);
                break;
            case DrawerUtils.TAB_3:
                break;
            case DrawerUtils.TAB_4:
                break;
            case DrawerUtils.TAB_5:
                break;
            case DrawerUtils.TAB_6:
                break;
            case DrawerUtils.TAB_7:
                break;
            case DrawerUtils.TAB_8:
                break;
            case DrawerUtils.TAB_9:
                break;
            case DrawerUtils.TAB_NO:
                break;
        }
        return fragment;
    }


    public static List<DrawerItem> getDrawerItems() {
        List<DrawerItem> drawerItems = new ArrayList<>();
        drawerItems.add(new DrawerItem(DrawerUtils.TAB_0, R.string.signup_error_title, R.drawable.common_google_signin_btn_icon_dark_focused));
        drawerItems.add(new DrawerItem(DrawerUtils.TAB_1, R.string.signup_error_message, R.drawable.common_full_open_on_phone));
        drawerItems.add(new DrawerItem(DrawerUtils.TAB_2, R.string.login_error_title, R.drawable.common_google_signin_btn_text_dark_disabled));
        return drawerItems;
    }
}
