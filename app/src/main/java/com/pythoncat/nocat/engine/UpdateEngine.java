package com.pythoncat.nocat.engine;

import android.content.Intent;
import android.net.Uri;

import com.apkfuns.logutils.LogUtils;
import com.pythoncat.nocat.utils.PathUtil;
import com.pythoncat.proxy.App;
import com.pythoncat.proxy.bean.Download;
import com.pythoncat.proxy.bean.Update;
import com.pythoncat.proxy.net.okhttp.UpdateApp;
import com.pythoncat.proxy.util.ToastHelper;

import java.io.File;

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
     * @param url file url
     * @return newly apk
     */
    public static Observable<Download> updateApk(String url) {
        return UpdateApp.downloadInOkhttpUtils(url, getNewlyPackageDir(), apkFile);
    }

    public static void openApk() {
        File apk = getNewlyApk();
        LogUtils.e("OpenFile", apk);
        try {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(apk),
                    "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            App.get().startActivity(intent);
        } catch (Exception e) {
            LogUtils.e(e);
            ToastHelper.showShort("安装更新包失败，请手动安装");
        }
    }

    private static File getNewlyApk() {
        return new File(getNewlyPackageDir(), apkFile);
    }

    public static void deleteCacheNewly() {
        if (getNewlyApk().isFile()) {
            getNewlyApk().delete();
        }
    }
}
