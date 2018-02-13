package com.ruanorz.sdos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ruanorz.sdos.deps.DaggerDeps;
import com.ruanorz.sdos.deps.Deps;
import com.ruanorz.sdos.networking.NetworkModule;

import java.io.File;

/**
 * Created by ruano on 11/02/2018.
 */

public class BaseApp extends AppCompatActivity {
    Deps deps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();

    }

    public Deps getDeps() {
        return deps;
    }
}