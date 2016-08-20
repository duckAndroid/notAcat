package com.pythoncat.nocat.base;

import android.app.Application;

import com.pythoncat.nocat.engine.UpdateEngine;
import com.pythoncat.proxy.App;

/**
 * Created by pythonCat on 2016/8/14 0014.
 * Application
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        App.set(this);
        UpdateEngine.deleteCacheNewly();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

    }
}
