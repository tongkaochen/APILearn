package com.tifone.learn.photoswall.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.tifone.learn.photoswall.data.PhotoItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by tongkao.chen on 2018/3/14.
 */

public class BitmapUtils {
    public static Bitmap makeOriginalBitmap(Context context, Uri uri) throws IOException {
        ContentResolver resolver = context.getContentResolver();
        InputStream inputStream = resolver.openInputStream(uri);
        inputStream.close();
        return BitmapFactory.decodeStream(inputStream);
    }
    public static Bitmap makeBitmapWithSpecifiedSize(Context context, Uri uri,
                                                     int width, int height) throws IOException {
        ContentResolver resolver = context.getContentResolver();
        InputStream inputStream = resolver.openInputStream(uri);
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, bitmapOptions);

        int originalWidth = bitmapOptions.outWidth;
        int originalHeight = bitmapOptions.outHeight;
        if (originalWidth == -1 || originalHeight == -1) {
            return null;
        }

        int simpleSize = getSimpleSize(originalWidth, originalHeight, width, height);

        bitmapOptions.inJustDecodeBounds = false;
        bitmapOptions.inSampleSize = simpleSize;
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, bitmapOptions);
        inputStream.close();
        return  bitmap;
    }
    private static int getSimpleSize(int originalWidth, int originalHeight, int width, int height) {
        int simpleSize = 1;
        if (originalWidth > originalHeight && originalWidth > width) {
            simpleSize = Math.round(originalWidth/width);
        } else if (originalHeight > originalWidth && originalHeight > height) {
            simpleSize = Math.round(originalHeight/ height);
        }
        if (simpleSize <=0 ) {
            simpleSize = 1;
        }
        return simpleSize;

    }
    public static Bitmap getBitmapFromDisk(String path) {
        return BitmapFactory.decodeFile(path);
    }
    public static Bitmap getBitmapFromDiskWithSize(String path, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        int originalWidth = options.outWidth;
        int originalHeight = options.outHeight;
        int simpleSize = getSimpleSize(originalWidth, originalHeight, width, height);
        options.inJustDecodeBounds = false;
        options.inSampleSize = simpleSize;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }
    private static String generateFileName() {
        return UUID.randomUUID().toString();
    }
    public static String saveBitmapToFile(String path, Bitmap bitmap, PhotoItem item) {
        if (item != null) {
            String itemPath = item.getPath();
            if (!TextUtils.isEmpty(itemPath)) {
                File file = new File(itemPath);
                if (file.exists()) {
                    return itemPath;
                }
            }
        }
        if (bitmap == null) {
            return "";
        }
        String cachePath = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/Android/data/"  + path;
        Log.d("tifone-n" , cachePath);
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.d("tifone-n" , "unmounted");
        }
        File photosDir = new File(cachePath);
        if (!photosDir.exists()) {
            photosDir.mkdirs();
        }
        String photoName = generateFileName() + ".jpg";
        File photoFile = new File(cachePath + photoName);
        FileOutputStream fos = null;
        try {
            if (!photoFile.exists()) {
                Log.d("tifone-n" , "" + photoFile);
                photoFile.createNewFile();
            }
            fos = new FileOutputStream(photoFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return photoFile.getAbsolutePath();
    }

}
