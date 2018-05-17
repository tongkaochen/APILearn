package com.tifone.ui.recyclerview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Create by Tifone on 2018/5/17.
 */
public class HeaderFooterWrapAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final int BASE_VIEW_TYPE = 1000;
    private static final int HEADER_VIEW_TYPE = BASE_VIEW_TYPE;
    private static final int FOOTER_VIEW_TYPE = BASE_VIEW_TYPE + 1;
    private RecyclerView.Adapter mInnerAdapter;
    private SparseArray<View> mHeaderViews = new SparseArray<>();
    private SparseArray<View> mFooterViews = new SparseArray<>();

    public HeaderFooterWrapAdapter(@NonNull RecyclerView.Adapter adapter) {
        mInnerAdapter = adapter;
    }

    public int getHeaderSize() {
        return mHeaderViews.size();
    }

    public int getFooterSize() {
        return mFooterViews.size();
    }

    public void addHeaderView(View view) {
        if (mHeaderViews.indexOfValue(view) < 0) {
            mHeaderViews.put(HEADER_VIEW_TYPE + mHeaderViews.size(), view);
            notifyItemInserted(getHeaderSize());
        }
    }

    public void addFooterView(View view) {
        if (mFooterViews.indexOfValue(view) < 0) {
            mFooterViews.put(FOOTER_VIEW_TYPE + mFooterViews.size(), view);
            notifyItemInserted(getContentItemsSize() + getHeaderSize() + getFooterSize());
        }
    }

    public void removeHeader(View view) {
        int index = mHeaderViews.indexOfValue(view);
        if (index < 0) {
            return;
        }
        mHeaderViews.removeAt(index);
        notifyItemRemoved(index);
    }

    public void removeFooter(View view) {
        int index = mFooterViews.indexOfValue(view);
        if (index < 0) {
            return;
        }
        mFooterViews.removeAt(index);
        notifyItemRemoved(getHeaderSize() + getContentItemsSize() + index);
    }

    private boolean isHeaderPos(int position) {
        return position < getHeaderSize();
    }

    private boolean isFooterPos(int position) {
        return position >= getHeaderSize() + getContentItemsSize();
    }

    private int getContentItemsSize() {
        return mInnerAdapter.getItemCount();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {
            // Header
            return new HeaderViewHolder(mHeaderViews.get(viewType));
        }
        if (mFooterViews.get(viewType) != null) {
            // Footer
            return new FooterViewHolder(mFooterViews.get(viewType));
        }
        // content
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (isHeaderPos(position) || isFooterPos(position)) {
            return;
        }
        // 子adapter的position要剔除Header的大小
        mInnerAdapter.onBindViewHolder(holder, position - getHeaderSize());
    }

    @Override
    public int getItemCount() {
        return getContentItemsSize() + getHeaderSize() + getFooterSize();
    }

    @Override
    public int getItemViewType(int position) {
        // wrapper adapter只处理Header和Footer相关的view，其他的view 交给子Adapter来实现
        if (isHeaderPos(position)) {
            // 返回mHeaderViews的id，以便在onCreateViewHolder中根据id来过相对应的view
            return mHeaderViews.keyAt(position);
        } else if (isFooterPos(position)) {
            // 底部的计算开始位置是子adapter内容的数量与头部数量之和
            return mFooterViews.keyAt(position - getContentItemsSize() - getHeaderSize());
        }
        // 非Header和Footer，交给子Adapter处理
        return mInnerAdapter.getItemViewType(position);
    }

    /**
     * 解决GridLayoutManager添加的头部和底部不占一行的问题
     *
     * @param recyclerView
     */
    public void adjustSpanSize(RecyclerView recyclerView) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            final GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    boolean isHeaderOrFooter = isHeaderPos(position) || isFooterPos(position);
                    return isHeaderOrFooter ? layoutManager.getSpanCount() : 1;
                }
            });
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
