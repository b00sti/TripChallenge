package com.example.skeleton.ui.mvp_base;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-14
 */

public abstract class MvpPresenter<P> {

    public P view;

    public void setView(P view) {
        this.view = view;
    }

}
