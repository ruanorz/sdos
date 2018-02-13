package com.ruanorz.sdos.login.interactor;

import android.content.Context;
import android.util.Log;

import com.ruanorz.sdos.dbwrapper.Wrapper;
import com.ruanorz.sdos.login.interfaces.ILoginPresenter;
import com.ruanorz.sdos.login.interfaces.ILoginView;
import com.ruanorz.sdos.models.User;

public class LoginInteractor {

    Context context;
    ILoginPresenter presenter;

    public LoginInteractor(Context context, ILoginPresenter presenter){
        this.context = context;
        this.presenter = presenter;
    }

    public void checkUserNameInDB(String userName){

        Log.e("error", "Searching in DB username: "+userName);
        User userFound = Wrapper.getInstance().userExistInDB(userName);

        if (userFound!=null){
            presenter.userFoundOK(userFound);
        }else {
            presenter.userFoundKO();
        }
    }
}
