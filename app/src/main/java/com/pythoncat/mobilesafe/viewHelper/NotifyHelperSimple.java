package com.pythoncat.mobilesafe.viewHelper;

import android.app.NotificationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

import com.pythoncat.mobilesafe.R;

/**
 * Created by pythonCat on 2016/8/15 0015.
 * 简单的进度条通知栏的实现
 * <br/>无点击跳转
 *
 * @link http://www.jianshu.com/p/22e27a639787
 */
public class NotifyHelperSimple {

    private static int id = 2048;

    public static void show(@NonNull NotificationManager mNotifyManager, @NonNull NotificationCompat.Builder mBuilder,
                            @NonNull String first, @NonNull String title, @NonNull String content) {
        mBuilder.setContentTitle(title)
                .setTicker(first)
                .setContentText(content)
                .setSmallIcon(R.drawable.cat04);
        update(mNotifyManager, mBuilder, 0, "");
    }

    public static void update(@NonNull NotificationManager mNotifyManager, @NonNull NotificationCompat.Builder mBuilder,
                              int progress, @NonNull String completedContent) {
        mBuilder.setProgress(100, progress, false);
        mNotifyManager.notify(id, mBuilder.build());

        if (progress == 100) {
            mBuilder.setContentText(completedContent)// 下载完成
                    .setProgress(0, 0, false);    // 移除进度条
            mNotifyManager.notify(id, mBuilder.build());
        }
    }
    public static void cancel(@Nullable NotificationManager mNotifyManager) {
        if (mNotifyManager == null) return;
        mNotifyManager.cancel(id);
    }
}
