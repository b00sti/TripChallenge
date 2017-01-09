package com.example.b00sti.tripchallenge.ui_trip_details;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.b00sti.tripchallenge.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Dominik (b00sti) Pawlik on 2017-01-09
 */

@EViewGroup(R.layout.place_todo_itemview)
public class PlaceToDoItemView extends RelativeLayout {

    @ViewById(R.id.placeToDoIconIV) ImageView icon;
    @ViewById(R.id.placeToDoTitleTV) TextView textView;
    @ViewById(R.id.placeToDoMainRL) RelativeLayout drawerItemMainRL;

    Context context;

    public PlaceToDoItemView(Context context) {
        super(context);
        this.context = context;
    }

    public void bind(PlaceToDoItem placeToDoItem) {
        icon.setImageBitmap(placeToDoItem.getBitmap());
        textView.setText(placeToDoItem.getTitle());
    }
}
