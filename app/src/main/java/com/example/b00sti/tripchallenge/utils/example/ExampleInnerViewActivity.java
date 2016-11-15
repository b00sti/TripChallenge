package com.example.b00sti.tripchallenge.utils.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.b00sti.tripchallenge.R;
import com.example.b00sti.tripchallenge.utils.navigation.FragmentSwitcher;
import com.example.b00sti.tripchallenge.utils.navigation.FragmentSwitcherParams;
import com.example.b00sti.tripchallenge.utils.ui.activity_utils.FragmentBuilder;
import com.example.b00sti.tripchallenge.utils.util.CLog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_inner_view)
public class ExampleInnerViewActivity extends AppCompatActivity {
    private static final String TAG = "ExampleInnerViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void setInitialFragment() {
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
            CLog.d(TAG, "set fragment with fragmentId", fragmentId);
            switchToFragment(fragmentId);
        }
    }

    public void switchToFragment(int fragmentId) {
        Fragment fragment = FragmentBuilder.getInnerActivityFragment(fragmentId);
        executeTransaction(fragment);
    }

    private void executeTransaction(Fragment f) {
        FragmentSwitcher.switchFragment(new FragmentSwitcherParams(getSupportFragmentManager(), f, R.id.activity_inner_view_placeholder));
    }

}
