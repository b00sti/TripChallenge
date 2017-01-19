package com.example.skeleton.ui.activity_utils;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
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
    @ViewById(resName = "toolbarMain") public Toolbar toolbarMain;
    @DrawerUtils.DrawerTab public int drawerCurrentlySelectedTab = DrawerUtils.TAB_NO;
    @ViewById(resName = "collapsedTitleL") public CollapsingToolbarLayout collapsedTitleL;
    @ViewById(resName = "appBarL") public AppBarLayout appBarLayout;
    private A drawerAdapter;

    public abstract Fragment setFragmentForTab(@DrawerUtils.DrawerTab int tab);

    public abstract Fragment setTopFragmentForTab(@DrawerUtils.DrawerTab int tab);

    public abstract List<I> setDrawerItems();

    public abstract A setDrawerAdapter();

    public void setCollapsedTitleL(String titleL) {
        collapsedTitleL.setTitle(titleL);
    }

    @AfterViews
    public void init() {
        EventBus.getDefault().register(this);

        prepareDrawerMenuRecyclerView();
        initDrawer(toolbar);
    }

    private void initDrawer(Toolbar toolbar) {
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //toggle.setDrawerIndicatorEnabled(false);
        //toggle.setHomeAsUpIndicator(ContextCompat.getDrawable(this, setHomeAsUpIndicatorAsDrawable()));
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

        Fragment targetFragment = event.getTargetFragment();
        Fragment topTargetFragment = event.getTopTargetFragment();

        //drawerAdapter = setDrawerAdapter();


        if (topTargetFragment instanceof EmptyFragment) {
            toolbarMain.setVisibility(View.VISIBLE);
            appBarLayout.setVisibility(View.GONE);
            initDrawer(toolbarMain);
        } else {
            toolbarMain.setVisibility(View.GONE);
            appBarLayout.setVisibility(View.VISIBLE);
            initDrawer(toolbar);
        }
        FragmentSwitcher.switchFragment(new FragmentSwitcherParams(getSupportFragmentManager(), topTargetFragment, R.id.activity_top_placeholder));
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
            drawerCurrentlySelectedTab = DrawerUtils.getDrawerTab(position);
            drawer.closeDrawers();
        }));

        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                drawerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onDrawerClosed(View drawerView) {

                // set toolbar title to selected drawer item's title
                toolbar.setTitle(setDrawerItems().get(drawerCurrentlySelectedTab).getTitleResource());
                toolbarMain.setTitle(setDrawerItems().get(drawerCurrentlySelectedTab).getTitleResource());

                Fragment targetFragment = setFragmentForTab(drawerCurrentlySelectedTab);
                Fragment topTargetFragment = setTopFragmentForTab(drawerCurrentlySelectedTab);

                // post event to switch fragment
                EventBus.getDefault().post(new SwitchDrawerFragmentEvent(drawerCurrentlySelectedTab, targetFragment, topTargetFragment));

                drawerAdapter.setSelectedTabId(drawerCurrentlySelectedTab);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        // initialize adapter with selected position highlighted
        drawerAdapter = setDrawerAdapter();
        drawerAdapter.setDrawerAdapterData(setDrawerItems(), drawerCurrentlySelectedTab);
        return drawerAdapter;
    }

}
