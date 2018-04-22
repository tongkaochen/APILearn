package com.tifone.ui.recyclerview.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by tongkao.chen on 2018/4/2.
 */

public class ListItemDividerDecoration extends RecyclerView.ItemDecoration {

    public static final int VERTICAL = LinearLayout.VERTICAL;
    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    private static final int[] DIVIDER_ATTR = new int[]{android.R.attr.listDivider};
    private static final String TAG = "Decoration";
    private Drawable mDivider;
    private int mOrientation;
    private Context mContext;
    private final Rect mBound = new Rect();
    public ListItemDividerDecoration(Context context, int orientation) {
        mContext = context;
        TypedArray ta = context.obtainStyledAttributes(DIVIDER_ATTR);
        if (ta != null) {
            mDivider = ta.getDrawable(0);
        }
        if (mDivider == null) {
            Log.w(TAG, "@android:attr/listDivider was not set in the theme used for MyItemDividerDecoration.");
        }
        ta.recycle();
        setOrientation(orientation);
    }
    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        mOrientation = orientation;
    }

    /**
     * Set Drawable for this divider.
     * @param drawable
     */
    public void setDrawable(Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("Drawable can not be null");
        }
        mDivider = drawable;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() == null || mDivider == null) {
            return;
        }
        switch (mOrientation) {
            case HORIZONTAL:
                drawHorizontal(c, parent);
                break;
            case VERTICAL:
                drawVertical(c, parent);
                break;
            default:
                break;
        }
    }
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int left = 0;
        final int right = parent.getWidth();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, mBound);
            // getTranslationY 表示child在Y轴上的偏移量，向上偏移为负，向下偏移为正
            int bottom = mBound.bottom + Math.round(child.getTranslationY());
            int top = bottom - mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
        canvas.restore();

    }
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int top = 0;
        final int bottom = parent.getBottom();

        int childCount = parent.getChildCount();
        for (int i = 0; i< childCount; i++) {
            View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, mBound);
            final int right = mBound.right + Math.round(child.getTranslationX());
            final int left = right - mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
        canvas.restore();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mDivider == null) {
            outRect.set(0, 0, 0, 0);
        }
        switch (mOrientation) {
            case VERTICAL:
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
                break;
            case HORIZONTAL:
                outRect.set(0, 0, mDivider.getIntrinsicHeight(), 0);
                break;
            default:
                break;
        }
    }
}
