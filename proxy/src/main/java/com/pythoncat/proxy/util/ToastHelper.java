package com.pythoncat.proxy.util;

import android.widget.Toast;

import com.pythoncat.proxy.App;

/**
 * Created by pythonCat on 2016/8/14 0014.
 */
public class ToastHelper {

    private static Toast mToast;

    public static void showShort(CharSequence ch) {
        cancel();
        if (mToast == null) {
            mToast = Toast.makeText(App.get(), ch, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(ch);
        }
        mToast.show();
    }

    public static void cancel() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
