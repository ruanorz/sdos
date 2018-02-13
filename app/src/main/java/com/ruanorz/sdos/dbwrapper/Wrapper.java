package com.ruanorz.sdos.dbwrapper;


import android.util.Log;

import com.ruanorz.sdos.models.FruitListResponse;
import com.ruanorz.sdos.models.Task;
import com.ruanorz.sdos.models.TaskGroup;
import com.ruanorz.sdos.models.User;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class Wrapper {

    private static Wrapper wrapperInstance = null;

    private static Realm realm;

    public static Wrapper getInstance(){
        if (wrapperInstance == null){
            wrapperInstance = new Wrapper();

            realm = Realm.getDefaultInstance();
        }
        return wrapperInstance;
    }

    public User userExistInDB(String userName){

        User user = realm.where(User.class).equalTo("userName", userName).findFirst();

        return user;
    }

    public ArrayList<TaskGroup> getAllSkillGroup(){


        ArrayList<TaskGroup> resultArray = new ArrayList(realm.where(TaskGroup.class).findAll().distinct("name"));

        return resultArray;
    }

    public ArrayList<User> getAllUser(){

        ArrayList<User> resultArray = new ArrayList(realm.where(User.class).findAll().distinct("userName"));

        return resultArray;
    }

    public ArrayList<User> getAllUserFromDBWhichCanDoThisTask(String task){

        ArrayList<User> resultArray = new ArrayList(realm.where(User.class).contains("skills.name", task).findAll().distinct("name"));
        return resultArray;

    }

    public RealmResults<Task> getPendingTaskOfThisUsers(ArrayList<User> users){

        RealmQuery<Task> userQuery = realm.where(Task.class);
        int i = 0;
        for(User user : users) {
            if(i != 0) {
                userQuery = userQuery.or();
            }
            userQuery = userQuery.equalTo("assignedTo.userName", user.getUserName());
            i++;
        }
        userQuery = userQuery.equalTo("pending", true);
        return userQuery.findAll();

    }

    public void closeRealm(){
        realm.close();
    }

    public void saveTask(Task task){

        realm.beginTransaction();
        realm.copyToRealm(task);
        realm.commitTransaction();

    }

    public void getAllTask(){
        ArrayList<User> resultArray = new ArrayList(realm.where(Task.class).findAll());
        Log.e("error", "NUMERO DE TASK: "+resultArray.size());
    }

    public ArrayList<Task> getAllTaskForThisUser(User user){
        ArrayList<Task> resultArray = new ArrayList(realm.where(Task.class).contains("assignedTo.userName", user.getUserName()).findAll());
        return resultArray;
    }

    public void updateTask(Task skill, boolean val){
        Task skilltoupdate = realm.where(Task.class)
                .equalTo("name", skill.getName())
                .findFirst();
        realm.beginTransaction();
        if (skilltoupdate != null) {
            skilltoupdate.setPending(val);
        }
        realm.commitTransaction();
    }

    public void saveFruits(List<FruitListResponse> fruitListResponse){
        realm.beginTransaction();
        realm.copyToRealm(fruitListResponse);
        realm.commitTransaction();
    }

    public ArrayList<FruitListResponse> getAllFruits(){
        ArrayList<FruitListResponse> resultArray = new ArrayList(realm.where(FruitListResponse.class).findAll());
        return resultArray;
    }
}
