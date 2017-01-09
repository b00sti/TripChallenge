package com.example.b00sti.tripchallenge.main;

import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;

import com.example.b00sti.tripchallenge.ui_dashboard.DashboardFragment;
import com.example.b00sti.tripchallenge.ui_log_in.LogInFragment;
import com.example.b00sti.tripchallenge.ui_sign_up.SignUpFragment;
import com.example.b00sti.tripchallenge.ui_trip_details.TripDetailsBottomFragment;
import com.example.b00sti.tripchallenge.ui_trip_details.TripDetailsTopFragment;
import com.example.skeleton.ui.activity_utils.BaseFragmentBuilder;
import com.example.skeleton.ui.activity_utils.EmptyFragment;

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
    public static final int LOG_IN = 2;
    public static final int SIGN_IN = 3;
    public static final int TRIPS_TOP = 4;
    public static final int TRIPS_BOTTOM = 5;
    public static final int EMPTY = 100;

    public Fragment newFragment(@FragBuild int fragmentId) {

        switch (fragmentId) {
            case DASHBOARD:
                return DashboardFragment.newInstance();
            case SETTINGS:
                return LogInFragment.newInstance();
            case LOG_IN:
                return LogInFragment.newInstance();
            case SIGN_IN:
                return SignUpFragment.newInstance();
            case EMPTY:
                return EmptyFragment.newInstance();
            case TRIPS_TOP:
                return TripDetailsTopFragment.newInstance();
            case TRIPS_BOTTOM:
                return TripDetailsBottomFragment.newInstance();
            default:
                return DashboardFragment.newInstance();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DASHBOARD,
            SETTINGS,
            LOG_IN,
            SIGN_IN,
            TRIPS_TOP,
            TRIPS_BOTTOM,
            EMPTY
    })
    public @interface FragBuild {
    }
}
