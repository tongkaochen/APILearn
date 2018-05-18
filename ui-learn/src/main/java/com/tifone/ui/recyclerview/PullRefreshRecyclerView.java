package com.tifone.ui.recyclerview;

import android.content.Context;
import android.database.DataSetObservable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.tifone.ui.recyclerview.adapter.HeaderFooterWrapAdapter;
import com.tifone.ui.recyclerview.view.RecyclerViewFooter;
import com.tifone.ui.recyclerview.view.RecyclerViewHeader;

/**
 * Created by tongkao.chen on 2018/5/18.
 */

public class PullRefreshRecyclerView extends RecyclerView {
    RecyclerView.Adapter mAdapter;
    HeaderFooterWrapAdapter mWrapAdapter;

    public PullRefreshRecyclerView(Context context) {
        this(context, null);
    }

    public PullRefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (mAdapter != null) {
            mAdapter.unregisterAdapterDataObserver(mDataObserver);
        }
        mAdapter = adapter;
        mAdapter.registerAdapterDataObserver(mDataObserver);
        mWrapAdapter = new HeaderFooterWrapAdapter(adapter);
        mWrapAdapter.addHeaderView(new RecyclerViewHeader(getContext()));
        mWrapAdapter.addFooterView(new RecyclerViewFooter(getContext()));
        super.setAdapter(mWrapAdapter);
        mWrapAdapter.adjustSpanSize(this);
    }
    AdapterDataObserver mDataObserver = new AdapterDataObserver() {
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
