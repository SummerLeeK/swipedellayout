package com.example.administrator.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.NormalBean;
import com.example.administrator.myapplication.recycler.adapter.NormalAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {


    private RecyclerView recycler;

    private NormalAdapter adapter;

    private List<NormalBean> datas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_main, null);

        recycler = root.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NormalAdapter(R.layout.recycler_normal_item);

        recycler.setAdapter(adapter);


        datas = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            NormalBean bean = new NormalBean("xxxxxxxxx" + i);
            datas.add(bean);
        }


        adapter.setData(datas);

        return root;
    }
}
