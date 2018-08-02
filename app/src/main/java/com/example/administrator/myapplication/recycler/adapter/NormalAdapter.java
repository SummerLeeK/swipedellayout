package com.example.administrator.myapplication.recycler.adapter;

import android.view.View;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.NormalBean;
import com.example.administrator.myapplication.recycler.viewholder.BaseViewHolder;

/**
 * Created by Administrator on 2018/7/5 0005.
 */

public class NormalAdapter extends BaseRecyclerAdapter<NormalBean, BaseViewHolder> {
    public NormalAdapter(int layout) {
        super(layout);
    }

    @Override
    public void convert(final BaseViewHolder holder, NormalBean item) {


        holder.setItemViewClicklistener(R.id.tv1, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.getContext(), holder.getLayoutPosition() + "1", Toast.LENGTH_SHORT).show();
            }
        });

        holder.setItemViewClicklistener(R.id.tv2, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.getContext(), holder.getLayoutPosition() + "2", Toast.LENGTH_SHORT).show();
            }
        });
        holder.setItemViewClicklistener(R.id.tv3, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.getContext(), holder.getLayoutPosition() + "3", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
