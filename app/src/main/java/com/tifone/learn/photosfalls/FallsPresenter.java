package com.tifone.learn.photosfalls;

import android.graphics.Bitmap;

import com.tifone.learn.base.IBaseDataResource;
import com.tifone.learn.base.IBaseView;
import com.tifone.learn.photoswall.data.IPhotosWallRepository;
import com.tifone.learn.photoswall.data.IPhotosWallRepository.GetCallback;
import com.tifone.learn.photosfalls.IFallsContrast.IBaseFallsView;
/**
 * Created by tongkao.chen on 2018/3/16.
 */

public class FallsPresenter implements IFallsContrast.IBaseFallsPresenter {

    private IBaseFallsView mFragment;
    private IPhotosWallRepository mRepository;
    private FallsPresenter(IBaseFallsView fragment, IPhotosWallRepository repository) {
        mFragment = fragment;
        mRepository = repository;
        mFragment.setPresenter(this);
    }
    public static FallsPresenter newInstance(IBaseFallsView fragment, IPhotosWallRepository repository) {
        return new FallsPresenter(fragment, repository);
    }

    @Override
    public void getBitmap(String key) {
        mRepository.getItem(key, new GetCallback<Bitmap>() {
            @Override
            public void onDataGot(String key, Bitmap bitmap) {
                mFragment.updateImage(key, bitmap);
            }

            @Override
            public void noDataAvailable(String key) {
                mFragment.updateImage(key, null);
            }
        });
    }

    @Override
    public void addBitmap(String key, Bitmap bitmap) {

    }

    @Override
    public void cancelAll() {

    }

    @Override
    public void start() {

    }
}
