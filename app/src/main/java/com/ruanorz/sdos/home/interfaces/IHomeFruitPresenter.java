package com.ruanorz.sdos.home.interfaces;

import com.ruanorz.sdos.models.FruitListResponse;

import java.util.ArrayList;

/**
 * Created by ruano on 11/02/2018.
 */

public interface IHomeFruitPresenter {

    void FruitsDownloadedOK(ArrayList<FruitListResponse> arraylist);
    void FruitsDownloadedKO();
}
