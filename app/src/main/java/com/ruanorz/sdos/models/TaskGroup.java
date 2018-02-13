package com.ruanorz.sdos.models;

import io.realm.RealmObject;

/**
 * Created by ruano on 09/02/2018.
 */

public class TaskGroup extends RealmObject {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
