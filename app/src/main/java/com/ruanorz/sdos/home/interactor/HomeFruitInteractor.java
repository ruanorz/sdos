package com.ruanorz.sdos.home.interactor;

import android.content.Context;

import com.ruanorz.sdos.dbwrapper.Wrapper;
import com.ruanorz.sdos.home.interfaces.IHomeFruitPresenter;
import com.ruanorz.sdos.models.FruitListResponse;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * Created by ruano on 11/02/2018.
 */

public class HomeFruitInteractor {

    Context context;
    IHomeFruitPresenter presenter;

    public HomeFruitInteractor(Context context, IHomeFruitPresenter presenter){
        this.context = context;
        this.presenter = presenter;
    }

    public void saveFruits(List<FruitListResponse> fruitListResponse){
        Wrapper.getInstance().saveFruits(fruitListResponse);
    }

    public void getAllFruits(){
        ArrayList<FruitListResponse> arraylist = Wrapper.getInstance().getAllFruits();

        if (arraylist.size()>0){
            presenter.FruitsDownloadedOK(arraylist);
        }else {
            presenter.FruitsDownloadedKO();
        }
    }
}
