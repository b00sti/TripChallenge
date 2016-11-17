package com.example.skeleton.android_utils.util;

import android.support.v4.app.Fragment;

import com.example.skeleton.android_utils.navigation.DrawerUtils;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-17
 */

public class TestBaseMainActivity extends BaseMainActivity {
    @Override
    public int getHomeAsUpIndicatorAsDrawable() {
        return 0;
    }

    @Override
    public Fragment getFragmentsAsInDrawer(DrawerUtils.TABS tabs) {
        switch (tabs) {
            DrawerUtils.TABS.NO_TAB
        }
        return null;
    }
}
