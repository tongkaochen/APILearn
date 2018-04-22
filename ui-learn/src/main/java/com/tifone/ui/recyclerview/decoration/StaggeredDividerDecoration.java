package com.tifone.ui.recyclerview.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by tongkao.chen on 2018/4/4.
 */

public class StaggeredDividerDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "StaggeredDivider";
    public static final int VERTICAL = LinearLayout.VERTICAL;
    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    private Context mContext;
    private int mOrientation;
    private static final int[] ATTR = new int[]{android.R.attr.listDivider};
    private Drawable mDivider;
    private Rect mBound = new Rect();

    public StaggeredDividerDecoration(Context context, int orientation) {
        mContext = context;
        init(orientation);
    }

    private void init(int orientation) {
        TypedArray ta = mContext.obtainStyledAttributes(ATTR);
        if (ta != null) {
            mDivider = ta.getDrawable(0);
        }
        if (mDivider == null) {
            Log.w(TAG, "Show set @android:listDivider on your theme first");
        }

    }

    public void setDrawable(Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("The drawable for this Divider can not be null");
        }
        mDivider = drawable;
    }

    public void setOrientation(int orientation) {
        if (orientation != VERTICAL && orientation != HORIZONTAL) {
            throw new IllegalArgumentException("The orientation must be either VERTICAL or HORIZONTAL");
        }
        mOrientation = orientation;
    }

    private int getSpanCount(RecyclerView parent) {
        StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) parent.getLayoutManager();
        return layoutManager.getSpanCount();
    }
    private boolean needToDraw(int position, RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getChildCount();
        boolean isLast = (position + 1) % spanCount == 0;

        return !isLast;
    }

    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getLeft() - params.leftMargin;
            int right = child.getRight() + params.rightMargin + mDivider.getIntrinsicWidth();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
        canvas.restore();
    }

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (!needToDraw(i, parent)) {
                continue;
            }
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + params.rightMargin;
            int right = left + mDivider.getIntrinsicWidth();
            int top = child.getTop() - params.topMargin;
            int bottom = child.getBottom() + params.bottomMargin;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
    }
}
