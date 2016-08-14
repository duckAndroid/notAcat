package com.pythoncat.mobilesafe.base;

import android.support.v4.app.Fragment;

import com.pythoncat.proxy.base.EventBusUtil;

/**
 * Created by pythonCat on 2016/8/14 0014.
 */
public class BaseFragment extends Fragment {

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBusUtil.unregister(this);
    }
}
