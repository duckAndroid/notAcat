package com.pythoncat.nocat.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.pythoncat.nocat.R;

/**
 * Created by pythonCat on 2016/8/21 0021.
 * <p>
 *
 * @author pythonCat
 *         设置中心 item view
 *         {@link # item_settings.xml}
 */
public class SettingsItemView extends FrameLayout {

    private TextView tvTitle;
    private CheckBox cbCheck;

    public SettingsItemView(Context context) {
        super(context);
    }

    public SettingsItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SettingsItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public SettingsItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        View rootView = View.inflate(context, R.layout.item_settings, this);
        addView(rootView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        initChildren(context, rootView);
    }

    private void initChildren(Context context, View rootView) {
        tvTitle = (TextView) rootView.findViewById(R.id.setting_item_title);
        cbCheck = (CheckBox) rootView.findViewById(R.id.setting_item_checkbox);
    }

    public void setText(CharSequence ch) {
        tvTitle.setText(ch);
    }

    public void setChecked(boolean check) {
        cbCheck.setChecked(check);
    }

    public String getText() {
        return tvTitle.getText().toString();
    }

    public boolean isChecked() {
        return cbCheck.isChecked();
    }
}
