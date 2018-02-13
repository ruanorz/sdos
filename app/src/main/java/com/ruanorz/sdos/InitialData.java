package com.ruanorz.sdos;


import com.ruanorz.sdos.models.Task;
import com.ruanorz.sdos.models.TaskGroup;
import com.ruanorz.sdos.models.User;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class InitialData implements Realm.Transaction {
    @Override
    public void execute(Realm realm) {


        List<TaskGroup> skillsgroup = new ArrayList<>();
        TaskGroup modelskillGroupSales = new TaskGroup();
        modelskillGroupSales.setName("Sales");
        skillsgroup.add(modelskillGroupSales);

        TaskGroup modelskillGroupMarketing = new TaskGroup();
        modelskillGroupMarketing.setName("Marketing");
        skillsgroup.add(modelskillGroupMarketing);

        TaskGroup modelskillGroupHR = new TaskGroup();
        modelskillGroupHR.setName("Human Resources");
        skillsgroup.add(modelskillGroupHR);

        TaskGroup modelskillGroupDev = new TaskGroup();
        modelskillGroupDev.setName("Development");
        skillsgroup.add(modelskillGroupDev);

        for (TaskGroup skillgroup : skillsgroup) {
            realm.insertOrUpdate(skillgroup);
        }


        RealmList<TaskGroup> skillGroupList = new RealmList<TaskGroup>();
        skillGroupList.add(modelskillGroupDev);

        List<User> users = new ArrayList<>();
        User model = new User();
        model.setName("José Manuel Ruano Ruiz");
        model.setUserName("ruan0rz");
        model.setPassword("pass");
        model.setAdministrator(true);
        model.setSkills(skillGroupList);
        users.add(model);

        RealmList<TaskGroup> skillGroupList2 = new RealmList<TaskGroup>();
        skillGroupList2.add(modelskillGroupHR);

        User model2 = new User();
        model2 = new User();
        model2.setName("Paqui Muriel Rodriguez");
        model2.setUserName("muriro");
        model2.setPassword("pass");
        model2.setAdministrator(false);
        model2.setSkills(skillGroupList2);
        users.add(model2);

        for (User user : users) {
            realm.insertOrUpdate(user);
        }




        List<Task> skills = new ArrayList<>();
        Task modelskill = new Task();
        modelskill.setName("programar login");
        modelskill.setAssignedTo(model);
        modelskill.setDuration(22);
        modelskill.setPending(true);
        modelskill.setSkillGroupBelong(modelskillGroupDev);
        skills.add(modelskill);



        Task modelskill2 = new Task();
        modelskill2.setName("programar BD");
        modelskill2.setAssignedTo(model);
        modelskill2.setDuration(10);
        modelskill2.setPending(true);
        modelskill2.setSkillGroupBelong(modelskillGroupDev);
        skills.add(modelskill2);

        Task modelskill3 = new Task();
        modelskill3.setName("Contratar becario");
        modelskill3.setAssignedTo(model2);
        modelskill3.setDuration(22);
        modelskill3.setPending(true);
        modelskill3.setSkillGroupBelong(modelskillGroupHR);
        skills.add(modelskill3);

        for (Task skill : skills) {
            realm.insertOrUpdate(skill);
        }


    }

    @Override
    public boolean equals(Object object) {
        return object != null && object instanceof InitialData;
    }

    @Override
    public int hashCode() {
        return InitialData.class.hashCode();
    }
}