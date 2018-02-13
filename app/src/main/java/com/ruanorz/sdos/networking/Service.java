package com.ruanorz.sdos.networking;

import com.ruanorz.sdos.models.FruitListResponse;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class Service {


    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getFruitList(final GetFruitListCallback callback) {


        return networkService.getFruitList("Fruit", "Peaches")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends List<FruitListResponse>>>() {
                    @Override
                    public Observable<? extends List<FruitListResponse>> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<List<FruitListResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError();

                    }

                    @Override
                    public void onNext(List<FruitListResponse> fruitListResponse) {
                        callback.onSuccess(fruitListResponse);

                    }
                });
    }

    public interface GetFruitListCallback{
        void onSuccess(List<FruitListResponse> fruitListResponse);

        void onError();
    }

}