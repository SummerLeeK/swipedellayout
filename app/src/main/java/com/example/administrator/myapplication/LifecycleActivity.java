package com.example.administrator.myapplication;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.myapplication.lifecycle.LifecycleListener;

public class LifecycleActivity extends AppCompatActivity {

    LifecycleListener lifecycleListener;

    TextView ss;
    Handler

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_touch);
        ss = findViewById(R.id.src_over);


        ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LifecycleActivity.this, SecondActivity.class);

                startActivity(intent);
            }
        });

        lifecycleListener = new LifecycleListener("LifecycleActivity");
        getLifecycle().addObserver(lifecycleListener);

        ss.setText(getStrFromJni());

        float xx = 1 * 1000f;

        long mill = System.currentTimeMillis();
        Log.e("LifecycleActivity", String.valueOf(mill + xx) + "\t" + mill + "\t" + xx);

    }

    static {
        System.loadLibrary("demo");
    }

    public native String getStrFromJni();


}
