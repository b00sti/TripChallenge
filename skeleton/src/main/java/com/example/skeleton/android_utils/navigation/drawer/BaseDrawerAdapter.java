package com.example.skeleton.android_utils.navigation.drawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.skeleton.ui.recyclers.BaseAdapter;

import java.util.List;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-03
 */

public abstract class BaseDrawerAdapter<T extends BaseDrawerItem, U extends RecyclerView.ViewHolder> extends BaseAdapter<T, U> {
    private static final String TAG = "BaseDrawerAdapter";

    protected int selectedTabId;

    public void setDrawerAdapterData(List<T> items, @DrawerUtils.DrawerTab int selectedTabId, Context context) {
        setDataSet(items);
        context = context;
        this.selectedTabId = selectedTabId;
    }
}
