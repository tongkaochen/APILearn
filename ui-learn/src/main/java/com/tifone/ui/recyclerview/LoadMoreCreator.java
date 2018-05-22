package com.tifone.ui.recyclerview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tongkao.chen on 2018/5/21.
 */

public abstract class LoadMoreCreator {

    public abstract View getLoadMoreView(Context context, ViewGroup parent);
    public abstract void onLoad(int status);
    public abstract void onLoading();
    public abstract void onLoadFail();
    public abstract void onLoadComplete();

}
