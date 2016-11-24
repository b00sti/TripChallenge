package com.example.b00sti.tripchallenge;

import com.example.b00sti.tripchallenge.utils.config.LastRealmMigration;
import com.example.skeleton.android_utils.util.BaseApp;

import io.realm.RealmMigration;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-15
 */

public class App extends BaseApp {
    private static final String TAG = "App";

    @Override
    public RealmMigration setRealmMigration() {
        return new LastRealmMigration();
    }

    @Override
    protected int setRealmSchemaVersion() {
        return 1;
    }

}
