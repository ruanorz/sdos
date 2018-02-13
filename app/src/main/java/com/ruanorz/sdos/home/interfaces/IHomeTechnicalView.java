package com.ruanorz.sdos.home.interfaces;

import com.ruanorz.sdos.models.Task;

import java.util.ArrayList;

/**
 * Created by ruano on 11/02/2018.
 */

public interface IHomeTechnicalView {

    void getAllTaskForUserOK(ArrayList<Task> skills);
    void getAllTaskForUserKO();

}
