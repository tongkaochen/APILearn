package com.tifone.learn.photosfalls.view;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.tifone.learn.R;
import com.tifone.learn.photosfalls.IFallsContrast;

/**
 * Created by tongkao.chen on 2018/3/16.
 */

public class PhotosFallsFragment extends Fragment implements
        IFallsContrast.IBaseFallsView, View.OnTouchListener {
    private IFallsContrast.IBaseFallsPresenter mPresenter;
    private static final int PAGE_SIZE = 16;
    private int mFirstIndex = 0;
    private int mLastIndex = 0;
    private LinearLayout mColumnOne;
    private LinearLayout mColumnTwo;
    private LinearLayout mColumnThree;
    private ScrollView mScrollView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.falls_main_layout, null);
        mScrollView = (ScrollView) view.findViewById(R.id.falls_scroll_view);
        mColumnOne = (LinearLayout) view.findViewById(R.id.scroll_column_1);
        mColumnTwo = (LinearLayout) view.findViewById(R.id.scroll_column_2);
        mColumnThree = (LinearLayout) view.findViewById(R.id.scroll_column_3);
        return view;
    }

    @Override
    public void setPresenter(IFallsContrast.IBaseFallsPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void updateImage(String key, Bitmap bitmap) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            loadImages();
        }
        return false;
    }
    private void loadImages() {
        int scrollHeight = mScrollView.getHeight();
        int columnOneRemainHeight = scrollHeight - mColumnOne.getHeight();
        int columnTwoRemainHeight = scrollHeight - mColumnTwo.getHeight();
        int columnThreeRemainHeight = scrollHeight - mColumnThree.getHeight();

    }

    private LinearLayout getAvailableColumn(int remainOne, int remainTwo, int remainThree) {
        return null;
    }
}
