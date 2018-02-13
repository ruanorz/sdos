package com.ruanorz.sdos.deps;



import com.ruanorz.sdos.home.HomeActivity;
import com.ruanorz.sdos.home.HomeFruitFragment;
import com.ruanorz.sdos.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(HomeActivity listActivity);
    void inject(HomeFruitFragment homeFruitFragment);
}