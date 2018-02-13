package com.ruanorz.sdos.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by ruano on 09/02/2018.
 */

public class User extends RealmObject {

    private String name;
    private String userName;
    private String password;
    private boolean administrator;
    private RealmList<TaskGroup> skills;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public RealmList<TaskGroup> getSkills() {
        return skills;
    }

    public void setSkills(RealmList<TaskGroup> skills) {
        this.skills = skills;
    }
}
