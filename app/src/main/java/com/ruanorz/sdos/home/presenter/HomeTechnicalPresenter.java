package com.ruanorz.sdos.home.presenter;

import android.content.Context;

import com.ruanorz.sdos.home.interactor.HomeTechnicalInteractor;
import com.ruanorz.sdos.home.interfaces.IHomeTechnicalPresenter;
import com.ruanorz.sdos.home.interfaces.IHomeTechnicalView;
import com.ruanorz.sdos.models.Task;
import com.ruanorz.sdos.models.User;

import java.util.ArrayList;

/**
 * Created by ruano on 11/02/2018.
 */

public class HomeTechnicalPresenter implements IHomeTechnicalPresenter {

    Context context;
    IHomeTechnicalView view;

    public HomeTechnicalPresenter(Context context, IHomeTechnicalView view){
        this.context = context;
        this.view = view;
    }

    private HomeTechnicalInteractor interactor = new HomeTechnicalInteractor(context, this);

    public void getAllTaskForUser(User user){
        interactor.getAllTaskForUser(user);
    }

    @Override
    public void getAllTaskForUserOK(ArrayList<Task> skills) {
        view.getAllTaskForUserOK(skills);
    }

    @Override
    public void getAllTaskForUserKO() {
        view.getAllTaskForUserKO();
    }

    public void updateTask(Task skill){
        if (skill.isPending()){
            interactor.updateTask(skill, false);
        }else {
            interactor.updateTask(skill, true);
        }
    }
}
