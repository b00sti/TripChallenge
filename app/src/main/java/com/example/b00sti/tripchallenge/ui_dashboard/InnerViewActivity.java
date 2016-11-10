package com.example.b00sti.tripchallenge.ui_dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.b00sti.tripchallenge.R;

public class InnerViewActivity extends AppCompatActivity {
    public final static int EDIT_PROFILE_FRAGMENT = 0;
    private static final String TAG = InnerViewActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initMap language
        setContentView(R.layout.activity_inner_view);

/*        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() == null)
                throw new AssertionError("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }*/

        // initMap first fragment displayed
        setInitialFragment(getIntent());
    }

/*
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return (true);
        }


        return super.onOptionsItemSelected(item);
    }

    private void setInitialFragment(Intent intent) {
        Log.d(TAG, "setInitialFragment: ");

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
            case InnerViewActivity.EDIT_PROFILE_FRAGMENT:
                Log.d(TAG, "InnerView - Edit Profile");
                fragment = DashboardFragment.newInstance();
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
