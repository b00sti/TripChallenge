package com.example.skeleton.ui.activity_utils;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.skeleton.R;
import com.example.skeleton.android_utils.util.CLog;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-15
 */

public class InnerViewActivity extends AppCompatActivity {
    private static final String TAG = "InnerViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_view);
        setInitialFragment(getIntent());
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
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

    private void setInitialFragment(Intent intent) {
        int fragmentId;
        if (intent.hasExtra(getString(R.string.bundle_fragment))) {
            fragmentId = intent.getIntExtra(getString(R.string.bundle_fragment), -1);
            CLog.d(TAG, "setInitialFragment with fragmentId", fragmentId);
//            switchToFragment(fragmentId);
        }
    }
/*

    public void switchToFragment(int fragmentId) {
        Fragment fragment = BaseFragmentBuilder.getInnerActivityFragment(fragmentId);
        executeTransaction(fragment);
    }

    private void executeTransaction(Fragment f) {
        FragmentSwitcher.switchFragment(new FragmentSwitcherParams(getSupportFragmentManager(), f, R.id.activity_inner_view_placeholder));
    }
*/

}
