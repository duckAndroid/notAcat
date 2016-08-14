package com.pythoncat.mobilesafe.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pythoncat.proxy.base.EventBusUtil;

/**
 * Created by pythonCat on 2016/8/14 0014.
 * base activity
 */
public class BaseAppCompactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtil.unregister(this);
    }

    public AppCompatActivity get() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (isDestroyed() && isFinishing()) {
                throw new RuntimeException(" current activity is destroyed !!!");
            }
        } else {
            if (isFinishing()) {
                throw new RuntimeException(" current activity is destroyed !!!");
            }
        }
        return this;
    }
}
