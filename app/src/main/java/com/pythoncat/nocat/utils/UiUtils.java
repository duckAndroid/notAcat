package com.pythoncat.nocat.utils;

import com.pythoncat.proxy.App;

/**
 * Created by pythonCat on 2016/8/14 0014.
 *
 * @author pythonCat
 *         Ui 相关的工具类
 */
public class UiUtils {

    public static int dp2px(float dp) {
        final float scale = App.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2dp(float px) {
        final float scale = App.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
