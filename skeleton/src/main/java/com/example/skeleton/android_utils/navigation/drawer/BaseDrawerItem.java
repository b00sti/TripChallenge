package com.example.skeleton.android_utils.navigation.drawer;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-03
 */

public abstract class BaseDrawerItem {

    @DrawerUtils.DrawerTab private int tabId;
    private int imageResource;
    private int titleResource;
    private int counter;

    public BaseDrawerItem(@DrawerUtils.DrawerTab int tabId, int titleResource, int imageResource) {
        this.tabId = tabId;
        this.imageResource = imageResource;
        this.titleResource = titleResource;
        this.setCounter(0);
    }


    public BaseDrawerItem(@DrawerUtils.DrawerTab int tabId, int titleResource, int imageResource, int counter) {
        this.tabId = tabId;
        this.imageResource = imageResource;
        this.titleResource = titleResource;
        this.setCounter(counter);
    }

    @DrawerUtils.DrawerTab
    public int getTabId() {
        return tabId;
    }

    public int getImageResource() {
        return imageResource;
    }

    public int getTitleResource() {
        return titleResource;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }


}
