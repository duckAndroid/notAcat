package com.pythoncat.proxy.base;

import rx.Subscription;

/**
 * Created by pythonCat on 2016/8/14 0014.
 */
public class RxJavaUtil {

    public static void close(Subscription s) {

        if (s != null && !s.isUnsubscribed()) {
            s.unsubscribe();
        }
    }
}
