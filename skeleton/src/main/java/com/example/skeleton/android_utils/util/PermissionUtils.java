package com.example.skeleton.android_utils.util;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-15
 */

public class PermissionUtils {

    public static AlertDialog createReAskDialog(Context context, String title, String message, ClicksInterface clicksInterface) {
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    clicksInterface.onPositiveClick();
                }).setNegativeButton(android.R.string.cancel, (dialog, which) -> {
                    clicksInterface.onNegativeClick();
                })
                .create();
    }

    public static AlertDialog createNeverAskAgainDialog(Context context, String title, String message, PositiveClickInterface clickInterface) {
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    clickInterface.onPositiveClick();
                })
                .create();
    }

    public interface PositiveClickInterface {
        void onPositiveClick();
    }

    public interface ClicksInterface {
        void onPositiveClick();

        void onNegativeClick();
    }
}
