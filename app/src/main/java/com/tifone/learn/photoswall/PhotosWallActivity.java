package com.tifone.learn.photoswall;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tifone.learn.R;
import com.tifone.learn.photoswall.data.PhotosWallRepository;
import com.tifone.learn.photoswall.data.local.LocalPhotosResource;
import com.tifone.learn.photoswall.presenter.PhotosWallPresenter;
import com.tifone.learn.photoswall.utils.Injection;
import com.tifone.learn.photoswall.view.PhotosWallFragment;
import com.tifone.learn.utils.ActivityUtils;
import com.tifone.learn.utils.PermissionUtils;

import java.util.concurrent.ExecutorService;

/**
 * Created by tongkao.chen on 2018/3/13.
 */

public class PhotosWallActivity extends Activity {
    private PhotosWallPresenter mPresenter;
    private PhotosWallRepository mRepository;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photos_main_activity);
        PhotosWallFragment photosWallFragment = (PhotosWallFragment) getFragmentManager()
                .findFragmentById(R.id.content_view);
        if (photosWallFragment == null) {
            photosWallFragment = PhotosWallFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    photosWallFragment, R.id.content_view);
        }
        mPresenter = PhotosWallPresenter.newInstance(this, photosWallFragment,
                Injection.providePhotosWallRepository(this));
    }
}
