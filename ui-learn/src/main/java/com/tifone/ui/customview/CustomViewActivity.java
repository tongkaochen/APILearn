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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.tifone.ui.R;
import com.tifone.ui.customview.demo.ViewDemoFragment;

/**
 * Created by tongkao.chen on 2018/4/22.
 */

public class CustomViewActivity extends AppCompatActivity {

    private static final int BASE_SHAPE_INDEX = 0;
    private static final int SLIDE_BAR_INDEX = 1;
    private static final int VIEW_DEMO_INDEX = 2;

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
            Log.e("tifone", "onCreate: " + showedFragmentKey);

            SampleFragment sampleFragment = (SampleFragment) mFragmentManager.findFragmentByTag(SampleFragment.class.getName());
            SlideBarFragment slideBarFragment = (SlideBarFragment) mFragmentManager.findFragmentByTag(SlideBarFragment.class.getName());
            ViewDemoFragment viewDemoFragment = (ViewDemoFragment) mFragmentManager.findFragmentByTag(ViewDemoFragment.class.getName());
            if (sampleFragment != null) {
                mFragmentList.put(BASE_SHAPE_INDEX, sampleFragment);
            }
            if (slideBarFragment != null) {
                mFragmentList.put(SLIDE_BAR_INDEX, slideBarFragment);
            }
            if (viewDemoFragment != null) {
                mFragmentList.put(VIEW_DEMO_INDEX, viewDemoFragment);
            }
        } else {
            showedFragmentKey = VIEW_DEMO_INDEX;
        }
        setSpinnerDefault();
        mSpinner.setVisibility(View.GONE);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //resolveItems(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_pref_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.base_view_menu:
                resolveItems(BASE_SHAPE_INDEX);
                break;
            case R.id.slide_bar_menu:
                resolveItems(SLIDE_BAR_INDEX);
                break;
            case R.id.pull_return_menu:
                resolveItems(VIEW_DEMO_INDEX);
                break;
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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
        hideFragment(mFragmentList.get(VIEW_DEMO_INDEX));
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
            case VIEW_DEMO_INDEX:
                if (mFragmentList.get(VIEW_DEMO_INDEX) == null) {
                    ViewDemoFragment fragment = ViewDemoFragment.newInstance();
                    mFragmentList.put(VIEW_DEMO_INDEX, fragment);
                    addFragment(fragment, ViewDemoFragment.class.getName());
                } else {
                    showFragment(VIEW_DEMO_INDEX);
                }
                break;
        }
    }
}
