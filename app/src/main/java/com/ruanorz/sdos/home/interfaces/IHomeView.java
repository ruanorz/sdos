package com.ruanorz.sdos.home.interfaces;

import com.ruanorz.sdos.models.User;

/**
 * Created by ruano on 09/02/2018.
 */

public interface IHomeView {

    void userInfoOK(User user);
    void userInfoKO();
    void showWait();
    void removeWait();

}
