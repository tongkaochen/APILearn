package com.tifone.learn.photoswall.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.tifone.learn.R;
import com.tifone.learn.photoswall.data.PhotosLruCache;
import com.tifone.learn.photoswall.presenter.IPhotosPresenter;
import com.tifone.learn.photoswall.utils.StaticsPhotosUrls;

/**
 * Created by tongkao.chen on 2018/3/13.
 */

public class PhotosAdapter extends ArrayAdapter<String> {
    private GridView mPhotosWall;
    private IPhotosPresenter mPresenter;
    public PhotosAdapter(@NonNull Context context, int textViewResourceId,
                         @NonNull String[] objects, IPhotosPresenter presenter) {
        super(context, textViewResourceId, objects);
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return StaticsPhotosUrls.PHOTOS_URL[position];
    }
    public void setPresenter(IPhotosPresenter presenter) {
        mPresenter = presenter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final  String url = StaticsPhotosUrls.PHOTOS_URL[position];
        View view = null;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.photos_wall_item, null);
        } else {
            view = convertView;
        }
        final ImageView imageView = (ImageView) view.findViewById(R.id.photos_item);
        imageView.setTag(url);
        Log.d("tifone", "url = " + url);
        Bitmap bitmap = PhotosLruCache.getInstance().getPhotoByKey(url);
        if (bitmap == null) {
            imageView.setImageResource(R.drawable.ic_photo_empty);
        } else {
            imageView.setImageBitmap(bitmap);
        }
        return view;
    }

}
