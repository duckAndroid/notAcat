package com.pythoncat.mobilesafe.engine;

import com.pythoncat.mobilesafe.utils.PathUtil;
import com.pythoncat.proxy.bean.Download;
import com.pythoncat.proxy.bean.Update;
import com.pythoncat.proxy.net.okhttp.UpdateApp;

import rx.Observable;

/**
 * Created by pythonCat on 2016/8/14 0014.
 * 更新版本的核心类
 */
public class UpdateEngine extends Engine {

    private static String versionFile = "version.json";
    private static String apkFile = "update.apk";

    public static Observable<Update> checkVersion() {
        return UpdateApp.getNewVersionPOJO(PathUtil.checkVersion, getFileDir(), versionFile);
    }

    /**
     * 下载更新包
     *
     * @param url       file url
     * @return newly apk
     */
    public static Observable<Download> updateApk(String url) {
        return UpdateApp.downloadFileWithProgress(url, getCacheDir(), apkFile);
    }

    @Deprecated
    public static String getDestpathFromInfo(Download d){

        return "";
    }
}
