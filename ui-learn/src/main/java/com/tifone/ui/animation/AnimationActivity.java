package com.tifone.ui.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tifone.ui.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tongkao.chen on 2018/4/12.
 */

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int KEY_BASE = 1000;
    private static final int KEY_ALPHA_BTN = KEY_BASE + 1;
    private static final int KEY_TRANSLATE_BTN = KEY_BASE + 2;
    private static final int KEY_SCALE_BTN = KEY_BASE + 3;
    private static final int KEY_ROTATE_BTN = KEY_BASE + 4;
    LinearLayout linearLayout;
    Map<Integer, BaseFragment> mFragmentList;
    private static final String TAG = "tifone";
    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_layout);
        mFragmentList = new HashMap<>();
        /*setContentView(R.layout.animation_layout);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageResource(R.drawable.power_on_animation);
        AnimationDrawable animationDrawable  = (AnimationDrawable) imageView.getDrawable();
        //animationDrawable.setOneShot(true);
        animationDrawable.start();*/
        linearLayout = (LinearLayout) findViewById(R.id.button_layout);

        setupButtonLayout();

        mImageView = findViewById(R.id.alpha_image);

    }

    private void setupButtonLayout() {
        newButton(KEY_ALPHA_BTN, "Alpha animation");
        newButton(KEY_ROTATE_BTN, "Rotate animation");
        newButton(KEY_TRANSLATE_BTN, "Translate animation");
        newButton(KEY_SCALE_BTN, "Scale animation");
    }
    private void newButton(int id, String title) {
        ViewGroup.LayoutParams btnParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        Button button = new Button(this);
        button.setText(title);
        button.setLayoutParams(btnParams);
        button.setId(id);
        button.setOnClickListener(this);
        linearLayout.addView(button);
    }

    @Override
    public void onClick(View v) {
        Animation animation;
        switch (v.getId()) {
            case KEY_ALPHA_BTN:
                //Toast.makeText(this, "KEY_ALPHA_BTN clicked", Toast.LENGTH_SHORT).show();
                animation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
                animation.setInterpolator(new AccelerateDecelerateInterpolator());
                animation.setDuration(2000);
                mImageView.startAnimation(animation);
                break;
            case KEY_ROTATE_BTN:
                //Toast.makeText(this, "KEY_ROTATE_BTN clicked", Toast.LENGTH_SHORT).show();
                /*animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
                animation.setDuration(2000);
                animation.setInterpolator(new AccelerateDecelerateInterpolator());
                mImageView.startAnimation(animation);*/
                //rotateAnimation(mImageView);
                MyPropertyAnimation.rotateAnimation(mImageView);
                break;
            case KEY_SCALE_BTN:
                //Toast.makeText(this, "KEY_SCALE_BTN clicked", Toast.LENGTH_SHORT).show();
                animation = AnimationUtils.loadAnimation(this, R.anim.scale_anim);
                animation.setDuration(2000);
                animation.setInterpolator(new AccelerateDecelerateInterpolator());
                mImageView.startAnimation(animation);
                break;
            case KEY_TRANSLATE_BTN:
                //Toast.makeText(this, "KEY_TRANSLATE_BTN clicked", Toast.LENGTH_SHORT).show();
                animation = AnimationUtils.loadAnimation(this, R.anim.translate_anim);
                animation.setDuration(200);
                animation.setInterpolator(new AccelerateDecelerateInterpolator());
                animation.setRepeatCount(10);
                animation.setRepeatMode(Animation.REVERSE);
                mImageView.startAnimation(animation);
                break;
            default:
                return;
        }
    }

    private void rotateAnimation(ImageView imageView) {
        RotateAnimation animation = new RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF,
                0f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(2000);
        animation.setRepeatCount(-1);
        animation.setRepeatMode(Animation.REVERSE);
        imageView.startAnimation(animation);
    }
}
