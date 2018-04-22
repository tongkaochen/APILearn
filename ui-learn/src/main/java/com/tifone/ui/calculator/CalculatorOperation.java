package com.tifone.ui.calculator;

/**
 * Created by tongkao.chen on 2018/4/18.
 */

public class CalculatorOperation {
    AddOperation addOperation;
    public CalculatorOperation() {
        addOperation = new AddOperation();
    }

    public double add(double left, double right) {
       return addOperation.operation(left, right);
    }
    public long add(long left, long right) {
        return addOperation.operation(left, right);
    }
}
