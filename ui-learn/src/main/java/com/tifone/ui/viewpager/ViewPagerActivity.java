package com.tifone.ui.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.tifone.ui.R;

/**
 * Created by tongkao.chen on 2018/4/19.
 */

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_activity_layout);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        ViewPagerFragment fragment = (ViewPagerFragment) fm.findFragmentByTag(ViewPagerFragment.class.getName());
        if (fragment == null) {
            fragment = new ViewPagerFragment();
            transaction.add(R.id.container, fragment, ViewPagerFragment.class.getName());
            transaction.commit();
        }

    }
}
