package com.pythoncat.nocat.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.pythoncat.nocat.R;
import com.pythoncat.nocat.bean.Tool;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by pythonCat on 2016/8/21 0021.
 *
 * @author pythonCat
 */
public class ToolsAdapter extends CommonAdapter<Tool> {
    public ToolsAdapter(Context context, List<Tool> datas) {
        super(context, R.layout.view_tools_item, datas);
    }

    @Override
    protected void convert(ViewHolder helper, Tool item, int position) {
        ImageView ivIcon = helper.getView(R.id.tools_item_icon);
        TextView tvTitle = helper.getView(R.id.tools_item_title);
        ivIcon.setImageResource(item.drawableId);
        tvTitle.setText(item.title);
        helper.getView(R.id.tools_root).setOnClickListener(item.clickListener);
    }
}
