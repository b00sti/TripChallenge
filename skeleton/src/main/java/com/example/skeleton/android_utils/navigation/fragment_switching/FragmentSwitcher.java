package com.example.skeleton.android_utils.navigation.fragment_switching;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.example.skeleton.android_utils.util.CLog;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-10-28
 */

public class FragmentSwitcher {
    private static final String TAG = "FragmentSwitcher";

    public static void switchFragment(@NonNull FragmentSwitcherParams params, Context context) {
        checkNotNull(params);

        FragmentTransaction transaction = params.getFragmentManager().beginTransaction();

        Fragment fragment = Fragment.instantiate(context, params.getTag());

        fragment.setArguments(params.getBundle());
        if (params.getSharedElements() != null) {
            for (SharedElement sharedElement : params.getSharedElements()) {
                transaction.addSharedElement(sharedElement.view, sharedElement.name);
            }
        }

        if (params.getCustomEnterAnim() != 0 && params.getCustomExitAnim() != 0) {
            transaction.setCustomAnimations(params.getCustomEnterAnim(), params.getCustomExitAnim());
        }

        transaction.replace(params.getFrameId(), fragment, params.getTag());
        if (params.isCommitAllowingStateLoss()) {
            transaction.commitAllowingStateLoss();
        }

        transaction.commit();

        CLog.d(TAG, "switchFragment to", params.getTag());
    }

    public static void switchFragment(@NonNull FragmentSwitcherParams params) {
        checkNotNull(params);

        FragmentTransaction transaction = params.getFragmentManager().beginTransaction();

        Fragment fragment = params.getFragment();

        fragment.setArguments(params.getBundle());
        if (params.getSharedElements() != null) {
            for (SharedElement sharedElement : params.getSharedElements()) {
                transaction.addSharedElement(sharedElement.view, sharedElement.name);
            }
        }

        if (params.getCustomEnterAnim() != 0 && params.getCustomExitAnim() != 0) {
            transaction.setCustomAnimations(params.getCustomEnterAnim(), params.getCustomExitAnim());
        }

        transaction.replace(params.getFrameId(), fragment, params.getTag());
        if (params.isCommitAllowingStateLoss()) {
            transaction.commitAllowingStateLoss();
        }

        transaction.commit();

        CLog.d(TAG, "switchFragment to", params.getTag());
    }
}
