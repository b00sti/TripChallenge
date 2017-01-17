package com.example.b00sti.tripchallenge.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Dominik (b00sti) Pawlik on 2017-01-12
 */

public class RealmTrip extends RealmObject {
    @PrimaryKey
    private int id;
    private String type;
    private boolean done;
    private byte[] bitmap;

    public RealmTrip() {
        id = RealmHelper.getChallengeNextKey();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public byte[] getBitmap() {
        return bitmap;
    }

    public void setBitmap(byte[] bitmap) {
        this.bitmap = bitmap;
    }
}