package com.pythoncat.nocat.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pythoncat.nocat.R;

/**
 * Created by pythonCat on 2016/8/14 0014.
 *
 * @author pythonCat
 */
public class AppDialog extends AppCompatDialog {

    private TextView tvTitle;
    private Button btnIsee;
    private ProgressBar pb;

    protected AppDialog(Context context) {
        super(context);
    }

    protected AppDialog(Context context, int theme) {
        super(context, theme);
    }

    protected AppDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_update_dialog);
        init();
    }

    private void init() {
        tvTitle = (TextView) findViewById(R.id.app_update_title);
        btnIsee = (Button) findViewById(R.id.app_update_btn);
        pb = (ProgressBar) findViewById(R.id.app_update_pb);
        assert pb != null;
        pb.setMax(100);
    }

    public void setTitle(CharSequence ch) {
        if (tvTitle != null) {
            tvTitle.setText(ch);
        }
    }

    public void setTitle(int ch) {
        if (tvTitle != null) {
            tvTitle.setText(ch);
        }
    }

    public void setButtonText(CharSequence ch) {
        if (btnIsee != null) {
            btnIsee.setText(ch);
        }
    }

    public void setButtonText(int ch) {
        if (btnIsee != null) {
            btnIsee.setText(ch);
        }
    }

    public void setButtonOnClick(View.OnClickListener vo) {
        if (btnIsee != null) {
            btnIsee.setOnClickListener(vo);
        }
    }

    /**
     * @param progress 0-100
     */
    public void setProgress(int progress) {
        pb.setProgress(progress);
    }

}
