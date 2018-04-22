package com.tifone.learn.photoswall.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Objects;

/**
 * Created by tongkao.chen on 2018/3/14.
 */
@Entity(tableName = "photos")
public class PhotoItem {
    @PrimaryKey
    @ColumnInfo(name = "entryid")
    @NonNull
    private final String mId;

    @ColumnInfo(name = "photo_local_uri")
    private final String mPath;

    @ColumnInfo(name = "file_name")
    private final String mFileName;

    public PhotoItem(String id, String path, String fileName) {
        mId = id;
        mPath = path;
        mFileName = fileName;
    }

    public String getId() {
        return mId;
    }
    public String getPath() {
        return mPath;
    }
    public String getFileName() {
        return mFileName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PhotoItem item = (PhotoItem) obj;
        boolean isEquals = Objects.equals(mId, item.getId()) &&
                Objects.equals(mPath, item.getPath());
        return isEquals;
    }

    @Override
    public String toString() {
        return "id = " + mId +
                ", uri = " + mPath;
    }
}
