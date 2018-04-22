package com.tifone.learn.photoswall.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.tifone.learn.photoswall.data.PhotoItem;

/**
 * Created by tongkao.chen on 2018/3/14.
 */
@Database(entities = {PhotoItem.class}, version =  1)
public abstract class PhotosDatabase extends RoomDatabase {
    private static PhotosDatabase INSTANCE;
    abstract public  PhotosDao photosDao();
    private static final Object mObject = new Object();

    public static PhotosDatabase getInstance(Context context) {
        synchronized (mObject) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        PhotosDatabase.class, "photos.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}
