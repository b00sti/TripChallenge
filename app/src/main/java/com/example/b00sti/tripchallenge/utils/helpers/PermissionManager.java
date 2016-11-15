package com.example.b00sti.tripchallenge.utils.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.example.b00sti.tripchallenge.R;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-15
 */

public class PermissionManager {
    // add permission and exactly for each permission their code
    public final static String[] startingPermissionsTitles = new String[]{};
    public final static Integer[] startingPermissionCodes = new Integer[]{};

    public static void requestStartingPermissions(Activity context) {
        int i = 0;
        for (String startingPermission : PermissionManager.startingPermissionsTitles) {
            if (ContextCompat.checkSelfPermission(context, startingPermission) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(context, PermissionManager.startingPermissionsTitles, startingPermissionCodes[i]);
                PermissionManager.setFlagForInitialPermissionsNeverAskAgain(context, false);
                break;
            }
            i++;
        }
    }

    public static boolean getFlagForInitialPermissionsNeverAskAgain(Context context) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_preferences_initial_permissions_never_ask_again_key), Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(context.getString(R.string.shared_preferences_initial_permissions_never_ask_again), false);
    }

    public static void setFlagForInitialPermissionsNeverAskAgain(Context context, boolean isNeverAskAgain) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_preferences_initial_permissions_never_ask_again_key), Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(context.getString(R.string.shared_preferences_initial_permissions_never_ask_again), isNeverAskAgain).apply();
    }

}
