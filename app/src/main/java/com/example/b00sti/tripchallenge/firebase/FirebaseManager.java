package com.example.b00sti.tripchallenge.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-09
 */
public class FirebaseManager {
    private static FirebaseManager ourInstance = new FirebaseManager();
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference mainDatabaseRef;
    private String userId = "";

    private FirebaseManager() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        FirebaseDatabase.getInstance().getReference();
        if (firebaseUser != null) {
            userId = firebaseUser.getUid();
        }
    }

    public static FirebaseManager getInstance() {
        return ourInstance;
    }

    public boolean isUserLogged() {
        return !"".equals(userId);
    }

}
