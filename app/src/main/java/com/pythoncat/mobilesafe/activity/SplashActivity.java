package com.pythoncat.mobilesafe.activity;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.NotificationCompat;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.pythoncat.mobilesafe.R;
import com.pythoncat.mobilesafe.base.BaseAppCompactActivity;
import com.pythoncat.mobilesafe.engine.UpdateEngine;
import com.pythoncat.mobilesafe.viewHelper.NotifyHelperSimple;
import com.pythoncat.mobilesafe.viewHelper.AppDialoghelper;
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
    private AppDialoghelper appUpdate;
    private Subscription checkS;
    private Subscription updateApkS;
    private NotificationCompat.Builder builder;
    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView tvVersion = (TextView) findViewById(R.id.splash_tv_version);
        assert tvVersion != null;
        tvVersion.setText(App.getString(R.string.cur_version, PackageUtil.getVersionName(App.get())));
        container = (CoordinatorLayout) findViewById(R.id.splash_coor_layout);
        manager = (NotificationManager) get().getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(get());
        checkVersion();
    }

    private void checkVersion() {
        int localCode = PackageUtil.getVersionCode(App.get());
        Action1<Update> next = po -> {
            LogUtils.w("popopo");
            LogUtils.w(po);
            if (localCode <= po.versionCode) {
                Snackbar.make(container, R.string.can_update, Snackbar.LENGTH_LONG)
                        .setAction(R.string.update_now, v -> updateApk(e -> {
                            LogUtils.e("error");
                            LogUtils.e(e);
                        }, po))
                        .show();
            } else {
                Snackbar.make(container, R.string.cannot_update, Snackbar.LENGTH_LONG)
                        .show();
            }
        };
        RxJavaUtil.close(checkS);
        checkS = UpdateEngine.checkVersion()
                .subscribe(next, e -> {
                    LogUtils.e("error");
                    LogUtils.e(e);
                }, () -> LogUtils.w("completed!!!!"));
    }

    private void updateApk(Action1<Throwable> error, Update po) {
        //
        LogUtils.e("现在更新........!!! todo");
        if (appUpdate == null) {
            appUpdate = new AppDialoghelper(get(), v -> {
                appUpdate.cancel();
//                NotifyHelper.sendProgressNotification(manager, builder,
//                        "我第一个被发现", "我是标题", "我是内容",
//                        R.drawable.cat01,
//                        PendingIntent.getActivity(this, 0, new Intent(get(), SplashActivity.class), PendingIntent.FLAG_UPDATE_CURRENT),
//                        false, false, false);

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
//                            NotifyHelper.updateProgress(manager, builder, progress);
                            NotifyHelperSimple.update(manager, builder, progress, "下载完成！");
                        }, error,
                        () -> {
                            appUpdate.cancel();
                            LogUtils.e("completed!!!!");
                            LogUtils.w("completed!!!!");
                        });
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
