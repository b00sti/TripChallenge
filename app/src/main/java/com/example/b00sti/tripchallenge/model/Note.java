package com.example.b00sti.tripchallenge.model;

import io.realm.RealmObject;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Dominik (b00sti) Pawlik on 2017-01-12
 */

@Getter
@Setter
public class Note extends RealmObject {
    private String note;
}
