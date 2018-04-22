package com.tifone.ui.viewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tifone.ui.R;

/**
 * Created by tongkao.chen on 2018/4/19.
 */

public class ViewListFragment extends ItemFragment {

    private TextView mTextView;
    private String mContextText;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_view_item, null);
        LinearLayout linearLayout = view.findViewById(R.id.card_view_layout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setPadding(16, 16, 16, 16);
        mTextView = view.findViewById(R.id.card_item_tv);
        mTextView.setText(mContextText);
        mTextView.setTextColor(Color.WHITE);
        return view;
    }
    public void setContentText(String content) {
        mContextText = content;
    }
}
