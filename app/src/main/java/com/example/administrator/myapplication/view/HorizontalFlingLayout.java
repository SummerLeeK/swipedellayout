package com.example.administrator.myapplication.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.example.administrator.myapplication.utils.LogUtils;

/**
 * 滑动item的viewgroup
 * Created by Administrator on 2018/7/31 0031.
 */

public class HorizontalFlingLayout extends ViewGroup {

    private Scroller scroller;

    private int touchSlop;

    private int leftBorder;
    private int rightBorder;

    private int width;
    private int height;

    private int offset;

    private int bottom;

    private int scrollOffset = 0;

    private int remainSpace;

    private final static int TOUCH_STATE_REST = 0;
    private final static int TOUCH_STATE_SCROLLING = 1;
    private int mTouchState = TOUCH_STATE_REST;

    private static HorizontalFlingLayout viewCache;

    private ValueAnimator cancelAnim;

    public static HorizontalFlingLayout getViewCache() {
        return viewCache;
    }

    public HorizontalFlingLayout(Context context) {
        super(context);
        init(context);
    }

    public HorizontalFlingLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HorizontalFlingLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context) {
        scroller = new Scroller(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        touchSlop = configuration.getScaledTouchSlop();


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = 0;


        int heightSize = 0;


        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            if (child.getVisibility() != View.GONE) {
                measureChild(child, widthMeasureSpec, heightMeasureSpec);

                widthSize += child.getMeasuredWidth();

                heightSize = Math.max(heightSize, child.getMeasuredHeight());
            }

        }

        widthSize += getPaddingLeft() + getPaddingRight();
        heightSize += getPaddingTop() + getPaddingBottom();

        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();

            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);

                int left = offset;
                int right = offset + child.getMeasuredWidth();

                bottom = Math.max(bottom, child.getMeasuredHeight());
                child.layout(left, 0, right, bottom);
                offset = right;

                if (i == 0) {
                    width = child.getWidth();
                }
            }
        }
        leftBorder = getChildAt(0).getLeft();
        rightBorder = offset;
        remainSpace = getRemainSpace();
//        LogUtils.e("leftBorder\t" + leftBorder + "\trightBorder\t" + rightBorder);

    }

    private float lastMove = 0f;
    private float downX;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        LogUtils.e("onInterceptTouchEvent");
        final float x = ev.getX();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                lastMove = x;
                downX = x;
                mTouchState = scroller.isFinished() ? TOUCH_STATE_REST
                        : TOUCH_STATE_SCROLLING;
                break;
            case MotionEvent.ACTION_MOVE:
                final int yDiff = (int) Math.abs(x - downX);
                boolean yMoved = yDiff > touchSlop;
                // 判断是否是移动
                if (yMoved) {
                    mTouchState = TOUCH_STATE_SCROLLING;
                }
                break;
            case MotionEvent.ACTION_UP:
                mTouchState = TOUCH_STATE_REST;
                break;
        }

        if (mTouchState != TOUCH_STATE_REST) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return mTouchState != TOUCH_STATE_REST;
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        return false;
//    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        LogUtils.e(event.getAction() + "------------onTouchEvent");
        if (viewCache != null && viewCache != HorizontalFlingLayout.this) {
            viewCache.smoothClose();
        }
        viewCache = HorizontalFlingLayout.this;
        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                lastMove = event.getX();
//                LogUtils.e("onTouchEvent-----ACTION_DOWN \t" + lastMove);
//                break;
            case MotionEvent.ACTION_MOVE:

                float xMove = event.getX();


                int scrollX = (int) (lastMove - xMove);
//                LogUtils.e("onTouchEvent-----ACTION_MOVE +" + lastMove + "\t" + xMove + "\tremainSpace\t" + remainSpace);
                int moveNeed = 0;
                if (scrollOffset + scrollX < leftBorder) {
                    moveNeed = -scrollOffset;

                } else if (scrollOffset + scrollX > remainSpace) {
                    moveNeed = remainSpace - scrollOffset;

                } else {
                    moveNeed = scrollX;

                }


                scrollOffset += moveNeed;
                scrollBy(moveNeed, 0);
                lastMove = xMove;
//                LogUtils.e("onTouchEvent-----ACTION_MOVE moveNeed\t" + moveNeed + "\tremainSpace\t" + remainSpace);
//                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                smoothToView();
                break;
            default:
                break;
        }

        return true;
    }


//    @Override
//    public void computeScroll() {
//        if (scroller.computeScrollOffset()) {
//            this.scrollTo(scroller.getCurrX(), scroller.getCurrY());
//            postInvalidate();
//        }
//    }


    /**
     *
     */
    public void smoothClose() {

        cancelAnim = ValueAnimator.ofInt(getScrollX(), 0);
        cancelAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scrollTo((Integer) animation.getAnimatedValue(), 0);
            }
        });


        cancelAnim.setInterpolator(new AccelerateDecelerateInterpolator());
//        LogUtils.e("smoothClose\tscrollOffset\t" + scrollOffset);
//        scroller.startScroll(scrollOffset, 0, -scrollOffset, 0, 500);
        cancelAnim.setDuration(300).start();
        scrollOffset = 0;
        viewCache = null;
    }

    public void smoothToView() {

        int currentX = scrollOffset;
        int needScrollX = 0;
        int itemRightOffset = 0;
        int itemLeftOffset = 0;
        for (int i = 1; i < getChildCount(); i++) {
            View itemView = getChildAt(i);
            itemRightOffset = itemView.getRight();
//                    LogUtils.e("currentX" + currentX + "i\t" + i + "\titemRightOffset\t" + itemRightOffset);
            if (currentX + width > itemLeftOffset && currentX + width < itemRightOffset) {
                int itemWidth = itemRightOffset - itemLeftOffset;
                if (currentX < itemWidth / 2) {
                    needScrollX = -currentX;
                } else {
                    needScrollX = itemWidth - currentX;
                }


                break;
            }
            itemLeftOffset = itemRightOffset;
        }
//                needScrollX += currentX + getWidth();
        if (needScrollX + currentX > remainSpace) {
            needScrollX = remainSpace - currentX;
        }
        scroller.startScroll(currentX, 0, needScrollX, 0, 500);
        scrollOffset += needScrollX;
//                LogUtils.e("onTouchEvent-----ACTION_UP \t" + scrollOffset + "\tneedScrollX\t" + needScrollX + "\t" + currentX);
        invalidate();
    }


    @Override
    protected void onDetachedFromWindow() {

        if (this == viewCache) {
            viewCache.smoothClose();
            viewCache = null;
        }

        super.onDetachedFromWindow();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            // 返回当前滚动X方向的偏移
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }

    private int getRemainSpace() {
        return rightBorder - width;
    }
}
