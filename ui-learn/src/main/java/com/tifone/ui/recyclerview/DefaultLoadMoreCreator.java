package com.tifone.ui.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tifone.ui.R;

/**
 * Created by tongkao.chen on 2018/5/22.
 */

public class DefaultLoadMoreCreator extends  LoadMoreCreator{
    @Override
    public View getLoadMoreView(Context context, ViewGroup parent) {
        View loadMoreView = LayoutInflater.from(context).inflate(R.layout.recycler_view_refresh_footer, parent, false);
        return loadMoreView;
    }

    @Override
    public void onLoad(int status) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoadFail() {

    }

    @Override
    public void onLoadComplete() {

    }
}
