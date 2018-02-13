package com.ruanorz.sdos;

import android.app.Application;


import io.realm.Realm;
import io.realm.RealmConfiguration;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("sdos.realm")
                .schemaVersion(0)
                .initialData(new InitialData())
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfig);
    }

}