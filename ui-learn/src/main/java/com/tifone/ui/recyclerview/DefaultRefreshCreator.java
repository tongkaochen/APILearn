package com.tifone.ui.recyclerview;

import android.animation.ObjectAnimator;
import android.animation.TypeConverter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tifone.ui.R;

/**
 * Create by Tifone on 2018/5/20.
 */
public class DefaultRefreshCreator extends RefreshViewCreator {

    private TextView mTextView;
    private ImageView mImageView;
    private int mLastStatus;

    @Override
    public View getRefreshView(Context context, ViewGroup parent) {
        View refreshView = LayoutInflater.from(context).inflate(R.layout.recycler_view_refresh_header, parent, false);
        mTextView = refreshView.findViewById(R.id.header_title);
        mImageView = refreshView.findViewById(R.id.header_image);
        return refreshView;
    }

    @Override
    public void onPull(int currentDragHeight, int refreshViewHeight, int currentRefreshStatus) {
        mImageView.clearAnimation();
        switch (currentRefreshStatus) {
            case PullRefreshRecyclerView.REFRESH_STATUS_LOOSEN_REFRESHING:
                mTextView.setText("松开后刷新");
                mImageView.setImageResource(R.drawable.ic_upward);
                break;
            case PullRefreshRecyclerView.REFRESH_STATUS_PULL_DOWN_REFRESH:
                mTextView.setText("下拉刷新");
                mImageView.setImageResource(R.drawable.ic_downward);

                break;
            case PullRefreshRecyclerView.REFRESH_STATUS_REFRESHING:
                mTextView.setText("正在刷新");
                mImageView.setImageResource(R.drawable.ic_loading);
                if (currentRefreshStatus != mLastStatus) {
                    doAnimator(0, 360, -1);
                }
                break;
            case PullRefreshRecyclerView.REFRESH_STATUS_NORMAL:
                mTextView.setText("准备就绪");
                mImageView.clearAnimation();
                break;
        }
        mLastStatus = currentRefreshStatus;
    }
    private void doAnimator(float start, float end, int repeatCount) {
        /*ObjectAnimator animator = ObjectAnimator.ofFloat(mImageView, "rotation", start, end);
        animator.setDuration(800);
        animator.setRepeatCount(repeatCount);
        animator.start();*/
        RotateAnimation animation = new RotateAnimation(start, end,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setRepeatCount(repeatCount);
        animation.setDuration(800);
        mImageView.startAnimation(animation);
    }

    @Override
    public void onRefreshing() {
    }

    @Override
    public void onStopRefresh() {
    }

    @Override
    public void onRefreshComplete() {
        mTextView.setText("刷新完成");
        mImageView.clearAnimation();
        mImageView.setImageResource(R.drawable.ic_success);
        Animation animation = AnimationUtils.loadAnimation(mImageView.getContext(), R.anim.image_scale_anim);
        animation.setDuration(500);
        mImageView.startAnimation(animation);
        mTextView.startAnimation(animation);
    }
}
