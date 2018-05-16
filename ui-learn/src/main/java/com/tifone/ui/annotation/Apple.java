package com.tifone.ui.annotation;

/**
 * Created by tongkao.chen on 2018/5/7.
 */

public class Apple {

    @FruitName("Apple")
    private String name;

    @FruitColor(fruitColor = FruitColor.FruitColors.RED)
    private String color;

    @FruitProvider(id = 10010, name = "Big apple", address = "maoming.dianbai")
    private String provider;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;

    }
}
