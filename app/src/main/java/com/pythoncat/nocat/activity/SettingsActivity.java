package com.pythoncat.nocat.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.pythoncat.nocat.Configs;
import com.pythoncat.nocat.R;
import com.pythoncat.nocat.base.BaseAppCompactActivity;
import com.pythoncat.proxy.util.ToastHelper;
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
        SettingsItemView itemView = (SettingsItemView) findViewById(R.id.settings_item01);
        assert itemView != null;
        itemView.setOnClickListener(v -> {
            itemView.setChecked(!itemView.isChecked());
            ToastHelper.showShort("我被人点了");
        });
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
