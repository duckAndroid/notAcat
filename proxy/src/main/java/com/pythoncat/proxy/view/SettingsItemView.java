package com.pythoncat.proxy.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.pythoncat.proxy.R;
import com.pythoncat.proxy.util.UiUtils;

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
    private View underLine;

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
        if (isInEditMode()) return;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_settings, this, false);
        addView(rootView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        cbCheck = (CheckBox) rootView.findViewById(R.id.setting_item_checkbox);
        tvTitle = (TextView) rootView.findViewById(R.id.setting_item_title);
        underLine = rootView.findViewById(R.id.setting_item_under_line);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SettingsItemView);
        boolean check = ta.getBoolean(R.styleable.SettingsItemView_checked, false);
        String titleText = ta.getString(R.styleable.SettingsItemView_text);
        ColorStateList colorStateList = ta.getColorStateList(R.styleable.SettingsItemView_textcolor);
        int textSize = ta.getDimensionPixelSize(R.styleable.SettingsItemView_textsize, UiUtils.sp2px(16));
        int underLineBackgroundRes = ta.getResourceId(R.styleable.SettingsItemView_underlinebackground, R.color.colorAccent);
        LogUtils.i(" text size == =" + textSize);
        if (isInEditMode()) return;
        underLine.setBackgroundResource(underLineBackgroundRes);
        tvTitle.setTextSize(UiUtils.px2sp(textSize)); // 默认单位是sp
        tvTitle.setText(titleText);
        tvTitle.setTextColor(colorStateList);
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

    public void setUnderLineBackgroundResource(int resource) {
        underLine.setBackgroundResource(resource);
    }

    public Drawable getUnderLineBackgroundResource() {
        return underLine.getBackground();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        super.setOnClickListener(listener);
    }
}
