package com.example.b00sti.tripchallenge.ui_trip_details;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.b00sti.tripchallenge.R;
import com.example.b00sti.tripchallenge.model.RealmChallenge;

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

    public void bind(RealmChallenge placeToDoItem) {
        byte[] bitmap = placeToDoItem.getBitmap();
        if (bitmap != null && bitmap.length > 0) {
            icon.setImageBitmap(BitmapFactory.decodeByteArray(bitmap, 0, bitmap.length));
        } else {
            icon.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        }
        textView.setText(placeToDoItem.getTitle());
    }
}
