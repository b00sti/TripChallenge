package com.example.b00sti.tripchallenge.ui_trip_details;

import android.graphics.Bitmap;

/**
 * Created by Dominik (b00sti) Pawlik on 2017-01-09
 */
public class PlaceToDoItem {

    private Bitmap bitmap;
    private String title;
    private float counter;

    public PlaceToDoItem(Bitmap bitmap, String title, float counter) {
        this.bitmap = bitmap;
        this.title = title;
        this.counter = counter;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getCounter() {
        return counter;
    }

    public void setCounter(float counter) {
        this.counter = counter;
    }
}
