package com.tifone.ui.calculator;

/**
 * Created by tongkao.chen on 2018/4/18.
 */

public class AddOperation implements IOperation {

    @Override
    public long operation(long left, long right) {
        return left + right;
    }

    @Override
    public double operation(double left, double right) {
        return left + right;
    }
}
