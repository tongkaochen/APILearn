package com.tifone.ui.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.tifone.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tongkao.chen on 2018/4/19.
 */

public class ViewPagerFragment extends Fragment {

    private TabLayout mTabLaout;
    private ViewPager mViewPager;
    private List<ItemFragment> mFragments;
    private ImageView mAddButton;
    ViewPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("tifone", "onCreateView");
        View view = inflater.inflate(R.layout.view_pager_fragment_layout, null);
        initView(view);
        initData();
        return view;
    }

    private void buildFragments() {
        mFragments = new ArrayList<ItemFragment>();
        for (int i = 0; i < 10; i++) {
            ViewListFragment fragment = new ViewListFragment();
            fragment.setTitle("Title " + (i + 1));
            fragment.setContentText("Title " + (i + 1));
            mFragments.add(fragment);
        }
    }
    private void addFragment(String title) {
        ViewListFragment fragment = new ViewListFragment();
        fragment.setTitle(title);
        fragment.setContentText(title);
        adapter.addItem(fragment);

    }

    private void initData() {
        buildFragments();
        adapter = new ViewPagerAdapter(getFragmentManager(), mFragments);
        mViewPager.setAdapter(adapter);
    }

    private void initView(View view) {
        mTabLaout = view.findViewById(R.id.chancel_tab_tl);
        mViewPager = view.findViewById(R.id.view_pager);
        mAddButton = view.findViewById(R.id.add_chanel);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment("Fragment new");
            }
        });

        mAddButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getContext(), "v " + v , Toast.LENGTH_SHORT).show();
                adapter.removeItem(mFragments.get(mViewPager.getCurrentItem()));
                return false;
            }
        });

        mTabLaout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLaout.setupWithViewPager(mViewPager);
    }
}
