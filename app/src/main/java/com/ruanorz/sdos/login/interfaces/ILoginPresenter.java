package com.ruanorz.sdos.login.interfaces;

import com.ruanorz.sdos.models.User;

/**
 * Created by ruano on 09/02/2018.
 */

public interface ILoginPresenter {

    void userFoundOK(User user);
    void userFoundKO();
}
