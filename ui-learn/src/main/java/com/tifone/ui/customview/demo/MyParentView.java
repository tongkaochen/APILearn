package com.tifone.ui.customview.demo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by tongkao.chen on 2018/4/25.
 */

public class MyParentView extends LinearLayout {

    private int mYDown, mYMove;
    private int mMoveDistance;
    private int mTotalMove;
    private boolean isIntercept;
    private boolean mIsInterceptUp;
    private boolean mIsInterceptDown;

    public MyParentView(Context context) {
        this(context, null);
    }

    public MyParentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchY = (int) event.getY();
        Log.e("tifone", "onTouchEvent ev.getAction(): " + event.getAction());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mYDown = touchY;
                break;
            case MotionEvent.ACTION_MOVE:
                mYMove = touchY;
                // Move down
                mMoveDistance = mYMove - mYDown;
                boolean shouldLayout = false;
                // totalDistance是滑动的总距离，当下滑时，该值为正值，该值越来越大，
                // 上滑时，该值为负值，并且其绝对值越来越大
                int totalDistance = mMoveDistance + mTotalMove;
                if (mMoveDistance > 0) {
                    // 当下滑时，布局跟随滑动下移，若滑动距离超过400，则停止布局下移
                    if (totalDistance > 400) {
                        shouldLayout = false;
                    } else {
                        mTotalMove = totalDistance;
                        shouldLayout = true;
                    }
                } else {
                    if (totalDistance < -200) {
                        shouldLayout = false;
                    } else {
                        mTotalMove = totalDistance;
                        shouldLayout = true;
                    }
                }
                if (shouldLayout) {
                    // 实现布局偏移是通过重新布局实现的
                    layout(getLeft(), getTop() + mMoveDistance, getRight(), getBottom() + mMoveDistance);
                }

                break;
            case MotionEvent.ACTION_UP:
                Log.e("tifone", "onTouchEvent: " + mTotalMove);
                // 回到起始位置
                layout(getLeft(), getTop() - mTotalMove, getRight(), getBottom() - mTotalMove);
                mTotalMove = 0;
                // 不拦截touch事件
                isIntercept = false;
                break;
        }
        return true;
    }

    public void setInterceptUp(boolean interceptUp) {
        mIsInterceptUp = interceptUp;
    }

    public void setInterceptDown(boolean interceptDown) {
        mIsInterceptDown = interceptDown;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int y = (int) ev.getY();
        Log.e("tifone", "onInterceptTouchEvent ev.getAction(): " + ev.getAction());
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mYDown = y;
                break;
            case MotionEvent.ACTION_MOVE:
                mYMove = y;
                Log.e("tifone", "onInterceptTouchEvent ACTION_MOVE: " + mYMove);
                if (mYMove - mYDown > 0) {
                    isIntercept = mIsInterceptDown;
                } else if (mYMove - mYDown < 0) {
                    isIntercept = mIsInterceptUp;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        Log.e("tifone", "onInterceptTouchEvent: " + isIntercept);
        return isIntercept;
    }
}
