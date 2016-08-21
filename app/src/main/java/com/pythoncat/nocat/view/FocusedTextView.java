package com.pythoncat.nocat.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by pythonCat on 2016/8/21 0021.
 *
 * @author pythonCat
 *         假装一直有焦点的textview
 */
public class FocusedTextView extends TextView {
    public FocusedTextView(Context context) {
        super(context);
    }

    public FocusedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FocusedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public FocusedTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    @Override
    protected void onFocusChanged(boolean focused, int direction,
                                  Rect previouslyFocusedRect) {
        if (focused) {
            super.onFocusChanged(true, direction, previouslyFocusedRect);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean focused) {
        if (focused) {
            super.onWindowFocusChanged(true);
        }
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
