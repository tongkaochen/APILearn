package com.tifone.ui.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.tifone.ui.R;

/**
 * Create by Tifone on 2018/5/20.
 */
public class DefaultRefreshCreator extends RefreshViewCreator{

    private ProgressBar mProgressBar;

    @Override
    public View getRefreshView(Context context, ViewGroup parent) {
        View refreshView = LayoutInflater.from(context).inflate(R.layout.recycler_view_refresh_header, parent, false);
        mProgressBar = refreshView.findViewById(R.id.header_progress);
        return refreshView;
    }

    @Override
    public void onPull(int currentDragHeight, int refreshViewHeight, int currentRefreshStatus) {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onRefreshing() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStopRefresh() {
        mProgressBar.setVisibility(View.GONE);
    }
}
