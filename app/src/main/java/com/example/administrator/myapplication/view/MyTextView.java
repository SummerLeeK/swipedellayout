package com.example.administrator.myapplication.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.example.administrator.myapplication.utils.LogUtils;

/**
 * Created by Administrator on 2018/8/1 0001.
 */


public class MyTextView extends TextView {

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("MyTextView ----onTouchEvent ------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e("MyTextView ----onTouchEvent ------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("MyTextView ----onTouchEvent ------ACTION_UP");
                break;
        }

        return super.onTouchEvent(event);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("MyTextView ----dispatchTouchEvent ------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e("MyTextView ----dispatchTouchEvent ------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("MyTextView ----dispatchTouchEvent ------ACTION_UP");
                break;
        }

        return super.dispatchTouchEvent(ev);
    }
}
