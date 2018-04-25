package com.tifone.ui.customview.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by tongkao.chen on 2018/4/25.
 */

public class MyScrollView extends ScrollView {

    private OnScrollChangedListener mListener;
    public interface OnScrollChangedListener{
        void onScrollChanged(int h, int v, int oldH, int oldV);
    }
    public void setOnScrollChangedListener(OnScrollChangedListener listener) {
        mListener = listener;
    }

    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mListener != null) {
            mListener.onScrollChanged(l, t, oldl, oldt);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mListener = null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e("tifone", "scrollview onTouchEvent: " + ev.getAction());
        return super.onTouchEvent(ev);
    }
}
