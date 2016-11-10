package com.example.b00sti.tripchallenge.mvp_base;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-10-28
 */
public interface BasePresenter<V> {

    void subscribe(V view);

    void unsubscribe();
}
