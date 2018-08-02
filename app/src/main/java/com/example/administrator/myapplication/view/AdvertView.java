package com.example.administrator.myapplication.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2018/7/12 0012.
 */

public class AdvertView extends ViewGroup {


    private RecyclerView recycler;

    private View root;

    public AdvertView(Context context) {
        this(context, null);
    }

    public AdvertView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdvertView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        root = LayoutInflater.from(context).inflate(R.layout.advert_view, null);
        recycler = root.findViewById(R.id.recycler);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }


    public abstract class AdvertRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
