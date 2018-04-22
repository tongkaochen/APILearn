package com.tifone.ui.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tongkao.chen on 2018/4/13.
 */

public class BaseFragment extends Fragment {
    private String fragmentId = "unknown";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public String getFragmentId() {
        return fragmentId;
    }
    public void setFragmentId(String fragmentId) {
        this.fragmentId = fragmentId;
    }
}
