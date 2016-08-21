package com.pythoncat.proxy.viewhelper;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

/**
 * Created by pythonCat on 2016/8/15 0015.
 * 通知栏进度条实现
 * <br/>
 * <b>貌似这个进度条很难移除掉</b><br/>
 * 有点击跳转
 *
 * @link * http://www.jianshu.com/p/141fc999ac10
 */
public class NotifyHelper extends Notify {


    private static int id = 1998;

    public static void sendProgressNotification(@NonNull NotificationManager manager, @NonNull NotificationCompat.Builder builder,
                                                @NonNull String ticker, @NonNull String title, @NonNull String content,
                                                int smallIcon, @NonNull PendingIntent intent,
                                                boolean sound, boolean vibrate, boolean lights) {
        sample(builder, ticker, title, content, smallIcon, intent, sound, vibrate, lights);
        updateProgress(manager, builder, 0);
    }

    public static void updateProgress(@NonNull NotificationManager manager, @NonNull NotificationCompat.Builder builder, int progress) {
        builder.setProgress(100, progress, false);
        send(manager, builder.build(), id);
    }

    public static void cancel(@Nullable NotificationManager mNotifyManager) {
        if (mNotifyManager == null) return;
        mNotifyManager.cancel(id);
    }
}
