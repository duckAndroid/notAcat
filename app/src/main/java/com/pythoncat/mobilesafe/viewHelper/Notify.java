package com.pythoncat.mobilesafe.viewHelper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;

import com.pythoncat.mobilesafe.R;
import com.pythoncat.proxy.App;

/**
 * Created by pythonCat on 2016/8/15 0015.
 */
public abstract class Notify {

    protected static void sample(NotificationCompat.Builder builder,
                                 String ticker, String title, String content,
                                 int smallIcon, PendingIntent intent,
                                 boolean sound, boolean vibrate, boolean lights) {
        builder.setTicker(ticker);
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setContentIntent(intent);
        builder.setColor(App.get().getResources().getColor(R.color.colorAccent));
        builder.setSmallIcon(smallIcon);
        builder.setLargeIcon(BitmapFactory.decodeResource(App.get().getResources(), R.drawable.cat03));
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

    protected static void send(NotificationManager manager, Notification build, int id) {
        manager.notify(id, build);
    }

}
