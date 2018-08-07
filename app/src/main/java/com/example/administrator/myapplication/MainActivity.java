package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.bean.NormalBean;
import com.example.administrator.myapplication.recycler.adapter.NormalAdapter;
import com.example.administrator.myapplication.recycler.layoutmanager.MyLayoutManager;
import com.example.administrator.myapplication.statictest.StaticTest;
import com.example.administrator.myapplication.utils.LogUtils;
import com.example.administrator.myapplication.view.HorizontalFlingLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;

    private NormalAdapter adapter;

    private List<NormalBean> datas;


    private TextView tv1, tv2, tv3;


    private View view;

    private float downX, downY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        tv1 = findViewById(R.id.tv1);
//        tv2 = findViewById(R.id.tv2);
//        tv3 = findViewById(R.id.tv3);
//        view = findViewById(R.id.view);
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
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        LogUtils.e("densityDpi=" + displayMetrics.densityDpi + "\t" + displayMetrics.toString());

//        recycler.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                HorizontalFlingLayout horizontalFlingLayout = HorizontalFlingLayout.getViewCache();
//
//                if (horizontalFlingLayout != null) {
//                    horizontalFlingLayout.smoothClose();
//                }
//                return false;
//            }
//        });

//        tv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//        tv2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//        tv3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT).show();
//            }
//        });

//
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        downX = event.getX();
//                        downY = event.getY();
//                        break;
//
//                    case MotionEvent.ACTION_MOVE:
//                        LogUtils.e((int) event.getX() + "\t" + (int) event.getY());
//                        view.scrollTo((int) event.getX(), (int) event.getY());
//                        break;
//
//                    case MotionEvent.ACTION_UP:
//                        break;
//                }
//
//                return true;
//            }
//        });

    }
}
