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
public class LayoutView2 extends LinearLayout {

    public LayoutView2(Context context) {
        super(context);
    }

    public LayoutView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LayoutView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("LayoutView2 ----onInterceptTouchEvent ------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e("LayoutView2 ----onInterceptTouchEvent ------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("LayoutView2 ----onInterceptTouchEvent ------ACTION_UP");
                break;
        }

        return true;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("LayoutView2 ----onTouchEvent ------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e("LayoutView2 ----onTouchEvent ------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("LayoutView2 ----onTouchEvent ------ACTION_UP");
                break;
        }

        return true;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("LayoutView2 ----dispatchTouchEvent ------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e("LayoutView2 ----dispatchTouchEvent ------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("LayoutView2 ----dispatchTouchEvent ------ACTION_UP");
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
