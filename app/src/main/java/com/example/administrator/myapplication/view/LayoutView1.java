package com.example.administrator.myapplication.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.administrator.myapplication.utils.LogUtils;

/**
 * Created by Administrator on 2018/8/1 0001.
 */

public class LayoutView1 extends LinearLayout {

    public LayoutView1(Context context) {
        super(context);
    }

    public LayoutView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("LayoutView1 ----onInterceptTouchEvent ------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e("LayoutView1 ----onInterceptTouchEvent ------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("LayoutView1 ----onInterceptTouchEvent ------ACTION_UP");
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("LayoutView1 ----onTouchEvent ------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e("LayoutView1 ----onTouchEvent ------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("LayoutView1 ----onTouchEvent ------ACTION_UP");
                break;
        }

        return super.onTouchEvent(event);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("LayoutView1 ----dispatchTouchEvent ------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e("LayoutView1 ----dispatchTouchEvent ------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("LayoutView1 ----dispatchTouchEvent ------ACTION_UP");
                break;
        }

        return super.dispatchTouchEvent(ev);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}