package com.tifone.ui.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.tifone.ui.R;

/**
 * Created by tongkao.chen on 2018/4/23.
 */

public class CircleView extends View {
    Paint mPaint;
    int mColor;
    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CircleView, 0, 0);
        mColor = ta.getColor(R.styleable.CircleView_circle_color, Color.GREEN);
        ta.recycle();
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(mColor);
        mPaint.setStrokeWidth(5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getMeasureSize(200, widthMeasureSpec, getLayoutParams().width);
        int height = getMeasureSize(300, heightMeasureSpec, getLayoutParams().height);
        setMeasuredDimension(width, height);
    }

    private int getMeasureSize(int defaultSize, int measureSpec, int layoutParams) {
        int result = defaultSize;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        switch (mode) {
            case MeasureSpec.AT_MOST:
                if (layoutParams == LinearLayout.LayoutParams.WRAP_CONTENT) {
                    result = defaultSize;
                    break;
                }
                result = size;
                break;
            case MeasureSpec.EXACTLY:
                result = size;
                break;
            case MeasureSpec.UNSPECIFIED:
                result = defaultSize;
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int r = Math.min(width - paddingLeft - paddingRight, height - paddingTop - paddingBottom) / 2;

        canvas.drawCircle(width/ 2, height / 2, r, mPaint);


    }
}
