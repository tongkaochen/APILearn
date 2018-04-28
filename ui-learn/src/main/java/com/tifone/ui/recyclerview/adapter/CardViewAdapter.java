package com.tifone.ui.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tifone.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tongkao.chen on 2018/3/22.
 */

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<String> mDataSet;
    private static final String TAG = "tifone";
    private List<Integer> mHeight;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    private OnItemClickListener mItemClickListener;
    public void setItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    // 提供合适的构造方法
    public CardViewAdapter(Context context, List<String> dataSet) {
        mDataSet = dataSet;
        mInflater = LayoutInflater.from(context);
        mHeight = new ArrayList<>(dataSet.size());
        for (int i = 0; i < dataSet.size(); i++) {
            mHeight.add((int) (400 + Math.random() * 300));
        }
    }

    // 创建一个新的视图， 由布局管理器调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder");
        // 创建一个新视图
        View view = mInflater.inflate(R.layout.card_view_item, parent, false);
        // 可以设置视图的各项属性，如宽度高度等

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // 替换view的内容（由布局管理器调用）
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ViewGroup.LayoutParams params = holder.cardView.getLayoutParams();
        params.height = mHeight.get(position);
        // 获取对应位置的数据
        // 用该元素提供view的内容
        holder.textView.setText(mDataSet.get(position));
        Log.e(TAG, "onBindViewHolder = " + position);
        if (mItemClickListener != null) {
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(v, holder.getAdapterPosition());
                }
            });
            holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mItemClickListener.onItemLongClick(v, holder.getAdapterPosition());
                    removeData(holder.getAdapterPosition());
                    return false;
                }
            });
        }
    }
    public void addData(int position) {
        mDataSet.add(position, "Add data . " + position);
        mHeight.add((int) (400 + Math.random() * 300));
        notifyItemInserted(position);
    }
    public void removeData(int position) {
        mDataSet.remove(mDataSet.get(position));
        notifyItemRemoved(position);
    }

    // 返回数据的长度（由布局管理器调用）
    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount");
        return mDataSet.size();
    }

    // 为每一个数据选项提供View的应用
    // 复杂的数据选项的每一项会用到多个view
    // 在view holder中为每一个数据选项提供对其多有视图的访问
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // 这里为每一个数据选项提供一个字符串的显示
        public TextView textView;
        public CardView cardView;

        public ViewHolder(final View itemView) {
            super(itemView);
            Log.e(TAG, "ViewHolder");
            textView = (TextView) itemView.findViewById(R.id.card_item_tv);
            cardView = (CardView) itemView.findViewById(R.id.my_card_view);
        }
    }
}
