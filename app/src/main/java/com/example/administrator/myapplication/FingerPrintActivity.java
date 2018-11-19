package com.example.administrator.myapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback;
import android.support.v4.os.CancellationSignal;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.myapplication.fragment.FingerDialogFragment;
import com.example.administrator.myapplication.utils.CryptoObjectHelper;
import com.example.administrator.myapplication.utils.LogUtils;

/**
 * Created by Administrator on 2018/9/10 0010.
 */

public class FingerPrintActivity extends AppCompatActivity implements View.OnClickListener {

    private Button start;
    private Button cancel;


    private FingerDialogFragment dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_finger_print);

        start = findViewById(R.id.start);
        cancel = findViewById(R.id.cancel);


        start.setOnClickListener(this);
        cancel.setOnClickListener(this);


        dialog = new FingerDialogFragment();


        LogUtils.e(Build.ID);
        LogUtils.e(Build.DEVICE);
        LogUtils.e(Build.DISPLAY);
        LogUtils.e(Build.BRAND);
        LogUtils.e(Build.HARDWARE);
        LogUtils.e(Build.HOST);
        LogUtils.e(Build.MODEL);
        LogUtils.e(Build.PRODUCT);
        LogUtils.e(Build.TYPE);
        LogUtils.e(Build.MANUFACTURER);
        LogUtils.e(Build.TAGS);
        LogUtils.e(Build.USER);
        LogUtils.e(String.valueOf(Build.TIME));

        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    dialog.show(getFragmentManager(), "finger");
                }
                break;
            case R.id.cancel:

                break;
        }
    }


}
