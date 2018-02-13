package com.ruanorz.sdos.addtask.interactor;

import android.content.Context;
import android.util.Log;

import com.ruanorz.sdos.addtask.interfaces.IAddTaskPresenter;
import com.ruanorz.sdos.dbwrapper.Wrapper;
import com.ruanorz.sdos.models.Task;
import com.ruanorz.sdos.models.TaskGroup;
import com.ruanorz.sdos.models.User;

import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by ruano on 09/02/2018.
 */

public class AddTaskInteractor {

    Context context;
    IAddTaskPresenter presenter;

    public AddTaskInteractor(Context context, IAddTaskPresenter presenter){
        this.context = context;
        this.presenter = presenter;
    }

    public void getAllSkillGroupfromDB(){
        ArrayList<TaskGroup> skillGroups = Wrapper.getInstance().getAllSkillGroup();

        if (skillGroups.size()>0){
            for (int i = 0; i<skillGroups.size();i++){
                if (skillGroups.get(i).getName().equals("")||skillGroups.get(i).getName()==null) {
                    skillGroups.remove(i);
                }
            }
            presenter.getallSkillGroupOK(skillGroups);
        }else {
            presenter.getallSkillGroupKO();
        }
    }

    public void getAllUserFromDBWhichCanDoThisTask(String task){

        ArrayList<User> users = Wrapper.getInstance().getAllUserFromDBWhichCanDoThisTask(task);

        if (users.size()>0){
            for (int i = 0; i<users.size();i++){
                if (users.get(i).getName().equals("")||users.get(i).getName()==null) {
                    users.remove(i);
                }
            }
            presenter.getallUsersOK(users);
        }else {
            presenter.getallUsersKO();
        }

    }

    public void getPendingTaskOfThisUsers(ArrayList<User> users){

        RealmResults<Task> skillsPending = Wrapper.getInstance().getPendingTaskOfThisUsers(users);

        if (users.size()>0){
            for (int i = 0; i<users.size();i++){
                if (users.get(i).getName().equals("")||users.get(i).getName()==null) {
                    users.remove(i);
                }
            }
            presenter.getPendingTaskOfThisUsersOK(skillsPending);
        }else {
            presenter.getPendingTaskOfThisUsersKO();
        }

        Log.e("error", "PENDINGTASKS: "+skillsPending.size());

    }

    public void getInfoAboutAnUserName(String userName){

        Log.e("error", "Searching in DB username: "+userName);
        User userFound = Wrapper.getInstance().userExistInDB(userName);

        if (userFound!=null){
            presenter.userFoundOK(userFound);
        }else {
            presenter.userFoundKO();
        }

    }

    public void saveTask(Task task){
        Wrapper.getInstance().saveTask(task);
    }

}
