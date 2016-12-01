package com.example.b00sti.tripchallenge;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.example.b00sti.tripchallenge.utils.config.LastRealmMigration;
import com.example.skeleton.android_utils.util.BaseApp;
import com.facebook.FacebookSdk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.realm.RealmMigration;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-15
 */

public class App extends BaseApp {
    private static final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        FacebookSdk.sdkInitialize(getApplicationContext());
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.b00sti.tripchallenge",  // replace with your unique package name
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    @Override
    public RealmMigration setRealmMigration() {
        return new LastRealmMigration();
    }

    @Override
    protected int setRealmSchemaVersion() {
        return 1;
    }

}
