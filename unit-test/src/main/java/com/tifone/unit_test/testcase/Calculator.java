package com.tifone.unit_test.testcase;

import com.tifone.unit_test.CalculatorCallback;

/**
 * Created by tongkao.chen on 2018/3/21.
 */

public class Calculator implements CalculatorCallback {
    StringBuilder builder = new StringBuilder("");
    public int add(int num1, int num2) {
        return num1 + num2;
    }
    public int mov(int num1, int num2) {
        return num2;
    }
    public String append(String str) {
        if (builder.length() > 1) {
            builder.append(" - ");
        }
        builder.append(str);
        return builder.toString();
    }
    public String append2(String str) {
        StringBuilder builder = new StringBuilder("");
        if (builder.length() > 1) {
            builder.append(" - ");
        }
        builder.append(str);
        return builder.toString();
    }

    private CalculatorCallback callback;
    public void setCallback(CalculatorCallback callback) {
        this.callback = callback;
    }

    @Override
    public void start() {
        System.out.println("start");
    }
}
