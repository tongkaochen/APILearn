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
    private RecyclerView.Adapter mAdapter;


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
        if (mAdapter != null) {
            mAdapter.unregisterAdapterDataObserver(mDataObserver);
        }
        mAdapter = adapter;
        if (adapter instanceof HeaderFooterWrapAdapter) {
            mWrapAdapter = (HeaderFooterWrapAdapter) adapter;
        } else {
            mWrapAdapter = new HeaderFooterWrapAdapter(adapter);
        }
        super.setAdapter(mWrapAdapter);
        mAdapter.registerAdapterDataObserver(mDataObserver);
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
            if (mAdapter == null) {
                return;
            }
            // 列表Adapter更新 包裹的也需要更新不然列表的notifyDataSetChanged没效果
            if (mWrapAdapter != mAdapter) {
                mWrapAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            if (mAdapter == null) {
                return;
            }
            if (mWrapAdapter != mAdapter) {
                mWrapAdapter.notifyItemChanged(positionStart);
            }
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            if (mAdapter == null) {
                return;
            }
            if (mWrapAdapter != mAdapter) {
                mWrapAdapter.notifyItemChanged(positionStart, payload);
            }
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            if (mAdapter == null) {
                return;
            }
            if (mWrapAdapter != mAdapter) {
                mWrapAdapter.notifyItemInserted(positionStart);
            }
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            if (mAdapter == null) {
                return;
            }
            if (mWrapAdapter != mAdapter) {
                mWrapAdapter.notifyItemRemoved(positionStart);
            }
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            if (mAdapter == null) {
                return;
            }
            if (mWrapAdapter != mAdapter) {
                mWrapAdapter.notifyItemMoved(fromPosition, toPosition);
            }
        }
    };
}
