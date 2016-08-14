package com.pythoncat.mobilesafe.base;

import android.app.Application;

import com.pythoncat.proxy.App;

/**
 * Created by pythonCat on 2016/8/14 0014.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        App.set(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

    }
}
