package com.tifone.ui.recyclerview.adapter;

import com.tifone.ui.R;

import java.util.List;

/**
 * Created by tongkao.chen on 2018/4/2.
 */

public class QuickCardViewAdapter extends QuickAdapter<String> {
    public QuickCardViewAdapter(List<String> dataSet) {
        super(dataSet);
    }

    @Override
    public void convertView(QuickViewHolder holder, String data, int position) {
        holder.setText(R.id.card_item_tv, data);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.card_view_item;
    }
}
