package com.pythoncat.mobilesafe.engine;

import android.support.annotation.NonNull;

import com.pythoncat.proxy.App;

import java.io.File;

/**
 * Created by pythonCat on 2016/8/14 0014.
 * app 核心接口
 */
public abstract class Engine {

    /**
     * 版本是否更新的配置文件所在路径
     *
     * @return dir
     */
    @NonNull
    protected static String getFileDir() {
        File dir = App.get().getFilesDir();
        return dir.getAbsolutePath();
    }

    /**
     * 版本更新包所在路径
     *
     * @return dir
     */
    @NonNull
    protected static String getCacheDir() {
        File dir = App.get().getCacheDir();
        return dir.getAbsolutePath();
    }

}
