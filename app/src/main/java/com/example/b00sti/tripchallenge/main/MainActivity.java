package com.example.b00sti.tripchallenge.main;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import com.example.b00sti.tripchallenge.R;
import com.example.b00sti.tripchallenge.ui_log_in.LogInFragment;
import com.example.skeleton.android_utils.eventbus.SwitchDrawerFragmentEvent;
import com.example.skeleton.android_utils.navigation.drawer.DrawerUtils;
import com.example.skeleton.ui.activity_utils.BaseMainActivity;
import com.example.skeleton.ui.activity_utils.EmptyFragment;

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
        onEvent(new SwitchDrawerFragmentEvent(DrawerUtils.TAB_00, LogInFragment.newInstance(), EmptyFragment.newInstance()));

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                0);
    }

    @AfterViews
    void runLogInActivity() {
        //ActivityUtils.startInnerViewActivity(this, FragmentBuilder.LOG_IN);
    }

    @Nullable
    @Override
    public Fragment setFragmentForTab(@DrawerUtils.DrawerTab int tab) {
        return DrawerHelper.getFragmentForTab(fragmentBuilder, tab);
    }

    @Nullable
    @Override
    public Fragment setTopFragmentForTab(@DrawerUtils.DrawerTab int tab) {
        return DrawerHelper.getTopFragmentForTab(fragmentBuilder, tab);
    }

    @Override
    public List<DrawerItem> setDrawerItems() {
        return DrawerHelper.getDrawerItems();
    }

    @Override
    public DrawerAdapter setDrawerAdapter() {
        return drawerAdapter;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
