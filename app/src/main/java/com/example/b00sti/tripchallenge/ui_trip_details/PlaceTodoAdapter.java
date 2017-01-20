package com.example.b00sti.tripchallenge.ui_trip_details;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.b00sti.tripchallenge.model.RealmChallenge;
import com.example.skeleton.ui.recyclers.BaseAdapter;
import com.example.skeleton.ui.recyclers.ViewWrapper;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.res.DrawableRes;

import static com.example.b00sti.tripchallenge.R.drawable.ic_done_black_24dp;
import static com.example.b00sti.tripchallenge.R.drawable.ic_done_green_900_24dp;

/**
 * Created by Dominik (b00sti) Pawlik on 2017-01-09
 */
@EBean
public class PlaceTodoAdapter extends BaseAdapter<RealmChallenge, PlaceToDoItemView> {

    @RootContext Context context;

    @DrawableRes(ic_done_black_24dp)
    Drawable iconDoneBlack;

    @DrawableRes(ic_done_green_900_24dp)
    Drawable iconDoneGreen;

    @Override
    protected PlaceToDoItemView onCreateItemView(ViewGroup parent, int viewType) {
        return PlaceToDoItemView_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<PlaceToDoItemView> holder, int position) {
        PlaceToDoItemView itemView = holder.getView();
        RealmChallenge placeToDoItem = dataSet.get(position);
        itemView.bind(placeToDoItem);

        ImageView done = holder.getView().placeToDoDoneIV;
        done.setOnClickListener(view -> {
            if (done.getDrawable().equals(iconDoneGreen)) {
                done.setImageDrawable(iconDoneBlack);
            } else {
                done.setImageDrawable(iconDoneGreen);
            }

        });
    }
}
