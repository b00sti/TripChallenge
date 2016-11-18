package com.example.b00sti.tripchallenge.main;

import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;

import com.example.b00sti.tripchallenge.ui_dashboard.DashboardFragment;
import com.example.skeleton.ui.activity_utils.BaseFragmentBuilder;

import org.androidannotations.annotations.EBean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-18
 */

@EBean(scope = EBean.Scope.Singleton)
public class FragmentBuilder extends BaseFragmentBuilder {
    public static final int DASHBOARD = 0;
    public static final int SETTINGS = 1;

    public Fragment newFragment(@FragBuild int fragmentId) {

        switch (fragmentId) {
            case DASHBOARD:
                return DashboardFragment.newInstance();
            case SETTINGS:
                return DashboardFragment.newInstance();
            default:
                return DashboardFragment.newInstance();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DASHBOARD,
            SETTINGS
    })
    public @interface FragBuild {
    }
}
