package com.example.b00sti.tripchallenge.utils.navigation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.b00sti.tripchallenge.R;

import java.util.List;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-03
 */

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {

    public static final String TAG = DrawerAdapter.class.getSimpleName();
    Context context;
    private List<DrawerItem> items;
    private int selectedTabId;
    private View notificationTabView;

    public DrawerAdapter(List<DrawerItem> items, @DrawerUtils.DrawerTab int selectedTabId, Context context) {
        this.items = items;
        this.selectedTabId = selectedTabId;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
/*        DrawerItem drawerItem = items.get(position);
        holder.icon.setImageResource(drawerItem.getImageResource());
        holder.itemTitle.setText(drawerItem.getTitleResource());

        holder.itemView.setSelected(selectedTabId == drawerItem.getTabId());

*//*        if (holder.itemView.isSelected()) {
                switchSelectedIcon(selectedTabId, holder);

        }*//*

        // set image counter if item has counter > 0
        if (items.get(position).getCounter() > 0) {
            holder.counter.setVisibility(View.VISIBLE);
            holder.counter.setText(String.valueOf(drawerItem.getCounter()));
        }*/

        // store notification item in list to update it when notif is received
/*        if (items.get(position).getTabId() == DrawerUtils.NOTIFICATIONS_TAB) {
            setNotificationTabView(holder.itemView);
        }*/
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getTabId();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        //EventBus.getDefault().register(this);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        //EventBus.getDefault().unregister(this);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setNotificationTabView(View notificationTabView) {
        this.notificationTabView = notificationTabView;
    }

    public void switchSelectedIcon(int selectedTabId, ViewHolder holder) {
/*        switch (selectedTabId) {
            case DrawerUtils.EVENTS_TAB:
                holder.icon.setImageResource(R.drawable.menu_icon_events);
                break;
            case DrawerUtils.DASHBOARD_TAB:
                holder.icon.setImageResource(R.drawable.menu_icon_dashboard);
                break;
            default:
                break;
        }
        holder.itemTitle.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));*/
    }

/*    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(NotificationReceivedEvent event) {
        if (notificationTabView != null && event.getNotificationCount() > 0) {
            View counterView = notificationTabView.findViewById(R.id.counter);
            if (counterView != null) {
                TextView counter = (TextView) counterView;
                counter.setVisibility(View.VISIBLE);
                counter.setText(String.valueOf(event.getNotificationCount()));
            }
        }
    }*/

    class ViewHolder extends RecyclerView.ViewHolder {
        //@ViewById(R.id.drawer_item_icon)
        ImageView icon;

        //@ViewById(R.id.drawer_item_title)
        TextView itemTitle;

        //@ViewById(R.id.counter)
        TextView counter;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
