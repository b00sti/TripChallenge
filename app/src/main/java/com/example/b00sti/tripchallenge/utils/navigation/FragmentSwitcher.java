package com.example.b00sti.tripchallenge.utils.navigation;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-10-28
 */

public class FragmentSwitcher {
    private static final String TAG = "FragmentSwitcher";

    public static void switchFragment(@NonNull FragmentSwitcherParams params) {
        FragmentTransaction transaction = params.getFragmentManager().beginTransaction();

        params.getFragment().setArguments(params.getBundle());
        if (params.getSharedElements() != null) {
            for (SharedElement sharedElement : params.getSharedElements()) {
                transaction.addSharedElement(sharedElement.view, sharedElement.name);
            }
        }
        transaction.setCustomAnimations(params.getCustomEnterAnim(), params.getCustomExitAnim());
        transaction.replace(params.getFrameId(), params.getFragment(), params.getTag());
        if (params.isCommitAllowingStateLoss()) {
            transaction.commitAllowingStateLoss();
        }

        transaction.commit();
    }
}