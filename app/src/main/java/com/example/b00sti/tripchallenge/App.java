package com.example.b00sti.tripchallenge;

import android.app.Application;

import com.example.b00sti.tripchallenge.utils.util.LastRealmMigration;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-15
 */

public class App extends Application {

    private static final String TAG = "App";
    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initRealmConfiguration();

    }

    private void initRealmConfiguration() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(getResources().getString(R.string.app_name) + ".realm")
                .schemaVersion(1)
//                .deleteRealmIfMigrationNeeded()
                .migration(new LastRealmMigration())
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
