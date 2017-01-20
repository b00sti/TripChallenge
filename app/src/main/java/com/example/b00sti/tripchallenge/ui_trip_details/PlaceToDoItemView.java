package com.example.b00sti.tripchallenge.ui_trip_details;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.b00sti.tripchallenge.R;
import com.example.b00sti.tripchallenge.model.RealmChallenge;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.DrawableRes;

import static com.example.b00sti.tripchallenge.R.drawable.ic_done_black_24dp;
import static com.example.b00sti.tripchallenge.R.drawable.ic_done_green_900_24dp;

/**
 * Created by Dominik (b00sti) Pawlik on 2017-01-09
 */

@EViewGroup(R.layout.place_todo_itemview)
public class PlaceToDoItemView extends RelativeLayout {

    @ViewById(R.id.placeToDoIconIV) ImageView icon;
    @ViewById(R.id.placeToDoTitleTV) TextView textView;
    @ViewById(R.id.placeToDoMainRL) RelativeLayout drawerItemMainRL;
    @ViewById(R.id.placeToDoDoneIV) ImageView placeToDoDoneIV;

    Context context;

    @DrawableRes(ic_done_black_24dp)
    Drawable iconDoneBlack;

    @DrawableRes(ic_done_green_900_24dp)
    Drawable iconDoneGreen;

    public PlaceToDoItemView(Context context) {
        super(context);
        this.context = context;
    }


    @UiThread
    void setImageBitmap(Bitmap bitmap) {
        icon.setImageBitmap(bitmap);
    }

    @Background
    void getBitmap(byte[] bitmapInBytes) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapInBytes, 0, bitmapInBytes.length);
        //Bitmap bitmap = ImageUtils.getInstant().getCompressedBitmap(bitmapInBytes);
        setImageBitmap(bitmap);
    }

    public void bind(RealmChallenge placeToDoItem) {
        byte[] bitmap = placeToDoItem.getBitmap();
        if (bitmap != null && bitmap.length > 0) {
            getBitmap(bitmap);
        } else {
            icon.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        }
        if (bitmap.length % 2 == 0) {
            placeToDoDoneIV.setImageDrawable(iconDoneBlack);
        } else {
            placeToDoDoneIV.setImageDrawable(iconDoneGreen);
        }

        textView.setText(placeToDoItem.getTitle());
    }
}

