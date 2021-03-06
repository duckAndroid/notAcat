package com.pythoncat.nocat.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.apkfuns.logutils.LogUtils;
import com.pythoncat.nocat.Configs;
import com.pythoncat.nocat.R;
import com.pythoncat.nocat.activity.SettingsActivity;
import com.pythoncat.nocat.adapter.ToolsAdapter;
import com.pythoncat.nocat.base.BaseFragment;
import com.pythoncat.nocat.bean.Tool;
import com.pythoncat.proxy.App;
import com.pythoncat.proxy.util.ToastHelper;

import java.util.LinkedList;
import java.util.List;


/**
 * @author pythonCat
 *         工具箱
 */
public class ToolsFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ToolsFragment() {
        // Required empty public constructor
    }

    public static ToolsFragment newInstance(String param1, String param2) {
        ToolsFragment fragment = new ToolsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tools, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        GridView guideLayout = (GridView) view.findViewById(R.id.tools_guide_view);
        guideLayout.setNumColumns(3);
        List<Tool> tools = loadTools();
        ToolsAdapter adapter = new ToolsAdapter(getContext(), tools);
        guideLayout.setAdapter(adapter);

    }

    private List<Tool> loadTools() {
        List<Tool> tools = new LinkedList<>();
        {
            Tool ob = new Tool();
            ob.clickListener = v -> {
                ToastHelper.showShort("dian w le");
                LogUtils.e("dian w le");
            };
            ob.drawableId = R.drawable.cat01;
            ob.title = App.getString(R.string.mobile_security);
            tools.add(ob);
        }
        {
            Tool ob = new Tool();
            ob.clickListener = v -> {
                LogUtils.e("dian w le");
                ToastHelper.showShort("dian w le");
            };
            ob.drawableId = R.drawable.cat02;
            ob.title = "通讯卫士";
            tools.add(ob);
        }
        {
            Tool ob = new Tool();
            ob.clickListener = v -> {
                LogUtils.e("dian w le");
                ToastHelper.showShort("dian w le");
            };
            ob.drawableId = R.drawable.cat03;
            ob.title = "软件管理";
            tools.add(ob);
        }
        {
            Tool ob = new Tool();
            ob.clickListener = v -> {
                LogUtils.e("dian w le");
                ToastHelper.showShort("dian w le");
            };
            ob.drawableId = R.drawable.cat04;
            ob.title = "进程管理";
            tools.add(ob);
        }
        {
            Tool ob = new Tool();
            ob.clickListener = v -> {
                ToastHelper.showShort("dian w le");
                LogUtils.e("dian w le");
            };
            ob.drawableId = R.drawable.cat05;
            ob.title = "流量统计";
            tools.add(ob);
        }
        {
            Tool ob = new Tool();
            ob.clickListener = v -> {
                ToastHelper.showShort("dian w le");
                LogUtils.e("dian w le");
            };
            ob.drawableId = R.drawable.cat06;
            ob.title = "手机杀毒";
            tools.add(ob);
        }
        {
            Tool ob = new Tool();
            ob.clickListener = v -> {
                ToastHelper.showShort("dian w le");
                LogUtils.e("dian w le");
            };
            ob.drawableId = R.drawable.cat06;
            ob.title = "缓存清理";
            tools.add(ob);
        }
        {
            Tool ob = new Tool();
            ob.clickListener = v -> {
                ToastHelper.showShort("dian w le");
                LogUtils.e("dian w le");
            };
            ob.drawableId = R.drawable.cat06;
            ob.title = "高级工具";
            tools.add(ob);
        }
        {
            Tool ob = new Tool();
            ob.clickListener = v ->
                    startActivity(new Intent(get(), SettingsActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            .putExtra(Configs.itemTitleKey, ob.title));
            ob.drawableId = R.drawable.cat06;
            ob.title = "设置中心";
            tools.add(ob);
        }

        return tools;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
