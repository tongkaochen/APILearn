package com.tifone.ui.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Created by tongkao.chen on 2018/4/19.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<ItemFragment> mFragments;
    public ViewPagerAdapter(FragmentManager fm, List<ItemFragment> fragments) {
        super(fm);
        mFragments = fragments;
        Log.e("tifone", "mFragments = " + mFragments.size());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }

    public void addItem(ItemFragment fragment) {
        mFragments.add(fragment);
        notifyDataSetChanged();
    }
    public void removeItem(ItemFragment fragment) {
        mFragments.remove(fragment);
        notifyDataSetChanged();
    }
}
