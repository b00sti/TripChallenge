package com.example.skeleton.ui.activity_utils;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.example.skeleton.R;
import com.example.skeleton.android_utils.eventbus.SwitchDrawerFragmentEvent;
import com.example.skeleton.android_utils.navigation.drawer.BaseDrawerAdapter;
import com.example.skeleton.android_utils.navigation.drawer.BaseDrawerItem;
import com.example.skeleton.android_utils.navigation.drawer.DrawerUtils;
import com.example.skeleton.android_utils.navigation.fragment_switching.FragmentSwitcher;
import com.example.skeleton.android_utils.navigation.fragment_switching.FragmentSwitcherParams;
import com.example.skeleton.android_utils.util.ViewUtils;
import com.example.skeleton.ui.recyclers.RecyclerItemClickListener;
import com.example.skeleton.ui.recyclers.RecyclerViewUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

@EActivity(resName = "activity_main")
public abstract class BaseMainActivity<I extends BaseDrawerItem, H extends View, A extends BaseDrawerAdapter<I, H>> extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @ViewById(resName = "drawer") public DrawerLayout drawer;
    @ViewById(resName = "drawer_recycler_view") public RecyclerView drawerRecyclerView;
    public ActionBarDrawerToggle toggle;
    @ViewById(resName = "toolbar") public Toolbar toolbar;
    @DrawerUtils.DrawerTab private int drawerCurrentlySelectedTab = DrawerUtils.TAB_NO;

    public abstract int setHomeAsUpIndicatorAsDrawable();

    public abstract Fragment setFragmentForTab(@DrawerUtils.DrawerTab int tab);

    public abstract List<I> setDrawerItems();

    public abstract A setDrawerAdapter();

    @AfterViews
    public void init() {
        setSupportActionBar(toolbar);

        EventBus.getDefault().register(this);

        initDrawer();
    }

    private void initDrawer() {
        prepareDrawerMenuRecyclerView();

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(ContextCompat.getDrawable(this, setHomeAsUpIndicatorAsDrawable()));
        toggle.setToolbarNavigationClickListener(view -> {
            if (drawer.isDrawerVisible(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
                ViewUtils.hideKeyboard(this);
            } else {
                drawer.openDrawer(GravityCompat.START);
                ViewUtils.hideKeyboard(this);
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
        return DrawerUtils.TAB_00;
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
    public void onEvent(SwitchDrawerFragmentEvent event) {

        this.drawerCurrentlySelectedTab = event.getDrawerItemSelected();
        Fragment targetFragment = event.getTargetFragment();
        @DrawerUtils.DrawerTab int tabToSelect;

        switch (this.drawerCurrentlySelectedTab) {
            case DrawerUtils.TAB_00:
                tabToSelect = DrawerUtils.TAB_00;
                break;
            case DrawerUtils.TAB_01:
                tabToSelect = DrawerUtils.TAB_01;
                break;
            case DrawerUtils.TAB_02:
                tabToSelect = DrawerUtils.TAB_02;
                break;
            case DrawerUtils.TAB_03:
                tabToSelect = DrawerUtils.TAB_03;
                break;
            case DrawerUtils.TAB_04:
                tabToSelect = DrawerUtils.TAB_04;
                break;
            case DrawerUtils.TAB_05:
                tabToSelect = DrawerUtils.TAB_05;
                break;
            case DrawerUtils.TAB_06:
                tabToSelect = DrawerUtils.TAB_06;
                break;
            case DrawerUtils.TAB_07:
                tabToSelect = DrawerUtils.TAB_07;
                break;
            case DrawerUtils.TAB_08:
                tabToSelect = DrawerUtils.TAB_08;
                break;
            case DrawerUtils.TAB_09:
                tabToSelect = DrawerUtils.TAB_09;
                break;
            case DrawerUtils.TAB_10:
                tabToSelect = DrawerUtils.TAB_10;
                break;
            case DrawerUtils.TAB_11:
                tabToSelect = DrawerUtils.TAB_11;
                break;
            case DrawerUtils.TAB_12:
                tabToSelect = DrawerUtils.TAB_12;
                break;
            case DrawerUtils.TAB_13:
                tabToSelect = DrawerUtils.TAB_13;
                break;
            case DrawerUtils.TAB_14:
                tabToSelect = DrawerUtils.TAB_14;
                break;
            case DrawerUtils.TAB_15:
                tabToSelect = DrawerUtils.TAB_15;
                break;
            case DrawerUtils.TAB_NO:
                tabToSelect = DrawerUtils.TAB_NO;
                break;
            default:
                tabToSelect = this.drawerCurrentlySelectedTab;
        }

        A drawerAdapter = setDrawerAdapter();
        drawerAdapter.setDrawerAdapterData(setDrawerItems(), tabToSelect);
        drawerRecyclerView.setAdapter(drawerAdapter);

        FragmentSwitcher.switchFragment(new FragmentSwitcherParams(getSupportFragmentManager(), targetFragment, R.id.activity_main_placeholder));
    }

    public void setTopDrawerFragment(Fragment targetFragment) {
        FragmentSwitcher.switchFragment(new FragmentSwitcherParams(getSupportFragmentManager(), targetFragment, R.id.drawer_top_placeholder));
    }

    public void setBottomDrawerFragment(Fragment targetFragment) {
        FragmentSwitcher.switchFragment(new FragmentSwitcherParams(getSupportFragmentManager(), targetFragment, R.id.drawer_bottom_placeholder));
    }

    private A initDrawerItemsAdapter() {
        final List<I> drawerItems = setDrawerItems();

        drawerRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, (view, position) -> {
            Fragment targetFragment = setFragmentForTab(position);

            // post event to switch fragment
            EventBus.getDefault().post(new SwitchDrawerFragmentEvent(targetFragment, drawerItems.get(position).getTabId()));

            // set toolbar title to selected drawer item's title
            toolbar.setTitle(setDrawerItems().get(position).getTitleResource());

            drawer.closeDrawers();
        }));
        // initialize adapter with selected position highlighted
        A drawerAdapter = setDrawerAdapter();
        drawerAdapter.setDrawerAdapterData(setDrawerItems(), drawerCurrentlySelectedTab);
        return drawerAdapter;
    }
}
