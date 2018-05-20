package com.tifone.ui.recyclerview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.database.DataSetObservable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.tifone.ui.recyclerview.adapter.HeaderFooterWrapAdapter;
import com.tifone.ui.recyclerview.view.RecyclerViewFooter;
import com.tifone.ui.recyclerview.view.RecyclerViewHeader;

/**
 * Created by tongkao.chen on 2018/5/18.
 */

public class PullRefreshRecyclerView extends WrapRecyclerView {

    private RefreshViewCreator mRefreshCreator;
    private View mRefreshView;
    private int mFingerDownY;
    private boolean mCurrentDrag;
    private int mCurrentRefreshState;
    private static final int REFRESH_STATUS_NORMAL = 1000;
    private static final int REFRESH_STATUS_LOOSEN_REFRESHING = 1001;
    private static final int REFRESH_STATUS_REFRESHING = 1002;
    private static final int REFRESH_STATUS_PULL_DOWN_REFRESH = 1003;
    private int mRefreshViewHeight;
    // 拖拽阻力系数
    private float mDragIndex = 0.35f;
    private OnRefreshListener mListener;

    public PullRefreshRecyclerView(Context context) {
        super(context);
    }

    public PullRefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PullRefreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addRefreshViewCreator(RefreshViewCreator creator) {
        mRefreshCreator = creator;
        addRefreshView();
    }

    private void addRefreshView() {
        RecyclerView.Adapter adapter = getAdapter();
        if (adapter != null && mRefreshCreator != null) {
            View refreshView = mRefreshCreator.getRefreshView(getContext(), this);
            if (refreshView != null) {
                addHeaderView(refreshView);
                mRefreshView = refreshView;
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        addRefreshView();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mFingerDownY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                if (mCurrentDrag) {
                    restoreRefreshView();
                }
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    private void restoreRefreshView() {
        int currentTopMargin = ((MarginLayoutParams) mRefreshView.getLayoutParams()).topMargin;
        int finalTopMargin = -currentTopMargin + 1;
        if (mCurrentRefreshState == REFRESH_STATUS_LOOSEN_REFRESHING) {
            finalTopMargin = 0;
            mCurrentRefreshState = REFRESH_STATUS_REFRESHING;
            if (mRefreshCreator != null) {
                mRefreshCreator.onRefreshing();
            }
            if (mListener != null) {
                mListener.onRefresh();
            }
        }
        int distance = currentTopMargin - finalTopMargin;
        Log.e("tifone", "distance = " + distance);
        ValueAnimator animator = ObjectAnimator.ofFloat(currentTopMargin,
                finalTopMargin).setDuration(distance);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentTopMargin = (float) animation.getAnimatedValue();
                setRefreshViewMarginTop((int) currentTopMargin);
            }
        });
        animator.start();
        mCurrentDrag = false;
    }

    private void setRefreshViewMarginTop(int marginTop) {
        MarginLayoutParams params = (MarginLayoutParams) mRefreshView.getLayoutParams();
        if (marginTop < -mRefreshViewHeight + 1) {
            marginTop = -mRefreshViewHeight + 1;
        }
        params.topMargin = marginTop;
        mRefreshView.setLayoutParams(params);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                Log.e("tifone", "canScrollUp = " + canScrollUp() + " getScrollY = " + getScrollY());
                if (canScrollUp() || mCurrentRefreshState == REFRESH_STATUS_REFRESHING) {
                    return super.onTouchEvent(e);
                }
                // 解决下拉刷新自动滚动的问题
                if (mCurrentDrag) {
                    scrollToPosition(0);
                }

                // 获取手指拖拽的距离
                int distanceY = (int) ((e.getRawY() - mFingerDownY) * mDragIndex);
                Log.e("tifone", "distanceY = " + distanceY);
                if (distanceY > 0) {
                    int marginTop = distanceY - mRefreshViewHeight;
                    Log.e("tifone", "marginTop = " + marginTop);
                    setRefreshViewMarginTop(marginTop);
                    updateRefreshStatus(marginTop);
                    mCurrentDrag = true;
                    return false;
                }
                break;
        }

        return super.onTouchEvent(e);
    }

    private void updateRefreshStatus(int marginTop) {
        if (marginTop <= -mRefreshViewHeight) {
            mCurrentRefreshState = REFRESH_STATUS_NORMAL;
        } else if (marginTop < 0) {
            mCurrentRefreshState = REFRESH_STATUS_PULL_DOWN_REFRESH;
        } else {
            mCurrentRefreshState = REFRESH_STATUS_REFRESHING;
        }
        if (mRefreshCreator != null) {
            mRefreshCreator.onPull(marginTop, mRefreshViewHeight, mCurrentRefreshState);
        }
    }

    private boolean canScrollUp() {
        if (Build.VERSION.SDK_INT < 14) {
            return canScrollVertically(-1) || getScrollY() > 0;
        }
        return canScrollVertically(-1);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            if (mRefreshView != null && mRefreshViewHeight <= 0) {
                mRefreshViewHeight = mRefreshView.getMeasuredHeight();
                if (mRefreshViewHeight > 0) {
                    // 隐藏头部刷新的view，marginTop预留多1px防止无法判断是否滚动到头部的问题
                    setRefreshViewMarginTop(-mRefreshViewHeight + 1);
                }
            }
        }
    }
    public void onStopRefresh() {
        mCurrentRefreshState = REFRESH_STATUS_NORMAL;
        restoreRefreshView();
        if (mRefreshCreator != null) {
            mRefreshCreator.onStopRefresh();
        }
    }
    public void setOnRefreshListener(OnRefreshListener listener) {
        mListener = listener;
    }
    public interface OnRefreshListener {
        void onRefresh();
    }
}
