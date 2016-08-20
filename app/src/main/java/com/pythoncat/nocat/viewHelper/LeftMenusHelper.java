package com.pythoncat.nocat.viewHelper;

import android.view.View;
import android.widget.RadioGroup;

import com.pythoncat.nocat.R;
import com.pythoncat.nocat.activity.MainActivity;
import com.pythoncat.nocat.base.BaseFragment;
import com.pythoncat.nocat.fragment.CameraFragment;
import com.pythoncat.nocat.fragment.HeadFragment;

/**
 * Created by pythonCat on 2016/8/21 0021.
 *
 * @author pythonCat
 */
public class LeftMenusHelper {

    private final MainActivity mActivity;
    private final View rootView;

    public LeftMenusHelper(MainActivity c, View root) {
        if (c == null) throw new RuntimeException("caller activity is null !");
        this.mActivity = c;
        this.rootView = root;
    }

    public void init() {
        View headView = rootView.findViewById(R.id.left_menu_head);
        headView.setOnClickListener(v -> {
            replaceFragment(HeadFragment.newInstance("ss", "bb"));
            mActivity.closeDrawer();
        });
        RadioGroup rg = (RadioGroup) rootView.findViewById(R.id.left_menu_rg);
        rg.check(R.id.rb_item01); // default
        replaceFragment(CameraFragment.newInstance("xxx", "xx")); // default
        rg.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_item01:
                    replaceFragment(CameraFragment.newInstance("xxx", "xx"));
                    break;
                case R.id.rb_item02:
                    replaceFragment(CameraFragment.newInstance("xxx", "xx"));
                    break;
                case R.id.rb_item03:
                    replaceFragment(CameraFragment.newInstance("xxx", "xx"));
                    break;
                case R.id.rb_item04:
                    replaceFragment(CameraFragment.newInstance("xxx", "xx"));
                    break;
            }
            mActivity.closeDrawer();
        });
    }

    private void replaceFragment(BaseFragment bf) {
        mActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,
                        bf)
                .commit();
    }
}
