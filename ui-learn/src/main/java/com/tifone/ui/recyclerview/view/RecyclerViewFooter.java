package com.tifone.ui.recyclerview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tifone.ui.R;

/**
 * Created by tongkao.chen on 2018/5/17.
 */

public class RecyclerViewFooter extends LinearLayout {
    public final static int STATE_NORMAL = 0;
    public final static int STATE_READY = 1;
    public final static int STATE_LOADING = 2;

    private TextView mFooterTitle;
    private int mState = STATE_NORMAL;
    private ProgressBar mFooterProgressBar;
    private LinearLayout mMoreView;

    public RecyclerViewFooter(Context context) {
        this(context, null);
    }

    public RecyclerViewFooter(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerViewFooter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mMoreView = (LinearLayout) LayoutInflater.from(getContext()).inflate(
                R.layout.recycler_view_refresh_footer, (ViewGroup) getParent(), true);
        addView(mMoreView);
        mMoreView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        mFooterTitle = findViewById(R.id.footer_title);
        mFooterProgressBar = findViewById(R.id.footer_progressbar);

    }

    public void setState(int state) {
        if (mState == state) {
            return;
        }
        mFooterProgressBar.setVisibility(INVISIBLE);
        mFooterTitle.setVisibility(VISIBLE);
        switch (state) {
            case STATE_NORMAL:
                mFooterTitle.setText("加载更多");
                break;
            case STATE_LOADING:
                mFooterProgressBar.setVisibility(VISIBLE);
                mFooterTitle.setVisibility(INVISIBLE);
                break;
            case STATE_READY:
                mFooterTitle.setText("松开加载");
                break;
            default:
                break;
        }
    }
    public void setBottomMargin(int margin) {
        if (margin < 0) {
            return;
        }
        LayoutParams lp = (LayoutParams) mMoreView.getLayoutParams();
        lp.bottomMargin = margin;
        mMoreView.setLayoutParams(lp);
    }
    public int getBottomMargin() {
        LayoutParams lp = (LayoutParams) mMoreView.getLayoutParams();
        return lp.bottomMargin;
    }
    public void hide() {
        LayoutParams lp = (LayoutParams) mMoreView.getLayoutParams();
        lp.height = 0;
        mMoreView.setLayoutParams(lp);
    }
    public void show() {
        LayoutParams lp = (LayoutParams) mMoreView.getLayoutParams();
        lp.height = LayoutParams.WRAP_CONTENT;
        mMoreView.setLayoutParams(lp);
    }
}
