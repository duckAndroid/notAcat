package com.pythoncat.nocat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pythoncat.nocat.R;
import com.pythoncat.proxy.util.ToastHelper;
import com.pythoncat.proxy.view.SettingsItemView;

/**
 * @author pythonCat
 *         设置中心
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        SettingsItemView itemView = (SettingsItemView) findViewById(R.id.settings_item01);
        assert itemView != null;
        itemView.setOnClickListener(v -> {
            itemView.setChecked(!itemView.isChecked());
            ToastHelper.showShort("我被人点了");
        });
    }
}
