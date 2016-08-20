package com.pythoncat.proxy.util;

import android.widget.Toast;

import com.pythoncat.proxy.App;

/**
 * Created by pythonCat on 2016/8/14 0014.
 *
 * @author pythonCat
 */
public class ToastHelper {

    private static Toast mToast;

    public static void showShort(CharSequence ch) {
        if (mToast == null) {
            mToast = Toast.makeText(App.get(), ch, Toast.LENGTH_SHORT);
        } else {
            cancel();
            mToast = Toast.makeText(App.get(), ch, Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void cancel() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
