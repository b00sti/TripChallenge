package com.example.skeleton.ui.activity_utils;


import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.skeleton.R;
import com.example.skeleton.android_utils.navigation.fragment_switching.FragmentSwitcher;
import com.example.skeleton.android_utils.navigation.fragment_switching.FragmentSwitcherParams;
import com.example.skeleton.android_utils.util.ViewUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-15
 */

@EActivity
public abstract class BaseInnerViewActivity extends AppCompatActivity {
    private static final String TAG = "BaseInnerViewActivity";

    @AfterViews
    public void setFragment() {
        setInitialFragment(getIntent());
        setTitle(getIntent());
        setActionBar();
    }

    @Override
    public void onBackPressed() {
        ViewUtils.hideKeyboard(this);
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return (true);
        }

        return super.onOptionsItemSelected(item);
    }

    private void setTitle(Intent intent) {
        if (intent.hasExtra("name")) {
            String s = intent.getStringExtra("name");
            setTitle(s);
        }
    }

    private void setInitialFragment(Intent intent) {
        int fragmentId;
        if (intent.hasExtra(getString(R.string.bundle_fragment))) {
            fragmentId = intent.getIntExtra(getString(R.string.bundle_fragment), -1);
            switchToFragment(fragmentId);
        }
    }

    public abstract Fragment setFragment(int fragmentId);

    private void switchToFragment(int fragmentId) {
        Fragment fragment = setFragment(fragmentId);
        FragmentSwitcher.switchFragment(new FragmentSwitcherParams(getSupportFragmentManager(), fragment, R.id.activity_inner_view_placeholder), this);
    }

    private void setActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
