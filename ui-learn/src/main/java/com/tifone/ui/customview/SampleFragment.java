package com.tifone.ui.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tifone.ui.R;

/**
 * Created by tongkao.chen on 2018/4/22.
 */

public class SampleFragment extends Fragment {
    private static final String FRAGMENT_KEY = "SampleFragment";

    public static SampleFragment newInstance() {
        SampleFragment fragment = new SampleFragment();
        Bundle bundle = new Bundle();
        bundle.putString(FRAGMENT_KEY, "SampleFragment");
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        String fragmentKey = bundle.getString(FRAGMENT_KEY);
        Log.e("tifone", "onCreate: " + fragmentKey);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sample_layout, container, false);
        return view;
    }
}
