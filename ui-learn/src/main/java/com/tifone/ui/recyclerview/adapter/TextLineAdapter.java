package com.tifone.ui.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tifone.ui.R;

/**
 * Created by tongkao.chen on 2018/3/22.
 */

public class TextLineAdapter extends RecyclerView.Adapter<TextLineAdapter.ViewHolder> {

    private String[] mDataSet;
    private static final String TAG = "tifone";

    // 提供合适的构造方法
    public TextLineAdapter(String[] dataSet) {
        mDataSet = dataSet;
    }

    // 创建一个新的视图， 由布局管理器调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder");
        // 创建一个新视图
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_item, parent, false);
        // 可以设置视图的各项属性，如宽度高度等

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // 替换view的内容（由布局管理器调用）
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 获取对应位置的数据
        // 用该元素提供view的内容
        holder.textView.setText(mDataSet[position]);
        Log.e(TAG, "onBindViewHolder = " + position);
    }

    // 返回数据的长度（由布局管理器调用）
    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount");
        return mDataSet.length;
    }

    // 为每一个数据选项提供View的应用
    // 复杂的数据选项的每一项会用到多个view
    // 在view holder中为每一个数据选项提供对其多有视图的访问
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // 这里为每一个数据选项提供一个字符串的显示
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.e(TAG, "ViewHolder");
            textView = (TextView) itemView;
        }
    }
}
