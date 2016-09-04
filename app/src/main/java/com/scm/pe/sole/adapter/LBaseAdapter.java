package com.scm.pe.sole.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class LBaseAdapter<T> extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<T> mDataObjects; // our generic object list
    private int mViewId;
    private int mCurrentPositon;

    public static class ViewHolder<T> {
        public T data;
    }

    public static abstract class OnClickListener<T> implements View.OnClickListener {
        private ViewHolder<T> mViewHolder;

        public OnClickListener(ViewHolder<T> holder) {
            mViewHolder = holder;
        }

        public void onClick(View v) {
            onClick(v, mViewHolder);
        }

        public abstract void onClick(View v, ViewHolder<T> viewHolder);
    }

    ;

    public static abstract class OnLongClickListener<T> implements View.OnLongClickListener {
        private ViewHolder<T> mViewHolder;

        public OnLongClickListener(ViewHolder<T> holder) {
            mViewHolder = holder;
        }

        public boolean onLongClick(View v) {
            onLongClick(v, mViewHolder);
            return true;
        }

        public abstract void onLongClick(View v, ViewHolder<T> viewHolder);
    }

    ;

    public LBaseAdapter(Context context, int viewid, List<T> objects) {
        mInflater = LayoutInflater.from(context);
        mDataObjects = objects;
        mViewId = viewid;
        if (objects == null) {
            mDataObjects = new ArrayList<T>();
        }
    }

    public List<T> getDataObjects() {
        return mDataObjects;
    }

    public final int getCount() {
        return mDataObjects.size();
    }

    public final T getItem(int position) {
        return mDataObjects.get(position);
    }

    public final long getItemId(int position) {
        return position;
    }

    public int getmCurrentPositon() {
        return mCurrentPositon;
    }


    @SuppressWarnings("unchecked")
    public final View getView(int position, View view, ViewGroup parent) {
        mCurrentPositon = position;
        ViewHolder<T> holder;
        if (view == null) {
            view = mInflater.inflate(mViewId, null);
            holder = createHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder<T>) view.getTag();
        }
        holder.data = getItem(position);
        bindHolder(holder);
        return view;
    }

    protected abstract ViewHolder<T> createHolder(View v);

    protected abstract void bindHolder(ViewHolder<T> h);
}