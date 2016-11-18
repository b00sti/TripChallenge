package com.example.b00sti.tripchallenge.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.example.b00sti.tripchallenge.R;
import com.example.skeleton.android_utils.navigation.drawer.DrawerUtils;
import com.example.skeleton.ui.activity_utils.BaseMainActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseMainActivity<DrawerItem, DrawerItemView, DrawerAdapter> {
    private static final String TAG = "MainActivity";

    @Bean
    FragmentBuilder fragmentBuilder;

    @Bean
    DrawerAdapter drawerAdapter;

    @AfterViews
    void setDrawerFragments() {
        setBottomDrawerFragment(DrawerBottomFragment.newInstance());
        setTopDrawerFragment(DrawerTopFragment.newInstance());
    }

    @Override
    public int getHomeAsUpIndicatorAsDrawable() {
        return R.drawable.common_plus_signin_btn_icon_dark_pressed;
    }

    @Nullable
    @Override
    public Fragment getFragmentForTab(@DrawerUtils.DrawerTab int tab) {
        return DrawerHelper.getFragmentForTab(fragmentBuilder, tab);
    }

    @Override
    public List<DrawerItem> getDrawerItems() {
        return DrawerHelper.getDrawerItems();
    }

    @Override
    public DrawerAdapter getDrawerAdapter() {
        return drawerAdapter;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
