package com.tifone.ui.recyclerview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tifone.ui.R;

/**
 * Created by tongkao.chen on 2018/5/17.
 */

public class RecyclerViewHeader extends LinearLayout {
    private LinearLayout mContainer;
    private static final int STATE_NORMAL = 0;
    private static final int STATE_READY = 1;
    private static final int STATE_REFRESHING = 2;
    private int mState = STATE_NORMAL;
    private TextView mHeaderTitle;

    public RecyclerViewHeader(Context context) {
        this(context, null);
    }

    public RecyclerViewHeader(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerViewHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, 0);
        // 下拉布局
        mContainer = (LinearLayout) LayoutInflater.from(getContext()).inflate(
                R.layout.recycler_view_refresh_header, (ViewGroup) getParent(), true);
        addView(mContainer, layoutParams);
        setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        mHeaderTitle = (TextView) findViewById(R.id.header_title);
    }

    public void setState(int state) {
        if (mState == state) {
            return;
        }
        switch (state) {
            case STATE_NORMAL:
                mHeaderTitle.setText("下拉刷新");
                break;
            case STATE_READY:
                mHeaderTitle.setText("松开刷新数据");
                break;
            case STATE_REFRESHING:
                mHeaderTitle.setText("正在加载...");
                break;
            default:
                break;
        }
        mState = state;

    }
    public void setPullImage(){}
    public void setPullContent() {

    }
    public int getRealHeight() {
        return mContainer.getHeight();
    }
    public void setVisibleHeight(int height) {
        if (height < 0) {
            height = 0;
        }
        LayoutParams lp = (LayoutParams) mContainer.getLayoutParams();
        lp.height = height;
        mContainer.setLayoutParams(lp);
    }
    public int getVisibleHeight() {
        return mContainer.getLayoutParams().height;
    }
}
