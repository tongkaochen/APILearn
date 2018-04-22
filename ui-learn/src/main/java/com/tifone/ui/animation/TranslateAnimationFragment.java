package com.tifone.ui.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tifone.ui.R;

/**
 * Created by tongkao.chen on 2018/4/13.
 */

public class TranslateAnimationFragment extends BaseFragment {
    public TranslateAnimationFragment() {
        super();
        setFragmentId("TranslateAnimationFragment");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.translate_layout, container, false);
        return view;
    }

}
