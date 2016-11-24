package com.example.b00sti.tripchallenge.utils.helpers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-24
 */

public class CustomAlertDialog extends AlertDialog {

    protected CustomAlertDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void setCustomTitle(View customTitleView) {
        super.setCustomTitle(customTitleView);
    }
}
