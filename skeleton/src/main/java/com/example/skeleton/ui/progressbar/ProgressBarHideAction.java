package com.example.skeleton.ui.progressbar;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-15
 */

public class ProgressBarHideAction extends ProgressBarAction {
    private boolean show = false;

    public ProgressBarHideAction(@NonNull Activity ctx, @Nullable View forExampleProgressBar, boolean show) {
        super(ctx, forExampleProgressBar, show);
    }
}
