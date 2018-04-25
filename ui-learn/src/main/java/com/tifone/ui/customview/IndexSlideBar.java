package com.tifone.ui.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.tifone.ui.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tongkao.chen on 2018/4/23.
 */

public class IndexSlideBar extends View {

    public static final String[] SOURCE = {"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private List<String> mIndexContent;
    private Paint mTextPaint;
    private Paint mHeighlightPaint;
    private OnItemSelectionChangedListener mListener;
    private int mIndex = -1;
    private Paint mShapePaint;
    private int mBackgroundColor;
    private int mFocusColor;
    private int mTextColor;

    public IndexSlideBar(Context context) {
        this(context, null);
    }

    public IndexSlideBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.IndexSlideBar, 0, 0);
        mBackgroundColor = ta.getColor(R.styleable.IndexSlideBar_bg_color, Color.GRAY);
        mTextColor = ta.getColor(R.styleable.IndexSlideBar_text_color, Color.WHITE);
        mFocusColor = ta.getColor(R.styleable.IndexSlideBar_focus_color, Color.BLUE);
        ta.recycle();
        initIndex();
    }

    private void initIndex() {
        mTextPaint = new Paint();
        mTextPaint.setColor(mTextColor);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mShapePaint = new Paint();
        mShapePaint.setColor(Color.parseColor("#fa7829"));
        mShapePaint.setStyle(Paint.Style.FILL);

        mHeighlightPaint = new Paint();
        mHeighlightPaint.setColor(mFocusColor);
        mHeighlightPaint.setAntiAlias(true);
        mHeighlightPaint.setStyle(Paint.Style.FILL);
        mIndexContent = Arrays.asList(SOURCE);
        mIndex = SlideBarUtils.getIndex(getContext());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        int singleHeight = height / (mIndexContent.size() + 1);

        // 绘制圆

        int r = Math.min(singleHeight / 2, width / 2);
        canvas.drawCircle(width / 2, singleHeight / 2, r / 1.5f, mShapePaint);

        // 绘制文本
        mTextPaint.setTextSize(singleHeight / 1.5f);
        int currentHeight = 0;
        int length = mIndexContent.size();
        for (int i = 0; i < length; i++) {
            currentHeight = singleHeight * (i + 1);
            int centerX = width / 2;
            int centerLine = currentHeight + singleHeight / 2;
            // 在基线上绘制文本
            float baseLine = centerLine + (mTextPaint.getFontMetrics().bottom - mTextPaint.getFontMetrics().top) / 2 - mTextPaint.getFontMetrics().bottom;

            // Highlight Selected
            if (mIndex == i) {
                int radius = singleHeight / 2;
                canvas.drawCircle(centerX, centerLine, radius, mHeighlightPaint);
            }

            canvas.drawText(SOURCE[i], centerX, baseLine, mTextPaint);
        }
    }

    float oldTouchX = -1f;
    float oldTouchY = -1f;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        Log.e("tifone", "touchX: " + touchX);
        Log.e("tifone", "touchY: " + touchY);
        Log.e("tifone", "getWidth: " + getWidth());
        Log.e("tifone", "getWidth: " + getHeight());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldTouchX = touchX;
                oldTouchY = touchY;
                break;
            case MotionEvent.ACTION_MOVE:
                oldTouchY = touchY;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        if (0f < oldTouchX && oldTouchX < getWidth()) {
            if (0f < oldTouchY) {
                resolveTextIndex();
            }
        }
        return true;
    }

    private void resolveTextIndex() {
        int index = Math.round(oldTouchY / (getHeight() / (mIndexContent.size() + 1)));
        Log.e("tifone", "index: " + index);
        if (index > 0 && index <= mIndexContent.size() && mIndex != (index - 1)) {
            mIndex = index - 1;
            SlideBarUtils.setIndex(getContext(), mIndex);
            Log.e("tifone", "index: " + index + ", value = " + mIndexContent.get(mIndex));
            if (mListener != null) {
                mListener.onItemSelected(mIndex, mIndexContent.get(mIndex));
            }
            invalidate();
        }
    }

    public void setOnSelectionChangedListener(OnItemSelectionChangedListener listener) {
        mListener = listener;
    }

    public interface OnItemSelectionChangedListener {
        void onItemSelected(int position, String key);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mIndex != -1) {
            if (mListener != null) {
                mListener.onItemSelected(mIndex, mIndexContent.get(mIndex));
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mListener = null;
    }
}
