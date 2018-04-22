package com.tifone.ui.animation;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.animation.Animation;
import android.widget.ImageView;

/**
 * Created by tongkao.chen on 2018/4/16.
 */

public class MyPropertyAnimation {
    public static void rotateAnimation(ImageView imageView) {
        //ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 180f, 0.4f, 0.6f, 0.2f, 0.0f);
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f, 0.8f, 0.2f, 0.4f, 0.8f);
        //ObjectAnimator animatorY = ObjectAnimator.ofFloat(imageView, "scaleY", 0f, 1f);
        animator.setDuration(2000);
        animator.start();
    }
    class MyEvaluator implements TypeEvaluator<Point> {

        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            int x = (int) (startValue.x + fraction * (endValue.x - startValue.x));
            int y = (int) ((float) (Math.sin(x * Math.PI / 180) * 100) + endValue.y / 2);
            Point point = new Point(x, y);
            return point;
        }
    }
}
