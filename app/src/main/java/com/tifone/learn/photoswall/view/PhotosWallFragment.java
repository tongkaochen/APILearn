package com.tifone.learn.photoswall.view;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.GridView;
import android.widget.ImageView;

import com.tifone.learn.R;
import com.tifone.learn.photoswall.presenter.IPhotosPresenter;
import com.tifone.learn.photoswall.presenter.PhotosWallPresenter;
import com.tifone.learn.photoswall.utils.StaticsPhotosUrls;

/**
 * Created by tongkao.chen on 2018/3/13.
 */

public class PhotosWallFragment extends Fragment implements IPhotosView, OnScrollListener {
    private GridView mGridView;
    private IPhotosPresenter mPresenter;

    private int mFirstVisibleItem = 0;
    private int mVisibleItemCount = 0;
    private boolean isFirstLoad = true;
    private PhotosAdapter mPhotosAdapter;

    public static PhotosWallFragment newInstance() {
        return new PhotosWallFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photos_wall_home, container, false);
        mGridView = (GridView) view.findViewById(R.id.photos_wall_gv);
        mGridView.setOnScrollListener(this);
        mPhotosAdapter = new PhotosAdapter(getActivity(), 0, StaticsPhotosUrls.PHOTOS_URL, mPresenter);
        mGridView.setAdapter(mPhotosAdapter);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(IPhotosPresenter presenter) {
        mPresenter = presenter;
        if (mPhotosAdapter != null) {
            mPhotosAdapter.setPresenter(mPresenter);
        }
    }

    @Override
    public void setImageViewByTag(String tag, Bitmap bitmap) {
        Log.d("tifone-m", tag);
        if (mGridView == null || bitmap == null) {
            return;
        }
        ImageView imageView = (ImageView) mGridView.findViewWithTag(tag);
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE) {
            loadBitmaps(mFirstVisibleItem, mVisibleItemCount);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mFirstVisibleItem = firstVisibleItem;
        mVisibleItemCount = visibleItemCount;
        if (isFirstLoad && visibleItemCount > 0) {
            loadBitmaps(firstVisibleItem, visibleItemCount);
            isFirstLoad = false;
        }
    }
    private void loadBitmaps(int firstVisibleItem, int visibleItemCount) {
        Log.d("tifone-d", "firstVisibleItem = " + firstVisibleItem + ", visibleItemCount = " + visibleItemCount);
        String url = "";
        for (int i = firstVisibleItem; i < firstVisibleItem + visibleItemCount; i ++) {
            if (i >= 0) {
                url = StaticsPhotosUrls.PHOTOS_URL[i];
            } else {
                break;
            }
            mPresenter.loadPhoto(url);
        }
    }
}
