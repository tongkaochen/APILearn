package com.tifone.learn.photoswall.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.tifone.learn.photoswall.data.PhotoItem;

import java.util.List;

/**
 * Created by tongkao.chen on 2018/3/14.
 */
@Dao
public interface PhotosDao {
    @Query("SELECT * FROM photos")
    List<PhotoItem> getPhotosItem();

    @Query("SELECT * FROM photos WHERE entryid = :id")
    PhotoItem getPhotoItem(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertItem(PhotoItem item);

    @Query("DELETE FROM photos WHERE entryid = :itemId")
    int deleteItemById(String itemId);

    @Delete
    int deleteItem(PhotoItem item);

    @Query("DELETE FROM photos")
    int deleteAllItems();
}
