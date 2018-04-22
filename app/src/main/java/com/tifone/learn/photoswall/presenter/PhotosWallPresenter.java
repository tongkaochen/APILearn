package com.tifone.learn.photoswall.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;

import com.tifone.learn.photoswall.data.IPhotosWallRepository;
import com.tifone.learn.photoswall.data.PhotosWallRepository;
import com.tifone.learn.photoswall.utils.StaticsPhotosUrls;
import com.tifone.learn.photoswall.view.IPhotosView;

import java.util.Set;

/**
 * Created by tongkao.chen on 2018/3/13.
 */

public class PhotosWallPresenter implements IPhotosPresenter {
    private IPhotosView mPhotosView;
    private IPhotosWallRepository<Bitmap> mRepository;
    private Context mContext;
    private PhotosWallPresenter(Context context, IPhotosView photosView, IPhotosWallRepository<Bitmap> repository) {
        mPhotosView = photosView;
        mRepository = repository;
        mContext = context.getApplicationContext();
        mPhotosView.setPresenter(this);
    }
    public static PhotosWallPresenter newInstance(Context context,
            IPhotosView photosView, IPhotosWallRepository<Bitmap> repository) {
        return new PhotosWallPresenter(context, photosView, repository);
    }
    @Override
    public void start() {

    }

    @Override
    public void addTask(String imageUrl) {

    }

    @Override
    public void removeTask(String imageUrl) {

    }

    @Override
    public void loadPhoto(String key) {
        Log.d("tifone-l", "loadPhoto = " + key);
        mRepository.getItem(key, new IPhotosWallRepository.GetCallback<Bitmap>() {
            @Override
            public void onDataGot(String key, Bitmap item) {
                Log.d("tifone", "loadPhoto onDataGot = " + item );
                setImage(key, item);
            }

            @Override
            public void noDataAvailable(String key) {
                Log.d("tifone", "loadPhoto noDataAvailable" );
                setImage(key, null);
            }
        });

    }

    @Override
    public void setImage(String key, Bitmap bitmap) {
        if (TextUtils.isEmpty(key) || bitmap == null) {
            return;
        }
        mPhotosView.setImageViewByTag(key, bitmap);
    }


}
