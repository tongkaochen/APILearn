package com.tifone.ui.customview;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.tifone.ui.R;

/**
 * Created by tongkao.chen on 2018/4/22.
 */

public class CustomViewActivity extends AppCompatActivity {

    private static final int BASE_SHAPE_INDEX = 0;
    private static final int SLIDE_BAR_INDEX = 1;

    private Spinner mSpinner;
    private FragmentManager mFragmentManager;
    private SparseArray<Fragment> mFragmentList;
    private static final String DEFAULT_FRAGMENT = "default_fragment";
    private static final String DEFAULT_SELECTION = "default_selection";
    private int showedFragmentKey;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view_activity_main_layout);
        mFragmentList = new SparseArray<>();
        mSpinner = (Spinner) findViewById(R.id.list_spinner);
        mFragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            showedFragmentKey = savedInstanceState.getInt(DEFAULT_FRAGMENT);

            SampleFragment sampleFragment = (SampleFragment) mFragmentManager.findFragmentByTag(SampleFragment.class.getName());
            SlideBarFragment slideBarFragment = (SlideBarFragment) mFragmentManager.findFragmentByTag(SlideBarFragment.class.getName());
            if (sampleFragment != null) {
                mFragmentList.put(BASE_SHAPE_INDEX, sampleFragment);
            }
            if (slideBarFragment != null) {
                mFragmentList.put(SLIDE_BAR_INDEX, slideBarFragment);
            }
        } else {
            showedFragmentKey = SLIDE_BAR_INDEX;
        }
        setSpinnerDefault();
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                resolveItems(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(DEFAULT_FRAGMENT, showedFragmentKey);
    }

    private void setSpinnerDefault() {
        resolveItems(showedFragmentKey);
        mSpinner.setSelection(showedFragmentKey);
    }

    private void addFragment(Fragment target, String tag) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (target == null) {
            return;
        }
        transaction.add(R.id.container, target, tag);
        transaction.commit();
    }

    private void hideAll() {
        hideFragment(mFragmentList.get(BASE_SHAPE_INDEX));
        hideFragment(mFragmentList.get(SLIDE_BAR_INDEX));
    }

    private void hideFragment(Fragment target) {
        if (target == null) {
            return;
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.hide(target);
        transaction.commit();
    }

    private void showFragment(int key) {
        showedFragmentKey = key;
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.show(mFragmentList.get(key));
        transaction.commit();
    }

    private void resolveItems(int position) {
        hideAll();
        switch (position) {
            case BASE_SHAPE_INDEX:
                //replaceFragment(SampleFragment.newInstance());
                if (mFragmentList.get(BASE_SHAPE_INDEX) == null) {
                    SampleFragment fragment = SampleFragment.newInstance();
                    mFragmentList.put(BASE_SHAPE_INDEX, fragment);
                    addFragment(fragment, SampleFragment.class.getName());
                } else {
                    showFragment(BASE_SHAPE_INDEX);
                }
                break;
            case SLIDE_BAR_INDEX:
                if (mFragmentList.get(SLIDE_BAR_INDEX) == null) {
                    SlideBarFragment fragment = SlideBarFragment.newInstance();
                    mFragmentList.put(SLIDE_BAR_INDEX, fragment);
                    addFragment(fragment, SlideBarFragment.class.getName());
                } else {
                    showFragment(SLIDE_BAR_INDEX);
                }
                break;
        }
    }
}
