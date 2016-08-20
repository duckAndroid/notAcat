package com.pythoncat.nocat.viewHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pythoncat.nocat.R;

/**
 * Created by pythonCat on 2016/8/14 0014.
 * app升级提醒对话框
 */
public class AppDialogHelper {

    private final Activity a;
    private View root;
    public ProgressBar pbProgress;
    private TextView tvTitle;
    private Button btnIsee;
    private AlertDialog appDialog;

    /**
     *
     * @param a context
     * @param listener 后台升级按钮点击监听器
     */
    public AppDialogHelper(Activity a, View.OnClickListener listener) {
        init(a, listener);
        this.a = a;
    }

    private void init(Activity a, View.OnClickListener listener) {
        root = View.inflate(a, R.layout.view_update_dialog, null);
        pbProgress = (ProgressBar) root.findViewById(R.id.app_update_pb);
        tvTitle = (TextView) root.findViewById(R.id.app_update_title);
        btnIsee = (Button) root.findViewById(R.id.app_update_btn);
        btnIsee.setOnClickListener(listener);
        pbProgress.setMax(100);
    }

    public AppDialogHelper showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(a)
                .setView(root);
        if (appDialog == null) {
            appDialog = builder.create();
        }
        appDialog.show();
        return this;
    }
    public void cancel() {
        if (appDialog != null) {
            appDialog.cancel();
        }
    }

    public void setTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }
}
