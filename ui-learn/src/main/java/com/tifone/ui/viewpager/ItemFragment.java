package com.tifone.ui.viewpager;

import android.support.v4.app.Fragment;

/**
 * Created by tongkao.chen on 2018/4/19.
 */

public abstract class ItemFragment extends Fragment {
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
}
