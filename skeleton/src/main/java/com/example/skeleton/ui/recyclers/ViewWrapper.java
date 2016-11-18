package com.example.skeleton.ui.recyclers;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-18
 */

public class ViewWrapper<V extends View> extends RecyclerView.ViewHolder {
    private V view;

    public ViewWrapper(V itemView) {
        super(itemView);
        view = itemView;
    }

    public V getView() {
        return view;
    }
}
