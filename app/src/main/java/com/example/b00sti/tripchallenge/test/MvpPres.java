package com.example.b00sti.tripchallenge.test;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-14
 */

public abstract class MvpPres<P> {

    public P view;

    public void setView(P view) {
        this.view = view;
    }

}
