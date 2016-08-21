package com.pythoncat.nocat.base;

import android.support.v4.app.Fragment;

import com.pythoncat.proxy.base.EventBusUtil;

/**
 * Created by pythonCat on 2016/8/14 0014.
 * base fragment
 */
public class BaseFragment extends Fragment {

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBusUtil.unregister(this);
    }

    public BaseAppCompactActivity get() {
        if (getActivity() instanceof BaseAppCompactActivity) {
            return (BaseAppCompactActivity) getActivity();
        } else {
            throw new RuntimeException("caller is not a child of base activity.... ");
        }
    }
}
