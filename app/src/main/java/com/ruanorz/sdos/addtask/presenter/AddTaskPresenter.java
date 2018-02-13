package com.ruanorz.sdos.addtask.presenter;

import android.content.Context;
import android.util.Log;

import com.ruanorz.sdos.addtask.interactor.AddTaskInteractor;
import com.ruanorz.sdos.addtask.interfaces.IAddTaskPresenter;
import com.ruanorz.sdos.addtask.interfaces.IAddTaskView;
import com.ruanorz.sdos.models.Task;
import com.ruanorz.sdos.models.TaskGroup;
import com.ruanorz.sdos.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import io.realm.RealmResults;

/**
 * Created by ruano on 09/02/2018.
 */

public class AddTaskPresenter implements IAddTaskPresenter {

    Context context;
    IAddTaskView view;

    private ArrayList<User> users;
    private Task task;

    private String rb_clicked="";
    private List<User> usersThatCanDoTheTaskSelected = new ArrayList<>();

    public AddTaskPresenter(Context context, IAddTaskView view){
        this.context = context;
        this.view = view;
    }

    private AddTaskInteractor interactor = new AddTaskInteractor(context, this);

    public void getAllSkillGroup(){
        interactor.getAllSkillGroupfromDB();
    }

    @Override
    public void getallSkillGroupOK(ArrayList<TaskGroup> skillgroups) {
        view.getallSkillGroupOK(skillgroups);
    }

    @Override
    public void getallSkillGroupKO() {

    }

    public void assignTaskToUser(String task, Task fullTask){

        this.task = fullTask;
        interactor.getAllUserFromDBWhichCanDoThisTask(task);

    }

    @Override
    public void getallUsersOK(ArrayList<User> users) {
        this.users = users;
        Log.e("error", "users: "+users.size());

        interactor.getPendingTaskOfThisUsers(users);


    }

    @Override
    public void getallUsersKO() {
        view.getFreeUsersForTaskKO();
    }

    @Override
    public void getPendingTaskOfThisUsersOK(RealmResults<Task> skillsPending) {

        if (skillsPending.size()>0) {
            ArrayList<Task> resultArray = new ArrayList(skillsPending);
            ArrayList<String> noDuplicates = getDistinctElements(resultArray);
            ArrayList<Float> numHours = getNumHoursPending(noDuplicates, resultArray);
            Log.e("ERROR", "AAAAAAAAAAAAAAA " + numHours);

            int userIndex = getMostFreeUserForTheTask(numHours);

            String userNameAssigned = noDuplicates.get(userIndex).split(Pattern.quote("userName:"))[1].split(Pattern.quote("}"))[0];

            interactor.getInfoAboutAnUserName(userNameAssigned);
        }else {
            interactor.getInfoAboutAnUserName(users.get(0).getUserName());
        }

    }

    @Override
    public void getPendingTaskOfThisUsersKO() {

        interactor.getInfoAboutAnUserName(users.get(0).getUserName());

    }

    public static ArrayList<String> getDistinctElements(ArrayList<Task> arr){

        ArrayList<String> result = new ArrayList<>();
        for(int i=0;i<arr.size();i++){
            boolean isDistinct = false;
            for(int j=0;j<i;j++){
                if(arr.get(i).getAssignedTo().toString().equals(result.get(j))){
                    isDistinct = true;
                    break;
                }
            }
            if(!isDistinct){
                result.add(arr.get(i).getAssignedTo().toString());
            }
        }
        return result;
    }

    public ArrayList<Float> getNumHoursPending(ArrayList<String> noDuplicates, ArrayList<Task> resultArray){

        ArrayList<Float> numHours = new ArrayList<>();

        for (int i = 0; i<noDuplicates.size(); i++){
            Float sumatory = 0f;
            for (int j = 0; j<resultArray.size(); j++){

                if (noDuplicates.get(i).equals(resultArray.get(j).getAssignedTo().toString())){

                    sumatory += resultArray.get(j).getDuration();

                }

            }
            numHours.add(sumatory);
        }
        return numHours;
    }

    public int getMostFreeUserForTheTask(ArrayList<Float> sumatory) {

        float min = sumatory.get(0);
        int userIndex = 0;

        for (int i = 1; i < sumatory.size(); i++) {
            if (sumatory.get(i) < min) {
                min = sumatory.get(i);
                userIndex=i;
            }
        }

        return userIndex;

    }

    @Override
    public void userFoundOK(User user) {
        Log.e("error", "---------------------------------");
        Log.e("error", "Task assigned to:"+user);
        Log.e("error", "---------------------------------");

        this.task.setAssignedTo(user);
        interactor.saveTask(this.task);
        view.taskAddedCompletedOK(user);
    }

    @Override
    public void userFoundKO() {

    }
}
