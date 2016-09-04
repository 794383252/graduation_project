package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/1.
 */
public class zhuye_fragment extends Fragment {

    private TextView zhuye_textview;
    private ListView zhuye_listview;
    private List<String> list;
    private View view;
    ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragement_zhuye, container, false);
        init();
        return view;
    }

    public void init() {
        zhuye_textview = (TextView) view.findViewById(R.id.zhuye_textview);
        zhuye_listview = (ListView) view.findViewById(R.id.zhuye_listview);
    }

}
