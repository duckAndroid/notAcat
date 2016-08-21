package com.pythoncat.nocat.engine;

import android.os.Environment;
import android.support.annotation.NonNull;

import com.pythoncat.proxy.App;
import com.pythoncat.proxy.util.PackageUtil;

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
    protected static String getNewlyPackageDir() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File dir = new File(Environment.getExternalStorageDirectory(), PackageUtil.getAppName(App.get()));
            return dir.getAbsolutePath();
        } else {
            throw new RuntimeException(" sdcard unmounted!!!");
        }
    }

}
