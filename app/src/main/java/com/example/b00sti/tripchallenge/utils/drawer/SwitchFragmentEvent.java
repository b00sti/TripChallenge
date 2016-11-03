package com.example.b00sti.tripchallenge.utils.drawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-03
 */

public class SwitchFragmentEvent {
    private
    @DrawerUtils.DrawerTab int drawerSelectedTabId = DrawerUtils.NO_TAB;
    private Fragment targetFragment;
    private Bundle extras;

    public SwitchFragmentEvent(Fragment targetFragment, @DrawerUtils.DrawerTab int drawerSelectedTabId) {
        this.targetFragment = targetFragment;
        this.drawerSelectedTabId = drawerSelectedTabId;
    }

    public SwitchFragmentEvent(Fragment targetFragment, @DrawerUtils.DrawerTab int drawerSelectedTabId, Bundle extras) {
        this.targetFragment = targetFragment;
        this.drawerSelectedTabId = drawerSelectedTabId;
        this.extras = extras;
    }

    public Fragment getTargetFragment() {
        return targetFragment;
    }

    public
    @DrawerUtils.DrawerTab
    int getDrawerItemSelected() {
        return drawerSelectedTabId;
    }

    public Bundle getExtras() {
        return extras;
    }
}
