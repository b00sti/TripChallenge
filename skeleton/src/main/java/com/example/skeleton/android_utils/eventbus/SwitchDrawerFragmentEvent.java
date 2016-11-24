package com.example.skeleton.android_utils.eventbus;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.skeleton.android_utils.navigation.drawer.DrawerUtils;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-03
 */

@SuppressWarnings("unused")
public class SwitchDrawerFragmentEvent {

    @DrawerUtils.DrawerTab private int drawerSelectedTabId = DrawerUtils.TAB_NO;
    private Fragment targetFragment;
    private Bundle extras;

    public SwitchDrawerFragmentEvent(Fragment targetFragment, @DrawerUtils.DrawerTab int drawerSelectedTabId) {
        this.targetFragment = targetFragment;
        this.drawerSelectedTabId = drawerSelectedTabId;
    }

    public SwitchDrawerFragmentEvent(Fragment targetFragment, @DrawerUtils.DrawerTab int drawerSelectedTabId, Bundle extras) {
        this.targetFragment = targetFragment;
        this.drawerSelectedTabId = drawerSelectedTabId;
        this.extras = extras;
    }

    public Fragment getTargetFragment() {
        return targetFragment;
    }

    @DrawerUtils.DrawerTab
    public int getDrawerItemSelected() {
        return drawerSelectedTabId;
    }

    public Bundle getExtras() {
        return extras;
    }
}
