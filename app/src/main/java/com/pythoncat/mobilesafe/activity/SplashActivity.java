package com.pythoncat.mobilesafe.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.NotificationCompat;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.pythoncat.mobilesafe.R;
import com.pythoncat.mobilesafe.base.BaseAppCompactActivity;
import com.pythoncat.mobilesafe.engine.UpdateEngine;
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

    private static final int NOTIFICATION_ID = 1024 + 21;
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
                sendProgressNotification("我第一个被发现", "我是标题", "我是内容",
                        R.drawable.cat01,
                        PendingIntent.getActivity(this, 0, new Intent(get(), SplashActivity.class), PendingIntent.FLAG_UPDATE_CURRENT),
                        false, false, false);
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
    }
//    ################################

    public void sample(String ticker, String title, String content, int smallIcon, PendingIntent intent, boolean sound, boolean vibrate, boolean lights) {
        builder = new NotificationCompat.Builder(get());
        builder.setTicker(ticker);
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setContentIntent(intent);
        builder.setColor(Color.RED);
        builder.setSmallIcon(smallIcon);
        builder.setLargeIcon(BitmapFactory.decodeResource(App.get().getResources(), R.mipmap.ic_launcher));
        builder.setWhen(System.currentTimeMillis());
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        int defaults = 0;
        if (sound) {
            defaults |= Notification.DEFAULT_SOUND;
        }
        if (vibrate) {
            defaults |= Notification.DEFAULT_VIBRATE;
        }
        if (lights) {
            defaults |= Notification.DEFAULT_LIGHTS;
        }
        builder.setDefaults(defaults);
        builder.setOngoing(true);
    }

    public void sendSingleLineNotification(String ticker, String title, String content, int smallIcon, PendingIntent intent, boolean sound, boolean vibrate, boolean lights) {
        sample(ticker, title, content, smallIcon, intent, sound, vibrate, lights);
        Notification notification = builder.build();
        send(notification);
    }

//    ################################ @@@@@@@@@@@@@@@
//    ################################ @@@@@@@@@@@@@@@
//    ################################ @@@@@@@@@@@@@@@
//    ################################ @@@@@@@@@@@@@@@

    public void sendProgressNotification(String ticker, String title, String content, int smallIcon, PendingIntent intent, boolean sound, boolean vibrate, boolean lights) {
        sample(ticker, title, content, smallIcon, intent, sound, vibrate, lights);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i += 10) {
                    builder.setProgress(100, i, false);
                    send(builder.build());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //下载完成
                builder.setContentText("下载完成").setProgress(0, 0, false);
                send(builder.build());
            }
        }).start();
    }

    private void send(Notification build) {
        manager.notify(1024, builder.build());
    }
}
