package com.example.administrator.myapplication.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import com.example.administrator.myapplication.utils.LogUtils;

public class LifecycleListener implements LifecycleObserver {

    public static final String TAG = "LifecycleListener";

    private String from = "";

    public LifecycleListener(String from) {
        this.from = from;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void create() {
        LogUtils.e(TAG, from + "\tLifecycle.Event.ON_CREATE");
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void start() {
        LogUtils.e(TAG, from + "\tLifecycle.Event.ON_START");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void pause() {
        LogUtils.e(TAG, from + "\tLifecycle.Event.ON_PAUSE");
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void stop() {
        LogUtils.e(TAG, from + "\tLifecycle.Event.ON_STOP");
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void resume() {
        LogUtils.e(TAG, from + "\tLifecycle.Event.ON_RESUME");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void destory() {
        LogUtils.e(TAG, from + "\tLifecycle.Event.ON_DESTROY");
    }
}