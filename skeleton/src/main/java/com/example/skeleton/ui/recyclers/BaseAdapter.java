package com.example.skeleton.ui.recyclers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-15
 */

public abstract class BaseAdapter<T, V extends View> extends RecyclerView.Adapter<ViewWrapper<V>> {

    protected List<T> dataSet = new ArrayList<>();
    protected Context context;

    public BaseAdapter() {

    }

    public BaseAdapter(List<T> dataSet) {
        this.dataSet.clear();
        this.dataSet.addAll(dataSet);
    }

    public BaseAdapter(List<T> dataSet, Context context) {
        this.dataSet.clear();
        this.dataSet.addAll(dataSet);
        this.context = context;
    }

    public void setDataSet(List<T> dataSet) {
        this.dataSet.clear();
        this.dataSet.addAll(dataSet);
        notifyDataSetChanged();
    }

    @Override
    public final ViewWrapper<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewWrapper<V>(onCreateItemView(parent, viewType));
    }

    protected abstract V onCreateItemView(ViewGroup parent, int viewType);

    @Override
    public int getItemCount() {
        if (dataSet != null) {
            return dataSet.size();
        } else {
            return 0;
        }
    }
}
