package com.example.skeleton.android_utils.util;

import android.support.annotation.NonNull;

import io.realm.RealmMigration;

public class RealmConfig<P extends RealmMigration> {
    private String dataBaseName;
    private P realmMigration;
    private int schemaVersion;

    public RealmConfig(@NonNull String dataBaseName, @NonNull P realmMigration, int schemaVersion) {
        this.dataBaseName = dataBaseName;
        this.realmMigration = realmMigration;
        this.schemaVersion = schemaVersion;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public P getRealmMigration() {
        return realmMigration;
    }

    public void setRealmMigration(P realmMigration) {
        this.realmMigration = realmMigration;
    }

    public int getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(int schemaVersion) {
        this.schemaVersion = schemaVersion;
    }
}
