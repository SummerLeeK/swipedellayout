package com.example.administrator.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.example.administrator.myapplication.R;

/**
 * 聊天气泡
 * Created by Administrator on 2018/9/3 0003.
 */

public class BubbleLayout extends RelativeLayout {

    private float radius;

    private int arrowDirection;

    private float arrowPosition;

    private int width;

    private int height;

    private Path rectPath, arrawPath;

    private Paint paint;

    private RectF rect;

    private float arrowWidth = 20;
    private float arrowHeight = 20;

    private int arrowType;

    private int left, top, right, bottom;

    private float contentWidth, contentHeight;

    private int padding = 10;

    public BubbleLayout(Context context) {
        this(context, null);
    }

    public BubbleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BubbleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BubbleLayout);

        arrowPosition = a.getDimension(R.styleable.BubbleLayout_arrowposition, 10);
        arrowDirection = a.getInteger(R.styleable.BubbleLayout_arrowdirection, 1);
        radius = a.getDimension(R.styleable.BubbleLayout_radius, 5);
        arrowType = a.getInteger(R.styleable.BubbleLayout_arrowtype, 0);
        a.recycle();

        initPaint();

    }

    private void initPaint() {

        paint = new Paint();

        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);

        arrawPath = new Path();
        rectPath = new Path();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        contentWidth = width - radius * 2;
        contentHeight = height - radius * 2;
        switch (arrowDirection) {
            case 1:
//                top

                left = 0;
                top = (int) (height - arrowWidth);
                right = width;
                bottom = height;
                break;
            case 2:
//                right
                left = 0;
                top = 0;
                right = (int) (width - arrowWidth);
                bottom = height;
                break;
            case 3:
//                bottom
                left = 0;
                top = 0;
                right = width;
                bottom = (int) (height - arrowWidth);
                break;
            case 4:
//                left
                left = (int) arrowWidth;
                top = 0;
                right = width;
                bottom = height;
                break;
        }
        rect = new RectF(left, top, right, bottom);

        canvas.drawRoundRect(rect, radius, radius, paint);
        if (arrowType == 0) {

            drawTriangleArrow();
        } else {

        }

    }

    private void drawTriangleArrow() {
        float startX = 0, startY = 0;
        float centerX = 0, centerY = 0;
        float endX = 0, endY = 0;
        switch (arrowDirection) {
            case 1:
//                top
                startX = (left + radius + arrowPosition);
                startY = top;
                centerX = startX + arrowWidth / 2;
                centerY = startY - arrowHeight;

                endX = startX + arrowWidth;
                endY = startY;

                break;
            case 2:
//                right
                startX = right;
                startY = (top + radius + arrowPosition);

                centerX = startX + arrowHeight;
                centerY = startY + arrowWidth / 2;

                endX = startX;
                endY = startY + arrowWidth;
                break;
            case 3:
//                bottom
                startX = (left + radius + arrowPosition);
                startY = bottom - arrowHeight;


                break;
            case 4:
                startX = left;
                startY = top + radius + arrowPosition;

                break;
        }
        arrawPath.moveTo(startX, startY);
        arrawPath.lineTo(centerX, centerY);
        arrawPath.lineTo(endX, endY);


    }


    private void drawArcArrow() {

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        height = h;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        l += padding;
        t += padding;
        r -= padding;
        bottom -= padding;
        super.onLayout(changed, l, t, r, b);
    }
}
