package com.pythoncat.nocat.base;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.pythoncat.nocat.R;
import com.pythoncat.nocat.fragment.ToolsFragment;
import com.pythoncat.proxy.base.EventBusUtil;

/**
 * Created by pythonCat on 2016/8/14 0014.
 * base activity
 */
public class BaseAppCompactActivity extends AppCompatActivity implements ToolsFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // todo 返回键
            finish();
            return true;
        } else if (item.getItemId() == R.id.action_settings) {
            return item.getItemId() == R.id.action_settings;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

    public boolean isFinished() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return isDestroyed() && isFinishing();
        } else {
            return isFinishing();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
