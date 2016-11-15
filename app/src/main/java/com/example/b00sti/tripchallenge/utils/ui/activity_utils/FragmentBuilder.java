package com.example.b00sti.tripchallenge.utils.ui.activity_utils;

import android.support.v4.app.Fragment;

import com.example.b00sti.tripchallenge.ui_dashboard.DashboardFragment;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-15
 */

public class FragmentBuilder {
    public final static int DASHBOARD_FRAGMENT = 0;

    public static Fragment getInnerActivityFragment(int fragmentId) {
        switch (fragmentId) {
            case FragmentBuilder.DASHBOARD_FRAGMENT:
                return DashboardFragment.newInstance();
            default:
                return new Fragment();
        }
    }
}
