package com.tifone.ui.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by tongkao.chen on 2018/5/7.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FruitColor {
    enum FruitColors{BLUE, RED, GREED}

    FruitColors fruitColor() default FruitColors.GREED;
}
