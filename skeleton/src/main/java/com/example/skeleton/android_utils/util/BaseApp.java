package com.example.skeleton.android_utils.util;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-15
 */

public abstract class BaseApp<P extends RealmMigration> extends Application {
    private static final String TAG = "BaseApp";
    private static BaseApp instance;

    public static BaseApp getInstance() {
        return instance;
    }

    protected abstract RealmConfig setRealmConfigParams();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initRealmConfiguration();
    }

    private void initRealmConfiguration() {
        int schemaVersion = setRealmConfigParams().getSchemaVersion();
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(setRealmConfigParams().getDataBaseName() + ".realm")
                .schemaVersion(schemaVersion)
//                .deleteRealmIfMigrationNeeded()
                .migration(setRealmConfigParams().getRealmMigration())
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
