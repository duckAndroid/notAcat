package com.pythoncat.nocat.activity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.NotificationCompat;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.pythoncat.nocat.R;
import com.pythoncat.nocat.base.BaseAppCompactActivity;
import com.pythoncat.nocat.engine.UpdateEngine;
import com.pythoncat.nocat.viewHelper.AppDialogHelper;
import com.pythoncat.nocat.viewHelper.NotifyHelperSimple;
import com.pythoncat.proxy.App;
import com.pythoncat.proxy.base.RxJavaUtil;
import com.pythoncat.proxy.bean.Update;
import com.pythoncat.proxy.util.PackageUtil;

import rx.Subscription;
import rx.functions.Action1;

/**
 * @author pythonCat
 *         启动界面，显示当前版本及检查是否有更新
 */
public class SplashActivity extends BaseAppCompactActivity {

    private CoordinatorLayout container;
    private AppDialogHelper appUpdate;
    private Subscription checkS;
    private Subscription updateApkS;
    private NotificationCompat.Builder builder;
    private NotificationManager manager;
    private Snackbar.Callback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
        checkVersion();
    }

    private void init() {
        TextView tvVersion = (TextView) findViewById(R.id.splash_tv_version);
        assert tvVersion != null;
        tvVersion.setText(App.getString(R.string.cur_version, PackageUtil.getVersionName(App.get())));
        container = (CoordinatorLayout) findViewById(R.id.splash_coor_layout);
        manager = (NotificationManager) get().getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(get());
        callback = new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {

                switch (event) {
                    case Snackbar.Callback.DISMISS_EVENT_ACTION: // "Snackbar通过Action的点击事件退出"
                        LogUtils.w("Snackbar通过Action的点击事件退出");
                        break;
                    case Snackbar.Callback.DISMISS_EVENT_CONSECUTIVE:  // "Snackbar由于新的Snackbar显示而退出"
                    case Snackbar.Callback.DISMISS_EVENT_MANUAL: // "Snackbar通过调用dismiss()方法退出"
                    case Snackbar.Callback.DISMISS_EVENT_SWIPE: // "Snackbar右划退出"
                    case Snackbar.Callback.DISMISS_EVENT_TIMEOUT: //  "Snackbar自然退出"
                        LogUtils.w("Snackbar退出");
                        startMain();
                        break;
                }
            }
        };
    }

    private void checkVersion() {
        int localCode = PackageUtil.getVersionCode(App.get());
        LogUtils.w("localVersionCode = " + localCode);
        RxJavaUtil.close(checkS);
        checkS = UpdateEngine.checkVersion()
                .subscribe( po -> {
                    LogUtils.w("localVersionCode = " + localCode + " , netVersionCode =  " + po.versionCode);
                    if (localCode < po.versionCode) {
                        Snackbar.make(container, R.string.can_update, Snackbar.LENGTH_LONG)
                                .setCallback(callback)
                                .setAction(R.string.update_now, v -> updateApk(e -> {
                                    LogUtils.e("error");
                                    LogUtils.e(e);
                                }, po))
                                .show();
                    } else {
                        Snackbar.make(container, R.string.cannot_update, Snackbar.LENGTH_LONG)
                                .setCallback(callback)
                                .show();
                    }
                }, e -> {
                    LogUtils.e("error");
                    LogUtils.e(e);
                    Snackbar.make(container, R.string.check_version_fail, Snackbar.LENGTH_LONG)
                            .setCallback(callback)
                            .show();
                }, () -> LogUtils.w("completed!!!!"));
    }

    private void updateApk(Action1<Throwable> error, Update po) {
        //
        LogUtils.e("现在更新........!!! todo");
        if (appUpdate == null) {
            appUpdate = new AppDialogHelper(get(), v -> {
                appUpdate.cancel();
                NotifyHelperSimple.show(manager, builder, "我第一个被发现", "我是标题", "我是内容");
            });
        }
        appUpdate.showDialog();
        RxJavaUtil.close(updateApkS);
        updateApkS = UpdateEngine.updateApk(po.updateUrl)
                .subscribe(download -> {
                            int progress = (int) (download.progress * 1f / download.total * 100f);
                            if (progress <= 1) progress = 1;
                            LogUtils.w("progress====" + progress + ", p=" + download.progress + " , to=" + download.total);
                            appUpdate.pbProgress.setProgress(progress);
                            NotifyHelperSimple.update(manager, builder, progress, "下载完成！");
                        }, error,
                        () -> {
                            appUpdate.cancel();
                            LogUtils.e("completed!!!!");
                            openNewlyApk();
                        });
    }

    private void openNewlyApk() {
        UpdateEngine.openApk(get());
    }

    private void startMain() {
        startActivity(new Intent(get(), MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (appUpdate != null) {
            appUpdate.cancel();
        }
        RxJavaUtil.close(updateApkS);
        RxJavaUtil.close(checkS);
        NotifyHelperSimple.cancel(manager);
    }
}
