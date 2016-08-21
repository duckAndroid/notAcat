package com.pythoncat.nocat.base;

import com.apkfuns.logutils.LogUtils;
import com.pythoncat.nocat.R;
import com.pythoncat.proxy.App;
import com.pythoncat.proxy.util.ToastHelper;

/**
 * Created by pythonCat on 2016/8/22 0022.
 *
 * @author pythonCat
 *         双击退出程序的实现
 */
public class DoubleClickExitActivity extends BaseAppCompactActivity {


    private long lastClickTime;

    @Override
    public void onBackPressed() {

        LogUtils.i("last==" + lastClickTime);
        LogUtils.i("now===" + System.currentTimeMillis());
        if (System.currentTimeMillis() - lastClickTime > 2000) {
            ToastHelper.showShort(App.getString(R.string.exit_again));
            lastClickTime = System.currentTimeMillis();
        } else {
            ToastHelper.cancel();
            super.onBackPressed();
        }
    }

}
