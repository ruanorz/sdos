package com.ruanorz.sdos.models;

import io.realm.RealmObject;

/**
 * Created by ruano on 09/02/2018.
 */

public class Task extends RealmObject {

    private String name;
    private TaskGroup skillGroupBelong;
    private User assignedTo;
    private boolean pending;
    private float duration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskGroup getSkillGroupBelong() {
        return skillGroupBelong;
    }

    public void setSkillGroupBelong(TaskGroup skillGroupBelong) {
        this.skillGroupBelong = skillGroupBelong;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }
}
