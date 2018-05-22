package com.tifone.ui.recyclerview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tongkao.chen on 2018/5/18.
 */

public class PullRefreshRecyclerView extends WrapRecyclerView {

    private RefreshViewCreator mRefreshCreator;
    private LoadMoreCreator mLoadMoreCreator;
    private View mPullRefreshView;
    private int mFingerDownY = -1;
    private boolean mCurrentDrag;
    private int mPullRefreshState;
    private int mLoadMoreState;
    public static final int REFRESH_STATUS_NORMAL = 1000;
    public static final int REFRESH_STATUS_LOOSEN_REFRESHING = 1001;
    public static final int REFRESH_STATUS_REFRESHING = 1002;
    public static final int REFRESH_STATUS_PULL_DOWN_REFRESH = 1003;
    public static final int LOAD_STATUS_NORMAL = 1004;
    public static final int LOAD_STATUS_LOOSEN_TO_LOAD = 1005;
    public static final int LOAD_STATUS_LOADING = 1006;
    public static final int LOAD_STATUS_PULL_DOWN_TO_LOAD = 1007;
    private int mRefreshViewHeight;
    // 拖拽阻力系数
    private float mDragIndex = 0.35f;
    private OnRefreshListener mListener;
    private View mLoadMoreView;
    private int mLoadMoreViewHeight;
    private boolean mLoadViewDrag;
    private int mScreenHeight;
    private int mChildContentHeight;

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
    public void addLoadMoreViewCreator(LoadMoreCreator creator) {
        mLoadMoreCreator = creator;
        addLoadMoreView();
    }

    private void addRefreshView() {
        RecyclerView.Adapter adapter = getAdapter();
        if (adapter != null && mRefreshCreator != null) {
            View refreshView = mRefreshCreator.getRefreshView(getContext(), this);
            if (refreshView != null) {
                addHeaderView(refreshView);
                mPullRefreshView = refreshView;
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        addRefreshView();
        addLoadMoreView();
    }

    private void addLoadMoreView() {
        RecyclerView.Adapter adapter = getAdapter();
        if (adapter != null && mLoadMoreCreator != null) {
            View loadMoreView = mLoadMoreCreator.getLoadMoreView(getContext(), this);
            if (loadMoreView != null) {
                addFooterView(loadMoreView);
                mLoadMoreView = loadMoreView;
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mFingerDownY == -1) {
                    mFingerDownY = (int) ev.getRawY();
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    public void reset() {
        Log.e("tifone", "do something");
        doAnimator(mPullRefreshView.getLayoutParams().height, 0);
        updateRefreshStatus(0);
    }

    private void restoreLoadMoreView() {
        int currentHeight = mLoadMoreView.getLayoutParams().height;
        int finalHeight = 0;
        logger("mLoadMoreViewHeight = " + mLoadMoreViewHeight);
        if (mLoadMoreState == LOAD_STATUS_LOOSEN_TO_LOAD) {
            finalHeight = mLoadMoreViewHeight;
            mLoadMoreState = LOAD_STATUS_LOADING;
            if (mLoadMoreCreator != null) {
                mLoadMoreCreator.onLoad(mLoadMoreState);
                mLoadMoreCreator.onLoading();
            }
            if (mListener != null) {
                mListener.onLoading();
            }
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadProcessReset();
                    if (mLoadMoreCreator != null) {
                        mLoadMoreCreator.onLoadComplete();
                    }
                }
            }, 2000);
        }
        doLoadMoreAnimator(currentHeight, finalHeight);
        mLoadViewDrag = false;
    }

    private void loadProcessReset() {
        doLoadMoreAnimator(mLoadMoreView.getLayoutParams().height, 0);
    }

    private void restorePullRefreshView() {
        int currentHeight = mPullRefreshView.getLayoutParams().height;
        int finalHeight = 0;
        if (mPullRefreshState == REFRESH_STATUS_LOOSEN_REFRESHING) {
            finalHeight = mRefreshViewHeight;
            mPullRefreshState = REFRESH_STATUS_REFRESHING;
            if (mRefreshCreator != null) {
                mRefreshCreator.onRefreshing();
                mRefreshCreator.onPull(currentHeight, mRefreshViewHeight, mPullRefreshState);
            }
            if (mListener != null) {
                mListener.onRefresh();
            }
            Log.e("tifone", "refresh");
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    reset();
                }
            }, 2000);
        }
        int distance = currentHeight - finalHeight;
        Log.e("tifone", "distance = " + distance);
        doAnimator(currentHeight, finalHeight);
        mCurrentDrag = false;
    }

    private void doAnimator(int from, int to) {
        ValueAnimator animator = ObjectAnimator.ofInt(from,
                to).setDuration(Math.abs(from - to));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int height = (int) animation.getAnimatedValue();
                setPullRefreshViewHeight(height);
            }
        });
        animator.start();
    }
    private void doLoadMoreAnimator(int from, int to) {
        ValueAnimator animator = ObjectAnimator.ofInt(from,
                to).setDuration(Math.abs(from - to));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int height = (int) animation.getAnimatedValue();
                setLoadMoreViewHeight(height);
            }
        });
        animator.start();
    }

    private void setPullRefreshViewHeight(int height) {
        ViewGroup.LayoutParams params = mPullRefreshView.getLayoutParams();
        if (height < 0) {
            height = 0;
        }
        params.height = height;
        mPullRefreshView.setLayoutParams(params);
    }

    private boolean isFullContent() {
        return mScreenHeight >= mChildContentHeight;
    }
    @Override
    public boolean onTouchEvent(MotionEvent e) {

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                // 获取手指拖拽的距离
                int distanceY = (int) ((e.getRawY() - mFingerDownY) * mDragIndex);
                logger("distanceY = " + distanceY);
                logger("isScrollToBottom = " + isScrollToBottom());
                if (isScrollToBottom() && isFullContent() ) {
                    if (distanceY < 0) {
                        setLoadMoreViewHeight(Math.abs(distanceY));
                        mLoadViewDrag = true;
                        return false;
                    }
                }

                if (!isScrollToTop() || mPullRefreshState == REFRESH_STATUS_REFRESHING) {
                    return super.onTouchEvent(e);
                }

                // 下拉刷新逻辑
                if (distanceY > 0) {
                    setPullRefreshViewHeight(distanceY);
                    updateRefreshStatus(distanceY);
                    mCurrentDrag = true;
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:
                mFingerDownY = -1;
                if (mCurrentDrag) {
                    restorePullRefreshView();
                }
                if (mLoadViewDrag) {
                    restoreLoadMoreView();
                }
                break;
        }

        return super.onTouchEvent(e);
    }

    private void setLoadMoreViewHeight(int height) {
        logger("setLoadMoreViewHeight = " + height);
        updateLoadMoreStatus(height);
        ViewGroup.LayoutParams params = mLoadMoreView.getLayoutParams();
        params.height = height;
        mLoadMoreView.setLayoutParams(params);
    }

    private void updateLoadMoreStatus(int height) {
        if (height <= 0) {
            mLoadMoreState = LOAD_STATUS_NORMAL;
        } else if (height < mLoadMoreViewHeight) {
            mLoadMoreState = LOAD_STATUS_PULL_DOWN_TO_LOAD;
        } else {
            mLoadMoreState = LOAD_STATUS_LOOSEN_TO_LOAD;
        }
        if (mLoadMoreCreator != null) {
            mLoadMoreCreator.onLoad(mLoadMoreState);
        }
    }

    private void updateRefreshStatus(int height) {
        if (height <= 0) {
            mPullRefreshState = REFRESH_STATUS_NORMAL;
        } else if (height < mRefreshViewHeight) {
            mPullRefreshState = REFRESH_STATUS_PULL_DOWN_REFRESH;
        } else {
            mPullRefreshState = REFRESH_STATUS_LOOSEN_REFRESHING;
        }
        if (mRefreshCreator != null) {
            mRefreshCreator.onPull(height, mRefreshViewHeight, mPullRefreshState);
        }
    }

    private boolean isScrollToTop() {
        if (Build.VERSION.SDK_INT < 14) {
            return canScrollVertically(-1) || getScrollY() > 0;
        }
        LinearLayoutManager llm = (LinearLayoutManager) getLayoutManager();
        return llm.findFirstCompletelyVisibleItemPosition() < 1;
    }
    private boolean isScrollToBottom() {
        LinearLayoutManager llm = (LinearLayoutManager) getLayoutManager();
        return llm.findLastCompletelyVisibleItemPosition() >= getAdapter().getItemCount() - 1;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        logger("onLayout");
        if (changed) {
            if (mPullRefreshView != null && mRefreshViewHeight <= 0) {
                mRefreshViewHeight = mPullRefreshView.getMeasuredHeight();
                if (mRefreshViewHeight > 0) {
                    // 隐藏头部刷新的view
                    setPullRefreshViewHeight(0);
                }
            }
            initLoadMoreViewOriginHeight();

        }
        mScreenHeight = getHeight();
        logger("mScreenHeight = " +mScreenHeight);
        mChildContentHeight = getLayoutManager().getHeight();
        logger("mChildContentHeight = " +mChildContentHeight);
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        // 获取Load more view的实际高度，只有当View加到RecyclerView中才能得到正确的值
        initLoadMoreViewOriginHeight();
    }
    private void initLoadMoreViewOriginHeight() {
        if (mLoadMoreView != null && mLoadMoreViewHeight <= 0) {
            mLoadMoreViewHeight = mLoadMoreView.getMeasuredHeight();
            if (mLoadMoreViewHeight > 0) {
                setLoadMoreViewHeight(0);
            }
        }
    }

    public void onStopRefresh() {
        mPullRefreshState = REFRESH_STATUS_NORMAL;
        restorePullRefreshView();
        if (mRefreshCreator != null) {
            mRefreshCreator.onStopRefresh();
        }
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        mListener = listener;
    }

    public interface OnRefreshListener {
        void onRefresh();
        void onLoading();
    }

    private static void logger(String msg) {
        Log.e("tifone", msg);
    }
}
