package com.example.b00sti.tripchallenge.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.b00sti.tripchallenge.R;
import com.example.b00sti.tripchallenge.utils.eventbus.EventBusManager;

public class ExampleInnerViewActivity extends AppCompatActivity {
    public final static int EDIT_PROFILE_FRAGMENT = 0;
    private static final String TAG = "ExampleInnerViewActivit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inner_view);

        setInitialFragment(getIntent());
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBusManager.register(this);
    }

    @Override
    protected void onStop() {
        EventBusManager.unregister(this);
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
            Log.d(TAG, "intent fragment: " + fragmentId);
            switchToFragment(fragmentId);
        }
    }

    public void switchToFragment(int fragmentId) {
        Fragment fragment;
        switch (fragmentId) {
            case ExampleInnerViewActivity.EDIT_PROFILE_FRAGMENT:
                Log.d(TAG, "InnerView - Edit Profile");
                fragment = ExampleFragment.newInstance();
                setFragmentChange(fragment).commit();
                break;
        }
    }

    private FragmentTransaction setFragmentChange(Fragment f) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_inner_view_placeholder, f);
        return transaction;
    }

}
