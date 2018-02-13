package com.ruanorz.sdos.home.presenter;

import android.content.Context;
import android.util.Log;

import com.ruanorz.sdos.home.interactor.HomeFruitInteractor;
import com.ruanorz.sdos.home.interfaces.IHomeFruitPresenter;
import com.ruanorz.sdos.home.interfaces.IHomeFruitView;
import com.ruanorz.sdos.models.FruitListResponse;
import com.ruanorz.sdos.networking.Service;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


public class HomeFruitPresenter implements IHomeFruitPresenter{

    private final Service service;
    Context context;
    IHomeFruitView view;
    private CompositeSubscription subscriptions;

    public HomeFruitPresenter(Service service, Context context, IHomeFruitView view){
        this.service = service;
        this.context = context;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    private HomeFruitInteractor interactor = new HomeFruitInteractor(context, this);


    public void getFruitList(){

        checkIfNeedDownloadData();

    }

    public interface GetFruitListCallback{
        void onSuccess(List<FruitListResponse> fruitListResponse);

        void onError();
    }


    public void unsuscribeRxAndroid(){
        if (subscriptions.isUnsubscribed()){
            subscriptions.unsubscribe();
        }
    }

    public void checkIfNeedDownloadData(){
        interactor.getAllFruits();
    }

    @Override
    public void FruitsDownloadedOK(ArrayList<FruitListResponse> arraylist) {
        view.removeWait();
        view.getFruitListSuccess(arraylist);
    }

    @Override
    public void FruitsDownloadedKO() {
        Subscription subscription = service.getFruitList(new Service.GetFruitListCallback() {
            @Override
            public void onSuccess(List<FruitListResponse> fruitListResponse) {


                Log.e("error", fruitListResponse.size()+" <------------------");
                interactor.saveFruits(fruitListResponse);

                view.removeWait();
                view.getFruitListSuccess(fruitListResponse);

            }

            @Override
            public void onError() {
                Log.e("error", "ERROR!");
                view.removeWait();

            }

        });

        subscriptions.add(subscription);
    }
}
