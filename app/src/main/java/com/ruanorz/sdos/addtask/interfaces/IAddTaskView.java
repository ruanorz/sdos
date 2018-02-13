package com.ruanorz.sdos.addtask.interfaces;

import com.ruanorz.sdos.models.TaskGroup;
import com.ruanorz.sdos.models.User;

import java.util.ArrayList;

/**
 * Created by ruano on 09/02/2018.
 */

public interface IAddTaskView {

    void getallSkillGroupOK(ArrayList<TaskGroup> skillgroups);
    void getallSkillGroupKO();

    void getFreeUsersForTaskKO();

    void taskAddedCompletedOK(User user);
    void taskAddedCompletedKO();

}
