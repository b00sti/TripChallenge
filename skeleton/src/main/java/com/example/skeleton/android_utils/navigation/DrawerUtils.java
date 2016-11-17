package com.example.skeleton.android_utils.navigation;

import android.support.annotation.IntDef;

import com.example.skeleton.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import static com.example.skeleton.android_utils.navigation.DrawerUtils.DRAWER_TAB_BASE.DASHBOARD_TAB;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-03
 */

public class DrawerUtils {
    public static final int TAB_0 = -1;
    public static final int TAB_1 = 1;
    public static final int TAB_2 = 2;
    public static final int TAB_3 = 3;
    public static final int TAB_4 = 4;
    public static final int TAB_5 = 5;
    public static final int TAB_6 = 6;
    public static final int TAB_7 = 7;
    public static final int TAB_8 = 8;
    public static final int TAB_9 = 9;
    public static final int TAB_10 = 10;


    public static List<DrawerItem> getDataSet() {
        List<DrawerItem> items = new ArrayList<>();

        items.add(new DrawerItem(DASHBOARD_TAB, R.string.login_error_title, R.drawable.common_full_open_on_phone));
        items.add(new DrawerItem(SETTINGS_TAB, R.string.signup_error_title, R.drawable.common_plus_signin_btn_text_dark_focused));

        return items;
    }

    public enum TABS {
        NO_TAB, DASHBOARD
    }


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TAB_0,
            TAB_1,
            TAB_2,
            TAB_3,
            TAB_4,
            TAB_5,
            TAB_6,
            TAB_7,
            TAB_8,
            TAB_9,
            TAB_10
    })
    public @interface DrawerTab {
    }

}
