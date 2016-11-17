package com.example.skeleton.android_utils.example;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.skeleton.data.Attraction;
import com.example.skeleton.ui.recyclers.BaseAdapter;

import java.util.List;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-15
 */

public class ExampleAdapter extends BaseAdapter<Attraction, ExampleAdapter.ExampleViewHolder> {

    public ExampleAdapter(List<Attraction> dataSet) {
        super(dataSet);
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {

    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public ExampleViewHolder(View itemView) {
            super(itemView);
        }
    }
}
