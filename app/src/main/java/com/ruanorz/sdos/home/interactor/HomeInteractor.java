package com.ruanorz.sdos.home.interactor;

import android.content.Context;

import com.ruanorz.sdos.home.interfaces.IHomeTechnicalView;

/**
 * Created by ruano on 09/02/2018.
 */

public class HomeInteractor {

    Context context;
    IHomeTechnicalView presenter;

    public HomeInteractor(Context context, IHomeTechnicalView presenter){
        this.context = context;
        this.presenter = presenter;
    }
}
