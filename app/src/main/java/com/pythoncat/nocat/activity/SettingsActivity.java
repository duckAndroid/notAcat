package com.pythoncat.nocat.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.pythoncat.nocat.Configs;
import com.pythoncat.nocat.R;
import com.pythoncat.nocat.base.BaseAppCompactActivity;
import com.pythoncat.nocat.utils.SpUtils;
import com.pythoncat.proxy.App;
import com.pythoncat.proxy.view.SettingsItemView;

/**
 * @author pythonCat
 *         设置中心
 */
public class SettingsActivity extends BaseAppCompactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        String title = handlerIntent();
        initToolbar(title);
        initContent();
    }

    private void initContent() {
        {
            SettingsItemView itemAutoUpdateView = getView(R.id.settings_item_auto_update);
            assert itemAutoUpdateView != null;
            itemAutoUpdateView.setText(App.getString(R.string.auto_update));
            itemAutoUpdateView.setChecked(SpUtils.isAutoUpdate());
            itemAutoUpdateView.setOnClickListener(v -> {
                SpUtils.setAutoUpdate(!SpUtils.isAutoUpdate());
                itemAutoUpdateView.setChecked(SpUtils.isAutoUpdate());
            }); // auto update ?
        }
        {
            SettingsItemView itemSecurityView = getView(R.id.settings_item_security);
            itemSecurityView.setChecked(SpUtils.isOpenSecurity());
            itemSecurityView.setText(App.getString(R.string.mobile_security));
            itemSecurityView.setOnClickListener(v -> {
                SpUtils.setOpenSecurity(!SpUtils.isOpenSecurity());
                itemSecurityView.setChecked(SpUtils.isOpenSecurity());
            }); // open security ?
        }
    }

    @NonNull
    private String handlerIntent() {
        String title = getIntent().getStringExtra(Configs.itemTitleKey);
        if (title == null) title = "no title....";
        return title;
    }

    private void initToolbar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(title);
        actionBar.setDisplayHomeAsUpEnabled(true); // 打开后退键
    }
}
