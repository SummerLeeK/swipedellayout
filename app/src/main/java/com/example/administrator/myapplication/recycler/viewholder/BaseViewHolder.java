package com.example.administrator.myapplication.recycler.viewholder;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/6/27 0027.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }


    public void setText(@IdRes int res, int msg) {

        ((TextView) itemView.findViewById(res)).setText(msg);
    }

    public void setText(int res, String msg) {
        ((TextView) itemView.findViewById(res)).setText(msg);
    }


    public void setImageResource(@IdRes int res, @DrawableRes int img) {
        ((ImageView) itemView.findViewById(res)).setImageResource(img);
    }

    public void setImageBitmap(@IdRes int res, Bitmap bitmap) {
        ((ImageView) itemView.findViewById(res)).setImageBitmap(bitmap);
    }


    public void setItemViewClicklistener(@IdRes int res, View.OnClickListener listener) {
        itemView.findViewById(res).setOnClickListener(listener);
    }

    public Context getContext() {
        return itemView.getContext();
    }
}
