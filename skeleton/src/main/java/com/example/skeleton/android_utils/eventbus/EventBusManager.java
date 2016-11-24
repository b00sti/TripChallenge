package com.example.skeleton.android_utils.eventbus;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.example.skeleton.android_utils.navigation.drawer.DrawerUtils;

import org.greenrobot.eventbus.EventBus;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-14
 */

public class EventBusManager {

    public static void register(@NonNull Object subscriber) {
        checkNotNull(subscriber);
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(@NonNull Object subscriber) {
        checkNotNull(subscriber);
        if (EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().unregister(subscriber);
        }
    }

    public static void postSwitchDrawerFragment(@NonNull Fragment targetFragment, @DrawerUtils.DrawerTab int drawerSelectedTabId) {
        EventBus.getDefault().post(new SwitchDrawerFragmentEvent(targetFragment, drawerSelectedTabId));
    }
}
