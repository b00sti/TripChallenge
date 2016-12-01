package com.example.skeleton.ui.progressbar;

import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by b00sti on 29.11.2016.
 */

public class ProgressBarUtils {

    public static void show(ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
    }

    public static void hide(ProgressBar progressBar) {
        progressBar.setVisibility(View.GONE);
    }
}
