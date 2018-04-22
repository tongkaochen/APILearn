package com.tifone.ui.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * Created by tongkao.chen on 2018/4/2.
 */

public class CustomSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private DrawThread mDrawThread;
    private Path mPath;
    public CustomSurfaceView(Context context) {
        super(context);
        init(context);
    }
    private void init(Context context) {
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        mDrawThread = new DrawThread(holder, context);
        mPath = new Path();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mDrawThread.start();
        mDrawThread.isRunning = false;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mDrawThread.isRunning = true;
        try {
            mDrawThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int xPoint = (int) event.getX();
        int yPoint = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(xPoint, yPoint);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(xPoint, yPoint);
                break;
        }
        return true;
    }

    class DrawThread extends Thread {
        private SurfaceHolder mHolder;
        private Context mContext;
        private Paint mPaint;
        private int radius = 0;
        public boolean isRunning = false;
        private boolean changed = false;
        private Random mRandom;
        private static final int DURATION = 30;

        public DrawThread(SurfaceHolder holder, Context context) {
            mHolder = holder;
            mContext = context;
            mPaint = new Paint();
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(3);
            mPaint.setColor(Color.GREEN);
            mRandom = new Random();
        }

        @Override
        public void run() {
            Canvas canvas = null;
            while (!isRunning) {
                try {
                    synchronized (mHolder) {
                        canvas = mHolder.lockCanvas();
                        //onDraw(canvas);
                        onDrawPath(canvas);
                        //Thread.sleep(1);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (canvas != null) {
                        mHolder.unlockCanvasAndPost(canvas);
                    }
                }

            }
        }
        private void onDrawPath(Canvas canvas) {
            canvas.drawColor(Color.WHITE);
            canvas.drawPath(mPath, mPaint);
        }

        private void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);
            canvas.translate(getWidth()/2, getHeight()/2);

            canvas.drawCircle(0, 0, radius, mPaint);
            int min = Math.min(getWidth(), getHeight());
            if (radius >= min/2) {
                changed = true;
            } else if (radius <= 0) {
                changed = false;
            }
            if (changed) {
                radius -= DURATION;
            } else {
                radius += DURATION;
            }
        }
    }
}
