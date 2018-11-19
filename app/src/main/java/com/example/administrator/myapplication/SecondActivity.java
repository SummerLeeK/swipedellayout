package com.example.administrator.myapplication;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.bean.User;
import com.example.administrator.myapplication.viewmodel.UserViewModel;

/**
 * Created by Administrator on 2018/7/12 0012.
 */

public class SecondActivity extends AppCompatActivity implements Observer<User>, View.OnClickListener {

    TextView changeTv;
    private UserViewModel userViewModel;

    PopupWindow popupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_second);


        changeTv = findViewById(R.id.change);

        userViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(UserViewModel.class);

        User user = new User("aa", 10, "aaa");

        userViewModel.setLiveData(user);


        userViewModel.getMutableLiveData().observe(this, this);


        changeTv.setOnClickListener(this);

        popupWindow = new PopupWindow(this);
        popupWindow.setOutsideTouchable(true);


        View view = LayoutInflater.from(this).inflate(R.layout.act_second, null);

        popupWindow.setContentView(view);

        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onChanged(@Nullable User user) {
        Toast.makeText(getApplicationContext(), user.toString(), Toast.LENGTH_SHORT).show();


    }


    @Override
    public void onClick(View v) {
        userViewModel.getMutableLiveData().postValue(new User("bb", 11, "bbb"));
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }
}
