package com.example.b00sti.tripchallenge.main;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.b00sti.tripchallenge.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-18
 */

@EViewGroup(R.layout.drawer_item)
public class DrawerItemView extends RelativeLayout {

    @ViewById(R.id.drawer_item_icon)
    ImageView icon;

    @ViewById(R.id.drawer_item_title)
    TextView textView;

    Context context;

    public DrawerItemView(Context context) {
        super(context);
        this.context = context;
    }

    public void bind(DrawerItem drawerItem) {
        icon.setImageDrawable(context.getResources().getDrawable(drawerItem.getImageResource()));
        textView.setText(drawerItem.getTitleResource());
    }

}
