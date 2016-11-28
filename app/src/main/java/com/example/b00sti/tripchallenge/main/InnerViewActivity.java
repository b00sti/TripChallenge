package com.example.b00sti.tripchallenge.main;

import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.example.b00sti.tripchallenge.R;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-21
 */

@EActivity(R.layout.activity_inner_view)
public class InnerViewActivity extends com.example.skeleton.ui.activity_utils.BaseInnerViewActivity {
    private static final String TAG = "InnerViewActivity";

    @Bean
    FragmentBuilder fragmentBuilder;

    @Override
    public Fragment setFragment(@FragmentBuilder.FragBuild int fragmentId) {
        return fragmentBuilder.newFragment(fragmentId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
