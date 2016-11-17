package com.example.skeleton.android_utils.util;

import android.util.Log;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-15
 */

public class LastRealmMigration implements RealmMigration {
    private static final String TAG = "LastRealmMigration";

    @Override
    public void migrate(DynamicRealm dynamicRealm, long oldVersion, long newVersion) {
        RealmSchema schema = dynamicRealm.getSchema();
        Log.d(TAG, "migrate: oldVersion: " + oldVersion);
        if (oldVersion == 0) {
            oldVersion++;
        }
    }
}
