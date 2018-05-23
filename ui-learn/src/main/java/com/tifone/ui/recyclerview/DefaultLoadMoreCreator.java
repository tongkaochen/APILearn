package com.tifone.ui.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tifone.ui.R;

/**
 * Created by tongkao.chen on 2018/5/22.
 */

public class DefaultLoadMoreCreator extends LoadMoreCreator {
    private ProgressBar mProgress;
    private TextView mTextView;

    @Override
    public View getLoadMoreView(Context context, ViewGroup parent) {
        View loadMoreView = LayoutInflater.from(context).inflate(R.layout.recycler_view_refresh_footer, parent, false);
        mProgress = loadMoreView.findViewById(R.id.footer_progressbar);
        mTextView = loadMoreView.findViewById(R.id.footer_title);
        return loadMoreView;
    }

    @Override
    public void onLoad(int status) {
        Log.e("tifone", "status = " + status);
        switch (status) {
            case PullRefreshRecyclerView.LOAD_STATUS_NORMAL:
                mTextView.setText("加载更多");
                mProgress.setVisibility(View.GONE);
                break;
            case PullRefreshRecyclerView.LOAD_STATUS_LOADING:
                mTextView.setText("正在加载");
                mProgress.setVisibility(View.VISIBLE);
                break;
            case PullRefreshRecyclerView.LOAD_STATUS_LOOSEN_TO_LOAD:
                mTextView.setText("松开后加载");
                mProgress.setVisibility(View.GONE);
                break;
            case PullRefreshRecyclerView.LOAD_STATUS_PULL_UP_TO_LOAD:
                mTextView.setText("上拉加载更多");
                mProgress.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoadFail() {

    }

    @Override
    public void onLoadComplete() {
        mTextView.setText("加载完成");
        Animation animation = AnimationUtils.loadAnimation(mTextView.getContext(), R.anim.image_scale_anim);
        animation.setDuration(500);
        mTextView.startAnimation(animation);
        mProgress.setVisibility(View.GONE);
    }
}
