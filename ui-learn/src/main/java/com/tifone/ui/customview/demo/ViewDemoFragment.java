package com.tifone.ui.customview.demo;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import com.tifone.ui.R;

/**
 * Created by tongkao.chen on 2018/4/25.
 */

public class ViewDemoFragment extends Fragment implements MyScrollView.OnScrollChangedListener {
    private static final String FRAGMENT_KEY = "view_demo_fragment_key";
    private MyScrollView mScrollView;
    private MyParentView mParentView;

    public static ViewDemoFragment newInstance() {
        Bundle bundle = new Bundle();
        bundle.putString(FRAGMENT_KEY, "ViewDemoFragment");
        ViewDemoFragment fragment = new ViewDemoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_demo_fragment_layout, container, false);
        mScrollView = view.findViewById(R.id.item_scroll_view);
        mParentView = (MyParentView) view.findViewById(R.id.parent_view);
        mScrollView.setOnScrollChangedListener(this);
        RelativeLayout relativeLayout = view.findViewById(R.id.todo2_layout);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofInt(mScrollView, "ScrollY", mScrollView.getScrollY(), 0);
                animator.setDuration(300);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.start();
            }
        });
        if (mScrollView.getScrollY() == 0) {
            handleScrolledToTop();
        }
        return view;
    }

    @Override
    public void onScrollChanged(int h, int v, int oldL, int oldV) {
        int scrollViewHeight = v + mScrollView.getHeight() - mScrollView.getPaddingTop() - mScrollView.getPaddingBottom();
        int totalHeight = mScrollView.getChildAt(0).getHeight();
        if (mScrollView.getScrollY() == 0) {
            // mScrollView.getScrollY()为scrollview滑动时Y轴的坐标，当为0时，处于顶部状态
            handleScrolledToTop();
        } else if (scrollViewHeight == totalHeight) {
            // scrollViewHeight是scrollview滑动时Y轴左边与所显示scrollview区域高度之和
            // 若没有padding属性，与scrollview中子view视图的总高度相等时，scrollview滑动到底部
            handleScrolledToBottom();
        } else {
            mParentView.setInterceptUp(false);
            mParentView.setInterceptDown(false);
        }

    }

    private void handleScrolledToBottom() {
        Log.e("tifone", "handleScrolledToBottom: ");
        mParentView.setInterceptUp(true);
        mParentView.setInterceptDown(false);

    }

    private void handleScrolledToTop() {
        Log.e("tifone", "handleScrolledToTop: ");
        mParentView.setInterceptDown(true);
        mParentView.setInterceptUp(false);
    }
}
