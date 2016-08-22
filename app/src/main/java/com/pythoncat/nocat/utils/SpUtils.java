package com.pythoncat.nocat.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.pythoncat.nocat.Configs;
import com.pythoncat.proxy.App;

/**
 * Created by pythonCat on 2016/8/21 0021.
 *
 * @author pythonCat
 *         基础配置信息
 */
public class SpUtils {
    private static SharedPreferences get() {
        return App.get().getSharedPreferences(Configs.settingsSp, Context.MODE_PRIVATE);
    }

    /**
     * 自动更新
     *
     * @return auto
     */
    public static boolean isAutoUpdate() {
        return get().getBoolean(Configs.settingsSpAutoUpdate, false);
    }

    /**
     * 自动更新
     *
     * @param auto boolean
     */
    public static void setAutoUpdate(boolean auto) {
        get().edit().putBoolean(Configs.settingsSpAutoUpdate, auto).apply();
    }
    // ########################################
    /**
     * 自动更新
     *
     * @return auto
     */
    public static boolean isOpenSecurity() {
        return get().getBoolean(Configs.settingsSpOpenSecurity, false);
    }

    /**
     * 自动更新
     *
     * @param auto boolean
     */
    public static void setOpenSecurity(boolean auto) {
        get().edit().putBoolean(Configs.settingsSpOpenSecurity, auto).apply();
    }
    // ########################################
}
