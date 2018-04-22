package com.tifone.ui.surfaceview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tifone.ui.R;

/**
 * Created by tongkao.chen on 2018/4/2.
 */

public class ViewActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new AnimatorView(this));

    }

    static class AnimatorView extends View {
        Paint mPaint;
        int radius = 0;
        boolean changed = false;
        public AnimatorView(Context context) {
            super(context);
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.BLUE);
            mPaint.setStyle(Paint.Style.STROKE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.translate(200, 200);
            canvas.drawCircle(0, 0, radius, mPaint);
            if (radius >= 100) {
                changed = true;
            } if (radius <= 0){
                changed = false;
            }
            if (changed) {
                radius--;
            } else {
                radius++;
            }

            invalidate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.surface_view:
                Intent intent = new Intent();
                intent.setClass(ViewActivity.this, SurfaceViewActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
