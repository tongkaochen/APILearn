package com.tifone.ui.calculator;

/**
 * Created by tongkao.chen on 2018/4/18.
 */

public interface IOperation {
    long operation(long left, long right);
    double operation(double left, double right);
}
