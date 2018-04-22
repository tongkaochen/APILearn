package com.tifone.ui.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.tifone.ui.R;

/**
 * Created by tongkao.chen on 2018/4/13.
 */

public class AlphaAnimationFragment extends BaseFragment implements View.OnClickListener {
    public AlphaAnimationFragment() {
        super();
        setFragmentId("AlphaAnimationFragment");
    }
    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alpha_layout, container, false);
        imageView = view.findViewById(R.id.alpha_image);
        imageView.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_anim);
        animation.setDuration(2000);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        imageView.startAnimation(animation);
    }
}
