package com.example.b00sti.tripchallenge.model;

import io.realm.RealmObject;

/**
 * Created by Dominik (b00sti) Pawlik on 2017-01-12
 */

public class ChallengeTypeHelper extends RealmObject {
    private String enumDesc;

    public void saveEnum(ChallengeType challengeType) {
        this.enumDesc = challengeType.toString();
    }

    public ChallengeType getEnum() {
        return (enumDesc != null) ? ChallengeType.valueOf(enumDesc) : null;
    }
}
