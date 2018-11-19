package com.example.administrator.myapplication;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication.bean.NormalBean;
import com.example.administrator.myapplication.fragment.MyBottomSheetDialogFragment;
import com.example.administrator.myapplication.recycler.adapter.NormalAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class BottomSheetActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bottomsheet, bottomsheetdialog, bottomsheetdialogfragment;


    private BottomSheetBehavior bottomSheetBehavior;

    private BottomSheetDialog bottomSheetDialog;

    private MyBottomSheetDialogFragment bottomSheetDialogFragment;

    private RecyclerView recycler;

    private NormalAdapter adapter;

    private List<NormalBean> datas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);

        bottomsheet = findViewById(R.id.bottomsheet);
        bottomsheetdialog = findViewById(R.id.bottomsheetdialog);
        bottomsheetdialogfragment = findViewById(R.id.bottomsheetdialogfragment);

        bottomsheet.setOnClickListener(this);
        bottomsheetdialog.setOnClickListener(this);
        bottomsheetdialogfragment.setOnClickListener(this);

        View bottom = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottom);

        bottomSheetDialog = new BottomSheetDialog(this);

        bottomSheetDialog.setContentView(R.layout.act_second);

        bottomSheetDialogFragment = new MyBottomSheetDialogFragment();


        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NormalAdapter(R.layout.recycler_normal_item);

        recycler.setAdapter(adapter);


        datas = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            NormalBean bean = new NormalBean("xxxxxxxxx" + i);
            datas.add(bean);
        }


        adapter.setData(datas);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottomsheet:
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                break;
            case R.id.bottomsheetdialog:
                bottomSheetDialog.show();
                break;
            case R.id.bottomsheetdialogfragment:
                bottomSheetDialogFragment.show(getSupportFragmentManager(), "aaa");
                break;
        }
    }
}
