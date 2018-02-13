package com.ruanorz.sdos.login.interfaces;

/**
 * Created by ruano on 09/02/2018.
 */

public interface ILoginView {

    void loginOK(boolean userIsAdministrator);
    void loginKO();
    void showWait();
    void removeWait();
    void finishActivity();
}
