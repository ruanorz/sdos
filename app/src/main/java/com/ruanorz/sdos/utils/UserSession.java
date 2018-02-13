package com.ruanorz.sdos.utils;

import com.ruanorz.sdos.dbwrapper.Wrapper;
import com.ruanorz.sdos.models.User;

import io.realm.Realm;

/**
 * Created by ruano on 09/02/2018.
 */

public class UserSession {

    private static UserSession userSessionInstance = null;

    private User userInfo;

    public static UserSession getInstance(){
        if (userSessionInstance == null){
            userSessionInstance = new UserSession();
        }
        return userSessionInstance;
    }

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    public void deleteUserSession(){
        userSessionInstance = null;
    }

}
