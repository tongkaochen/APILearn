package com.tifone.ui;

import com.tifone.ui.calculator.CalculatorOperation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by tongkao.chen on 2018/4/18.
 */

public class TestCalculatorOperation{

    CalculatorOperation mOperation;

    @Before
    public void setup() throws Exception {
        mOperation = new CalculatorOperation();
    }

    @After
    public void release() throws Exception {
        mOperation = null;
    }

    @Test
    public void testAdd() throws Exception{
        //assertEquals(5 , 1 + 4);
        long result = mOperation.add(5, 1);
        assertEquals(result, 6);
        result = mOperation.add(-1999, 1000);
        assertEquals(result, -999);
        result = mOperation.add(-199900000000L, 900000000);
        assertEquals(result, -199000000000L);
    }

}
