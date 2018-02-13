package com.ruanorz.sdos.home.presenter;

import android.content.Context;

import com.ruanorz.sdos.home.interfaces.IHomeView;
import com.ruanorz.sdos.models.User;
import com.ruanorz.sdos.utils.UserSession;

/**
 * Created by ruano on 09/02/2018.
 */

public class HomePresenter {

    Context context;
    IHomeView view;

    public HomePresenter(Context context, IHomeView view){
        this.context = context;
        this.view = view;
    }

    public void getUserLogedInInfo(){

        User user = UserSession.getInstance().getUserInfo();

        if (user!=null){
            view.userInfoOK(user);
        }else {
            view.userInfoKO();
        }

    }
}
