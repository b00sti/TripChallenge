package com.example.skeleton.android_utils.util;

import com.example.skeleton.ui.mvp_base.MvpFragment;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-17
 */

public interface DrawerBaseInterface<P extends MvpFragment> {
    P getInstance();
}
