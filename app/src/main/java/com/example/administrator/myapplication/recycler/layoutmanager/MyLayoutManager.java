package com.example.administrator.myapplication.recycler.layoutmanager;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.Vector;


/**
 * Created by Administrator on 2018/6/27 0027.
 */

public class MyLayoutManager extends RecyclerView.LayoutManager {

    private Context context;

    private SparseArray<Rect> sparseArray = new SparseArray<>();
    private SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();

    private int totalHeight = 0;
    private int verOffset = 0;

    public MyLayoutManager(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() <= 0) return;

        if (state.isPreLayout()) return;

        detachAndScrapAttachedViews(recycler);


        int veroffset = 0;
        totalHeight = 0;

        for (int i = 0; i < getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);

            addView(view);

            measureChildWithMargins(view, 0, 0);

            int width = getDecoratedMeasuredWidth(view);
            int height = getDecoratedMeasuredHeight(view);

            totalHeight += height;

            Rect frame = sparseArray.get(i);

            if (frame == null) {
                frame = new Rect();
            }
            frame.set(0, veroffset, width, height + veroffset);

            sparseArray.put(i, frame);
            sparseBooleanArray.put(i, false);
            veroffset += height;

        }

        totalHeight = Math.max(totalHeight, getVerticalSpace());

        recycleAndFillItems(recycler, state);

    }

    private void recycleAndFillItems(RecyclerView.Recycler recycler, RecyclerView.State state) {

        if (state.isPreLayout()) return;

        Rect displayFrame = new Rect(0, verOffset, getHorizontalSpace(), verOffset + getVerticalSpace());


        Rect childFrame = new Rect();

        for (int i = 0; i < getChildCount(); i++) {

            View view = getChildAt(i);

            childFrame.top = getDecoratedTop(view);
            childFrame.bottom = getDecoratedBottom(view);
            childFrame.left = getDecoratedLeft(view);
            childFrame.right = getDecoratedRight(view);

            if (!Rect.intersects(displayFrame, childFrame)) {

                removeAndRecycleView(view, recycler);
            }


        }

        for (int i = 0; i < getItemCount(); i++) {
            if (Rect.intersects(displayFrame, sparseArray.get(i))) {
                View scrap = getChildAt(i);
                measureChildWithMargins(scrap, 0, 0);

                addView(scrap);

                Rect frame = sparseArray.get(i);

                layoutDecorated(scrap, frame.left, frame.top - verOffset, frame.right, frame.bottom - verOffset);
            }
        }


    }


    private void calculateChildrenSite(RecyclerView.Recycler recycler) {


        for (int i = 0; i < getItemCount(); i++) {

            View view = recycler.getViewForPosition(i);

            addView(view);
            measureChildWithMargins(view, 0, 0);

            int width = getDecoratedMeasuredWidth(view);
            int height = getDecoratedMeasuredHeight(view);

            Rect rect = new Rect();

            calculateItemDecorationsForChild(view, rect);

            layoutDecorated(view, 0, totalHeight, width, totalHeight + height);

            totalHeight += height;
        }
    }


    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {


        int moveDy = dy;
        if (dy + verOffset < 0) {
//           顶部

            moveDy = -verOffset;
        } else if (dy + verOffset > totalHeight - getVerticalSpace()) {
            moveDy = totalHeight - getVerticalSpace() - verOffset;
        }

        verOffset += moveDy;

        offsetChildrenVertical(-moveDy);


        return moveDy;
    }


    private int getVerticalSpace() {
        return getHeight() - getPaddingBottom() - getPaddingTop();
    }

    private int getHorizontalSpace() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }
}
