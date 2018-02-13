package com.ruanorz.sdos.home.interactor;

import android.content.Context;

import com.ruanorz.sdos.dbwrapper.Wrapper;
import com.ruanorz.sdos.home.interfaces.IHomeTechnicalPresenter;
import com.ruanorz.sdos.models.Task;
import com.ruanorz.sdos.models.User;

import java.util.ArrayList;


public class HomeTechnicalInteractor {

    Context context;
    IHomeTechnicalPresenter presenter;

    public HomeTechnicalInteractor(Context context, IHomeTechnicalPresenter presenter){
        this.context = context;
        this.presenter = presenter;
    }

    public void getAllTaskForUser(User user){
        ArrayList<Task> skills = Wrapper.getInstance().getAllTaskForThisUser(user);
        if (skills.size()>0){
            presenter.getAllTaskForUserOK(skills);
        }else {
            presenter.getAllTaskForUserKO();
        }
    }

    public void updateTask(Task skill, boolean val){
        Wrapper.getInstance().updateTask(skill, val);
    }
}
