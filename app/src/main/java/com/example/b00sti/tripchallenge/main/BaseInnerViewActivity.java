package com.example.b00sti.tripchallenge.main;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.b00sti.tripchallenge.R;
import com.example.b00sti.tripchallenge.ui_log_in.LogInFragment;
import com.example.skeleton.android_utils.navigation.fragment_switching.FragmentSwitcher;
import com.example.skeleton.android_utils.navigation.fragment_switching.FragmentSwitcherParams;
import com.example.skeleton.android_utils.util.ViewUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_inner_view)
public class BaseInnerViewActivity extends AppCompatActivity {
    private static final String TAG = "BaseInnerViewActivity";

    @ViewById(R.id.toolbar) public Toolbar toolbar;

    @AfterViews
    public void init() {
        setInitialFragment(getIntent());
        setActivityTitle();
        setHomeButton(true);
    }

    @Override
    public void onBackPressed() {
        ViewUtils.hideKeyboard(this);
        super.onBackPressed();
    }

    private void setActivityTitle() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("name");
        setTitle(title);
    }

    public void setHomeButton(boolean isActive) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(isActive);
        }
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

    public void setInitialFragment(Intent intent) {
        int fragmentId;
        if (intent.hasExtra(getString(com.example.skeleton.R.string.bundle_fragment))) {
            fragmentId = intent.getIntExtra(getString(com.example.skeleton.R.string.bundle_fragment), -1);
            switchToFragment(fragmentId);
        }
    }

    public Fragment setFragment(int fragmentId) {
        return LogInFragment.newInstance();
    }

    private void switchToFragment(int fragmentId) {
        Fragment fragment = setFragment(fragmentId);
        Log.d(TAG, "switchToFragment: " + fragment.getTag());
        FragmentSwitcher.switchFragment(new FragmentSwitcherParams(getSupportFragmentManager(), fragment, com.example.skeleton.R.id.activity_inner_view_placeholder));
    }
}
