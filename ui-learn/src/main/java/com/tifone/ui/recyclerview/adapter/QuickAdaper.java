package com.tifone.ui.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tifone.ui.R;

import java.util.List;

/**
 * Created by tongkao.chen on 2018/4/2.
 */

public abstract class QuickAdaper<T> extends RecyclerView.Adapter<QuickAdaper.QuickViewHolder> {

    private List<T> mDataSet;
    public QuickAdaper(List<T> dataSet) {
        mDataSet = dataSet;
    }

    @Override
    public QuickViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return QuickViewHolder.get(parent, getLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(QuickViewHolder holder, int position) {
        convertView(holder, mDataSet.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public abstract void convertView(QuickViewHolder holder, T data, int position);
    public abstract int getLayoutId(int viewType);

    static class QuickViewHolder extends RecyclerView.ViewHolder{
        private View mConvertView;
        private SparseArray<View> mViews;
        public QuickViewHolder(View itemView) {
            super(itemView);
            mConvertView = itemView;
            mViews = new SparseArray<>();
        }
        public static QuickViewHolder get(ViewGroup parent, int layoutId) {
            View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return  new QuickViewHolder(convertView);
        }

        public <T extends View> T getView(int id) {
            View view = mViews.get(id);
            if (view == null) {
                view = mConvertView.findViewById(id);
            }
            return (T) view;
        }

        public void setText(int id, String value) {
            TextView view = getView(id);
            view.setText(value);
        }

    }
}
