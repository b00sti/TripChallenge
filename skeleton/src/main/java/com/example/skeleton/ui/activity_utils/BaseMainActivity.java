package com.example.skeleton.ui.activity_utils;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.skeleton.R;
import com.example.skeleton.android_utils.eventbus.SwitchFragmentEvent;
import com.example.skeleton.android_utils.navigation.drawer.BaseDrawerAdapter;
import com.example.skeleton.android_utils.navigation.drawer.BaseDrawerItem;
import com.example.skeleton.android_utils.navigation.drawer.DrawerUtils;
import com.example.skeleton.android_utils.navigation.fragment_switching.FragmentSwitcher;
import com.example.skeleton.android_utils.navigation.fragment_switching.FragmentSwitcherParams;
import com.example.skeleton.ui.recyclers.RecyclerItemClickListener;
import com.example.skeleton.ui.recyclers.RecyclerViewUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

@EActivity(resName = "activity_main")
public abstract class BaseMainActivity<P extends BaseDrawerAdapter<U, RecyclerView.ViewHolder>, U extends BaseDrawerItem> extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    @ViewById(resName = "drawer") public DrawerLayout drawer;
    @ViewById(resName = "drawer_recycler_view") public RecyclerView drawerRecyclerView;
    public ActionBarDrawerToggle toggle;
    @ViewById(resName = "toolbar") Toolbar toolbar;
    @DrawerUtils.DrawerTab private int drawerCurrentlySelectedTab = DrawerUtils.TAB_NO;

    public abstract int getHomeAsUpIndicatorAsDrawable();

    public abstract Fragment getFragmentForTab(@DrawerUtils.DrawerTab int tab);

    public abstract List<U> getDrawerItems();

    public abstract P getDrawerAdapter();

    @AfterViews
    void init() {
        setSupportActionBar(toolbar);

        EventBus.getDefault().register(this);

        initDrawer();
    }

    private void initDrawer() {
        prepareDrawerMenuRecyclerView();

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(ContextCompat.getDrawable(this, getHomeAsUpIndicatorAsDrawable()));
        toggle.setToolbarNavigationClickListener(view -> {
            if (drawer.isDrawerVisible(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void prepareDrawerMenuRecyclerView() {

        if (drawerCurrentlySelectedTab < 0) {
            drawerCurrentlySelectedTab = getDefaultTabId();
        }

        RecyclerViewUtil.initDefaultRecycler(this,
                drawerRecyclerView,
                initDrawerItemsAdapter());

    }

    @DrawerUtils.DrawerTab
    public int getDefaultTabId() {
        return DrawerUtils.TAB_0;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Subscribe
    public void onEvent(SwitchFragmentEvent event) {

        this.drawerCurrentlySelectedTab = event.getDrawerItemSelected();
        Fragment targetFragment = event.getTargetFragment();
        @DrawerUtils.DrawerTab int tabToSelect;

        switch (this.drawerCurrentlySelectedTab) {
            case DrawerUtils.TAB_0:
                tabToSelect = DrawerUtils.TAB_0;
                break;
            case DrawerUtils.TAB_10:
                tabToSelect = DrawerUtils.TAB_10;
                break;
            case DrawerUtils.TAB_1:
                tabToSelect = DrawerUtils.TAB_1;
                break;
            case DrawerUtils.TAB_2:
                tabToSelect = DrawerUtils.TAB_2;
                break;
            case DrawerUtils.TAB_3:
                tabToSelect = DrawerUtils.TAB_3;
                break;
            case DrawerUtils.TAB_4:
                tabToSelect = DrawerUtils.TAB_4;
                break;
            case DrawerUtils.TAB_5:
                tabToSelect = DrawerUtils.TAB_5;
                break;
            case DrawerUtils.TAB_6:
                tabToSelect = DrawerUtils.TAB_6;
                break;
            case DrawerUtils.TAB_7:
                tabToSelect = DrawerUtils.TAB_7;
                break;
            case DrawerUtils.TAB_8:
                tabToSelect = DrawerUtils.TAB_8;
                break;
            case DrawerUtils.TAB_9:
                tabToSelect = DrawerUtils.TAB_9;
                break;
            case DrawerUtils.TAB_NO:
                tabToSelect = DrawerUtils.TAB_NO;
                break;
            default:
                tabToSelect = this.drawerCurrentlySelectedTab;
        }

        P drawerAdapter = getDrawerAdapter();
        drawerAdapter.setDrawerAdapterData(getDrawerItems(), tabToSelect, this);
        drawerRecyclerView.setAdapter(drawerAdapter);

        FragmentSwitcher.switchFragment(new FragmentSwitcherParams(getSupportFragmentManager(), targetFragment, R.id.activity_main_placeholder));
    }

    public P initDrawerItemsAdapter() {
        final List<U> drawerItems = getDrawerItems();

        drawerRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, (view, position) -> {
            Fragment targetFragment = getFragmentForTab(position);

            // post event to switch fragment
            EventBus.getDefault().post(new SwitchFragmentEvent(targetFragment, drawerItems.get(position).getTabId()));

            // set toolbar title to selected drawer item's title
            toolbar.setTitle(getDrawerItems().get(position).getTitleResource());

            drawer.closeDrawers();
        }));
        // initialize adapter with selected position highlighted
        P drawerAdapter = getDrawerAdapter();
        drawerAdapter.setDrawerAdapterData(getDrawerItems(), drawerCurrentlySelectedTab, this);
        return drawerAdapter;
    }

}
