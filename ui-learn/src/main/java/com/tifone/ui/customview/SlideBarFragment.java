package com.tifone.ui.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tifone.ui.R;

/**
 * Created by tongkao.chen on 2018/4/23.
 */

public class SlideBarFragment extends Fragment implements IndexSlideBar.OnItemSelectionChangedListener {
    private static final String FRAGMENT_ID = "fragment_id";
    private TextView mIndexShowedTv;
    private IndexSlideBar mIndexSlideBar;

    public static SlideBarFragment newInstance() {
        Bundle bundle = new Bundle();
        bundle.putString(FRAGMENT_ID, "SlideBarFragment");
        SlideBarFragment fragment = new SlideBarFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        String key = bundle.getString(FRAGMENT_ID);
        Log.e("tifone", "onCreate: " + key);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index_slide_layout, container, false);
        mIndexShowedTv = view.findViewById(R.id.selected_index);
        mIndexSlideBar = view.findViewById(R.id.slide_bar);
        mIndexSlideBar.setOnSelectionChangedListener(this);
        return view;
    }

    @Override
    public void onItemSelected(int position, String key) {
        mIndexShowedTv.setText(key);
    }
}
