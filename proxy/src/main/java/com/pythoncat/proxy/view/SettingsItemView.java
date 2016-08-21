package com.pythoncat.proxy.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.pythoncat.proxy.R;

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
        this(context, null);
    }

    public SettingsItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @TargetApi(21)
    public SettingsItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    public SettingsItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_settings, this, false);
        addView(rootView);
        cbCheck = (CheckBox) rootView.findViewById(R.id.setting_item_checkbox);
        tvTitle = (TextView) rootView.findViewById(R.id.setting_item_title);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SettingsItemView);
        boolean check = ta.getBoolean(R.styleable.SettingsItemView_checked, false);
        String titleText = ta.getString(R.styleable.SettingsItemView_text);
        int textColor = ta.getColor(R.styleable.SettingsItemView_textcolor, 0);
        float textSize = ta.getDimension(R.styleable.SettingsItemView_textsize, TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 20, getResources().getDisplayMetrics()));
        tvTitle.setTextSize(textSize);
        tvTitle.setText(titleText);
        tvTitle.setTextColor(textColor);
        cbCheck.setChecked(check);
        ta.recycle();
    }

    // #################  public interface

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

    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
    }

    public int getVisibility() {
        return super.getVisibility();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        super.setOnClickListener(listener);
    }
}
