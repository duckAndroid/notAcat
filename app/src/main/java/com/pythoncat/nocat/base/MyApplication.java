package com.pythoncat.nocat.base;

import android.app.Application;
import android.app.NotificationManager;

import com.pythoncat.nocat.engine.UpdateEngine;
import com.pythoncat.proxy.App;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by pythonCat on 2016/8/14 0014.
 * Application
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        App.set(this);
        UpdateEngine.deleteCacheNewly();
        initOkhttpUtils();
    }

    private void initOkhttpUtils() {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null); //  忽略https认证
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("okhttputils", true))
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    /**
     * 关键是这个方法真机中不会调用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        reaseAll();
    }

    private void reaseAll() {
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.cancelAll();
    }
}
