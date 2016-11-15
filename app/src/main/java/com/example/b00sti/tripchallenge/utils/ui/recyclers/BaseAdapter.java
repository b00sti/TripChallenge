package com.example.b00sti.tripchallenge.utils.ui.recyclers;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-15
 */

public abstract class BaseAdapter<T, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {

    protected List<T> dataSet = new ArrayList<>();

    public BaseAdapter(List<T> dataSet) {
        this.dataSet.clear();
        this.dataSet.addAll(dataSet);
    }

    public void setDataSet(List<T> dataSet) {
        this.dataSet.clear();
        this.dataSet.addAll(dataSet);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (dataSet != null) {
            return dataSet.size();
        } else {
            return 0;
        }
    }
}
