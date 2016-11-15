package com.example.b00sti.tripchallenge.utils.navigation;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-03
 */

public class DrawerItem {

    @DrawerUtils.DrawerTab
    private int tabId;

    private int imageResource;
    private int titleResource;
    private int counter;

    public DrawerItem(@DrawerUtils.DrawerTab int tabId, int titleResource, int imageResource) {
        this.tabId = tabId;
        this.imageResource = imageResource;
        this.titleResource = titleResource;
        this.setCounter(0);
    }


    public DrawerItem(@DrawerUtils.DrawerTab int tabId, int titleResource, int imageResource, int counter) {
        this.tabId = tabId;
        this.imageResource = imageResource;
        this.titleResource = titleResource;
        this.setCounter(counter);
    }

    public
    @DrawerUtils.DrawerTab
    int getTabId() {
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
