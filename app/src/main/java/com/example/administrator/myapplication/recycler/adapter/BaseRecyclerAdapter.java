package com.example.administrator.myapplication.recycler.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.recycler.viewholder.BaseViewHolder;
import com.example.administrator.myapplication.utils.LogUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/6/27 0027.
 */

public abstract class BaseRecyclerAdapter<T, M extends BaseViewHolder> extends RecyclerView.Adapter<M> {

    private List<T> data;

    private int count = 0;

    @LayoutRes
    private int layout;

    private LayoutInflater layoutInflater;

    public BaseRecyclerAdapter(List<T> data) {
        this.data = data;
    }

    public BaseRecyclerAdapter(int layout) {
        this.layout = layout;
    }


    public BaseRecyclerAdapter(List<T> data, int layout) {
        this.data = data;
        this.layout = layout;
    }


    public void setData(List<T> data) {
        this.data = data == null ? new ArrayList<T>() : data;
        notifyDataSetChanged();
    }


    public void addData(T datas) {
        this.data.add(datas);
        notifyItemInserted(this.data.size());
    }


    public void addData(List<T> datas) {
        data.addAll(datas);
        notifyItemRangeInserted(this.data.size() - datas.size(), datas.size());
    }


    public void clear() {
        data.clear();
    }

    @Override
    public M onCreateViewHolder(ViewGroup parent, int viewType) {
        ++count;
//        LogUtils.e("" + count);
        layoutInflater = LayoutInflater.from(parent.getContext());
        return createBaseViewHolder(getItemView(layout, parent));
    }


    protected View getItemView(@LayoutRes int layoutResId, ViewGroup parent) {
        return layoutInflater.inflate(layoutResId, parent, false);
    }

    /**
     * if you want to use subclass of BaseViewHolder in the adapter,
     * you must override the method to create new ViewHolder.
     *
     * @param view view
     * @return new ViewHolder
     */
    @SuppressWarnings("unchecked")
    protected M createBaseViewHolder(View view) {
        Class temp = getClass();
        Class z = null;
        while (z == null && null != temp) {
            z = getInstancedGenericKClass(temp);
            temp = temp.getSuperclass();
        }
        M k;
        // 泛型擦除会导致z为null
        if (z == null) {
            k = (M) new BaseViewHolder(view);
        } else {
            k = createGenericKInstance(z, view);
        }
        return k != null ? k : (M) new BaseViewHolder(view);
    }

    /**
     * try to create Generic M instance
     *
     * @param z
     * @param view
     * @return
     */
    @SuppressWarnings("unchecked")
    private M createGenericKInstance(Class z, View view) {
        try {
            Constructor constructor;
            // inner and unstatic class
            if (z.isMemberClass() && !Modifier.isStatic(z.getModifiers())) {
                constructor = z.getDeclaredConstructor(getClass(), View.class);
                constructor.setAccessible(true);
                return (M) constructor.newInstance(this, view);
            } else {
                constructor = z.getDeclaredConstructor(View.class);
                constructor.setAccessible(true);
                return (M) constructor.newInstance(view);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get generic parameter M
     *
     * @param z
     * @return
     */
    private Class getInstancedGenericKClass(Class z) {
        Type type = z.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();
            for (Type temp : types) {
                if (temp instanceof Class) {
                    Class tempClass = (Class) temp;
                    if (BaseViewHolder.class.isAssignableFrom(tempClass)) {
                        return tempClass;
                    }
                } else if (temp instanceof ParameterizedType) {
                    Type rawType = ((ParameterizedType) temp).getRawType();
                    if (rawType instanceof Class && BaseViewHolder.class.isAssignableFrom((Class<?>) rawType)) {
                        return (Class<?>) rawType;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(M holder, int position) {

        convert(holder, data.get(position));

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    public abstract void convert(M holder, T item);


}
