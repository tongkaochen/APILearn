package com.tifone.ui.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Create by Tifone on 2018/5/17.
 */
public abstract class RefreshViewCreator {
    /**
     * 获取下拉刷新的View
     *
     * @param parent  recyclerview
     * @return refresh view
     */
    public abstract View getRefreshView(Context context, ViewGroup parent);

    /**
     * 正在下拉
     * @param currentDragHeight 当前拖动的高度
     * @param refreshViewHeight 总得刷新高度
     * @param currentRefreshStatus 当前的状态
     */
    public abstract void onPull(int currentDragHeight, int refreshViewHeight, int currentRefreshStatus);

    /**
     * 正在刷新
     */
    public abstract void onRefreshing();

    /**
     * 停止刷新
     */
    public abstract void onStopRefresh();

    public abstract void onRefreshComplete();

}
