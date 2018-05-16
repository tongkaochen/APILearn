package com.tifone.ui.annotation;

import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by tongkao.chen on 2018/5/7.
 */

public class FruitUtils {

    public static String[] setupFruitInfo(Class<?> cls) {
        String nameStr = "Fruit name: ";
        String colorStr = "Fruit color: ";
        String providerStr = "Fruit provider: ";

        Field[] fields = cls.getDeclaredFields();
        Log.e("tifone", "" + fields.length);
        for (Field field : fields) {
            if (field.isAnnotationPresent(FruitName.class)) {
                FruitName fruitName = (FruitName) field.getAnnotation(FruitName.class);
                nameStr += fruitName.value();
                Log.e("tifone", fruitName.value());
            }
            if (field.isAnnotationPresent(FruitColor.class)) {
                FruitColor color = (FruitColor) field.getAnnotation(FruitColor.class);
                colorStr += color.fruitColor();
            }
            if (field.isAnnotationPresent(FruitProvider.class)) {
                FruitProvider provider = (FruitProvider) field.getAnnotation(FruitProvider.class);
                providerStr += ("id: " + provider.id() + " name : "+ provider.name() + " address: " + provider.address());
            }
        }
        return new String[] {nameStr, colorStr, providerStr};
    }


}
