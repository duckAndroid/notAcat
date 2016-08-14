package com.pythoncat.proxy;

import android.app.Application;

/**
 * Created by pythonCat on 2016/8/14 0014.
 * App utils
 */
public class App {

    private static Application mApp;

    public static Application get() {
        if (mApp == null) throw new RuntimeException("app is null !");
        return mApp;
    }

    public static void set(Application a) {
        mApp = a;
    }

    public static String getString(int resID, Object... ob) {

        return get().getString(resID, ob);
    }
    public static String getString(int resID) {
        return get().getString(resID);
    }
}
