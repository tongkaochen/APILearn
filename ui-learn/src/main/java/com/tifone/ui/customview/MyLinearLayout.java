package com.tifone.ui.customview;

import android.content.Context;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tongkao.chen on 2018/4/23.
 */

public class MyLinearLayout extends ViewGroup {
    public MyLinearLayout(Context context) {
        this(context, null);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);


        int childCount = getChildCount();
        if (childCount == 0) {
            setMeasuredDimension(0, 0);
            return;
        }

        logger("onMeasure");
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {

            setMeasuredDimension(getChildMaxWidth(), getChildTotalHeight());
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(getChildMaxWidth(), heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, getChildTotalHeight());
        } else {
            setMeasuredDimension(widthSize, heightSize);
        }
    }

    private int getChildTotalHeight() {
        int childCount = getChildCount();
        int totalHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            totalHeight += view.getMeasuredHeight();
        }
        int padding = getPaddingTop() + getPaddingBottom();
        totalHeight += padding;
        logger("totalHeight: " + totalHeight);
        return totalHeight;
    }

    private int getChildMaxWidth() {
        int childCount = getChildCount();
        int maxWidth = 0;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (view.getMeasuredWidth() > maxWidth) {
                maxWidth = view.getMeasuredWidth();
            }
        }
        int padding = getPaddingLeft() + getPaddingRight();
        maxWidth += padding;
        logger("maxWidth = " + maxWidth);
        return maxWidth;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int currentHeight = 0;
        logger("l = " + l + " t = " + t + " r = " + r + " b = " + b);
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            child.layout(0, currentHeight, l + width, currentHeight + height);
            currentHeight += height + child.getPaddingTop();
            logger("currentHeight: " + currentHeight);
        }
    }

    private static void logger(String msg) {
        Log.e("tifone", msg);
    }
}
