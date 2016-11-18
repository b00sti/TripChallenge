package com.example.b00sti.tripchallenge.main;

import android.support.v4.app.Fragment;

import com.example.b00sti.tripchallenge.R;

import org.androidannotations.annotations.EFragment;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-18
 */

@EFragment(R.layout.fragment_bottom_drawer)
public class DrawerBottomFragment extends Fragment {

    public static Fragment newInstance() {
        return new DrawerBottomFragment_();
    }

}
