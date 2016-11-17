package com.example.skeleton.android_utils.util;

import android.content.Context;
import android.os.Bundle;
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
import android.view.MenuItem;

import com.example.skeleton.R;
import com.example.skeleton.android_utils.eventbus.SwitchFragmentEvent;
import com.example.skeleton.android_utils.navigation.DrawerAdapter;
import com.example.skeleton.android_utils.navigation.DrawerItem;
import com.example.skeleton.android_utils.navigation.DrawerUtils;
import com.example.skeleton.android_utils.navigation.FragmentSwitcher;
import com.example.skeleton.android_utils.navigation.FragmentSwitcherParams;
import com.example.skeleton.ui.activity_utils.ActivityUtils;
import com.example.skeleton.ui.activity_utils.FragmentBuilder;
import com.example.skeleton.ui.recyclers.RecyclerItemClickListener;
import com.example.skeleton.ui.recyclers.RecyclerViewUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import static com.example.skeleton.android_utils.navigation.DrawerUtils.TABS.NO_TAB;
import static com.example.skeleton.android_utils.navigation.DrawerUtils.getDataSet;

@EActivity(resName = "activity_main")
public abstract class BaseMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    @ViewById(resName = "drawer") public DrawerLayout drawer;
    @ViewById(resName = "drawer_recycler_view") public RecyclerView drawerRecyclerView;
    public ActionBarDrawerToggle toggle;
    @ViewById(resName = "toolbar") Toolbar toolbar;
    private int drawerCurrentlySelectedTab = NO_TAB;

    public abstract int getHomeAsUpIndicatorAsDrawable();

    public abstract Fragment getFragmentsAsInDrawer(DrawerUtils.TABS tabs);

    @AfterViews
    void initBasis() {
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
                DrawerUtils.initDrawerItemsAdapter(drawerRecyclerView, drawer, drawerCurrentlySelectedTab, toolbar, this));

    }

    public int getDefaultTabId() {
        return T.DASHBOARD_TAB;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        switch (this.drawerCurrentlySelectedTab) {
            case NO_TAB:
                drawerRecyclerView.setAdapter(new DrawerAdapter(getDataSet(), NO_TAB, this));
                break;
            //TABS
            case DrawerUtils.DASHBOARD_TAB:
            case DrawerUtils.SETTINGS_TAB:
            default:
                drawerRecyclerView.setAdapter(new DrawerAdapter(getDataSet(), this.drawerCurrentlySelectedTab, this));
        }
        FragmentSwitcher.switchFragment(new FragmentSwitcherParams(getSupportFragmentManager(), targetFragment, R.id.activity_main_placeholder));
    }

    public DrawerAdapter initDrawerItemsAdapter(RecyclerView drawerRecyclerView, final DrawerLayout drawerLayout,
                                                int selectedTabId, final Toolbar toolbar, final Context context) {

        final List<DrawerItem> drawerItems = getDataSet();

        drawerRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context, (view, position) -> {
            Fragment targetFragment = getFragmentsAsInDrawer();

            // post event to switch fragment
            EventBus.getDefault().post(new SwitchFragmentEvent(targetFragment, drawerItems.get(position).getTabId()));

            // set toolbar title to selected drawer item's title
            toolbar.setTitle(getDataSet().get(position).getTitleResource());

            drawerLayout.closeDrawers();
        }));
        // initialize adapter with selected position highlighted
        return new DrawerAdapter(drawerItems, selectedTabId, context);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_logout) {
            firebaseManager.getFirebaseAuth().signOut();
            ActivityUtils.startInnerViewActivity(this, FragmentBuilder.DASHBOARD_FRAGMENT);
            //test
            //loadLogInView();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
