package com.pythoncat.proxy.base;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by pythonCat on 2016/8/14 0014.
 */
public class EventBusUtil {

    public static void unregister(Object subscriber) {

        EventBus.getDefault().unregister(subscriber);
    }

    public static void regist(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }
}
