package com.example.skeleton.android_utils.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.androidannotations.annotations.EBean;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-09
 */
@EBean(scope = EBean.Scope.Singleton)
public class FirebaseManager {
    private static final String TAG = "FirebaseManager";

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference mainDatabaseRef;
    private String userId = "";

    FirebaseManager() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        mainDatabaseRef = FirebaseDatabase.getInstance().getReference();
        if (firebaseUser != null) {
            userId = firebaseUser.getUid();
        }
    }

    public boolean isUserLogged() {
        return !"".equals(userId);
    }

    private boolean getUserUid() {
        userId = firebaseUser.getUid();
        return isUserLogged();
    }

    public boolean refreshUserId() {
        if (firebaseUser != null) {
            return getUserUid();
        } else {
            firebaseUser = firebaseAuth.getCurrentUser();
            if (firebaseUser != null) {
                return getUserUid();
            } else {
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseUser = firebaseAuth.getCurrentUser();
                return getUserUid();
            }
        }
    }

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public void setFirebaseAuth(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    public FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    public void setFirebaseUser(FirebaseUser firebaseUser) {
        this.firebaseUser = firebaseUser;
    }

    public DatabaseReference getMainDatabaseRef() {
        return mainDatabaseRef;
    }

    public void setMainDatabaseRef(DatabaseReference mainDatabaseRef) {
        this.mainDatabaseRef = mainDatabaseRef;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
