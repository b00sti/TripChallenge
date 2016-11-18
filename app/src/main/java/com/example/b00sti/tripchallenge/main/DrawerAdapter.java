package com.example.b00sti.tripchallenge.main;

import android.content.Context;
import android.view.ViewGroup;

import com.example.skeleton.android_utils.navigation.drawer.BaseDrawerAdapter;
import com.example.skeleton.ui.recyclers.ViewWrapper;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-18
 */

@EBean
public class DrawerAdapter extends BaseDrawerAdapter<DrawerItem, DrawerItemView> {

    @RootContext
    Context context;

    @Override
    protected DrawerItemView onCreateItemView(ViewGroup parent, int viewType) {
        return DrawerItemView_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<DrawerItemView> holder, int position) {
        DrawerItemView itemView = holder.getView();
        DrawerItem drawerItem = dataSet.get(position);
        itemView.bind(drawerItem);
    }

}
