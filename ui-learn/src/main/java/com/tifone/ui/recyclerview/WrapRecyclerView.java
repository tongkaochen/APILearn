package com.tifone.ui.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.tifone.ui.recyclerview.adapter.HeaderFooterWrapAdapter;

/**
 * Create by Tifone on 2018/5/17.
 */
public class WrapRecyclerView extends RecyclerView {
    private HeaderFooterWrapAdapter mWrapAdapter;
    private RecyclerView.Adapter mAdatper;


    public WrapRecyclerView(Context context) {
        super(context);
    }

    public WrapRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (mAdatper != null) {
            mAdatper.unregisterAdapterDataObserver(mDataObserver);
        }
        mAdatper = adapter;
        if (adapter instanceof HeaderFooterWrapAdapter) {
            mWrapAdapter = (HeaderFooterWrapAdapter) adapter;
        } else {
            mWrapAdapter = new HeaderFooterWrapAdapter(adapter);
        }
        super.setAdapter(mWrapAdapter);
        mWrapAdapter.registerAdapterDataObserver(mDataObserver);
        // 解决GridLayoutManager添加的头部和底部不占一行的问题
        mWrapAdapter.adjustSpanSize(this);
    }
    public void addHeaderView(View view) {
        if (mWrapAdapter == null) {
            return;
        }
        mWrapAdapter.addHeaderView(view);
    }
    public void addFooterView(View view) {
        if (mWrapAdapter == null) {
            return;
        }
        mWrapAdapter.addFooterView(view);
    }
    public void removeHeaderView(View view) {
        if (mWrapAdapter == null) {
            return;
        }
        mWrapAdapter.removeHeader(view);
    }
    public void removeFooterView(View view) {
        if (mWrapAdapter == null) {
            return;
        }
        mWrapAdapter.removeFooter(view);
    }
    private AdapterDataObserver mDataObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            super.onItemRangeChanged(positionStart, itemCount, payload);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
        }
    };
}
