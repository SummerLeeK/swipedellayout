package com.example.administrator.myapplication.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.administrator.myapplication.bean.User;

import java.util.List;

public class UserViewModel extends ViewModel {

    private MutableLiveData<User> mutableLiveData = new MutableLiveData<>();


    public MutableLiveData<User> getMutableLiveData() {
        return mutableLiveData;
    }

    public void setLiveData(User liveData) {
        this.mutableLiveData.setValue(liveData);
    }
}
