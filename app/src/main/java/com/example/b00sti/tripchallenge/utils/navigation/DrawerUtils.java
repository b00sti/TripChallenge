package com.example.b00sti.tripchallenge.utils.navigation;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.b00sti.tripchallenge.R;
import com.example.b00sti.tripchallenge.ui_settings.SettingsFragment;
import com.example.b00sti.tripchallenge.utils.eventbus.SwitchFragmentEvent;
import com.example.b00sti.tripchallenge.utils.ui.recyclers.RecyclerItemClickListener;

import org.greenrobot.eventbus.EventBus;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-03
 */

public class DrawerUtils {
    public static final int NO_TAB = -1;
    public static final int DASHBOARD_TAB = 0;
    public static final int SETTINGS_TAB = 1;

    public static List<DrawerItem> getDataSet() {
        List<DrawerItem> items = new ArrayList<>();

        items.add(new DrawerItem(DASHBOARD_TAB, R.string.login_error_title, R.drawable.common_full_open_on_phone));
        items.add(new DrawerItem(SETTINGS_TAB, R.string.signup_error_title, R.drawable.common_plus_signin_btn_text_dark_focused));

        return items;
    }

    public static DrawerAdapter initDrawerItemsAdapter(RecyclerView drawerRecyclerView, final DrawerLayout drawerLayout,
                                                       int selectedTabId, final Toolbar toolbar, final Context context) {

        final List<DrawerItem> drawerItems = getDataSet();

        drawerRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context, (view, position) -> {
            Fragment targetFragment;
            switch (drawerItems.get(position).getTabId()) {
                case DASHBOARD_TAB:
                    //targetFragment = new MvpFragment();
                    break;
                case SETTINGS_TAB:
                    targetFragment = new SettingsFragment();
                    break;
                default:
                    //targetFragment = new MvpFragment();
                    break;
            }
            targetFragment = new SettingsFragment();
            // post event to switch fragment
            EventBus.getDefault().post(new SwitchFragmentEvent(targetFragment, drawerItems.get(position).getTabId()));

            // set toolbar title to selected drawer item's title
            toolbar.setTitle(getDataSet().get(position).getTitleResource());

            drawerLayout.closeDrawers();
        }));
        // initialize adapter with selected position highlighted
        return new DrawerAdapter(drawerItems, selectedTabId, context);
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({NO_TAB,
            SETTINGS_TAB,
            DASHBOARD_TAB,
    })
    public @interface DrawerTab {
    }

}
