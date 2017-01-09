package com.example.b00sti.tripchallenge.ui_trip_details;

import android.content.Context;
import android.view.ViewGroup;

import com.example.skeleton.ui.recyclers.BaseAdapter;
import com.example.skeleton.ui.recyclers.ViewWrapper;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by Dominik (b00sti) Pawlik on 2017-01-09
 */
@EBean
public class PlaceTodoAdapter extends BaseAdapter<PlaceToDoItem, PlaceToDoItemView> {

    @RootContext Context context;

    @Override
    protected PlaceToDoItemView onCreateItemView(ViewGroup parent, int viewType) {
        return PlaceToDoItemView_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<PlaceToDoItemView> holder, int position) {
        PlaceToDoItemView itemView = holder.getView();
        PlaceToDoItem placeToDoItem = dataSet.get(position);
        itemView.bind(placeToDoItem);
    }
}
