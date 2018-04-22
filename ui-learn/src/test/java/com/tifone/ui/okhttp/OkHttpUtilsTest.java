package com.tifone.ui.okhttp;

import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.Response;

import static org.junit.Assert.*;

/**
 * Created by tongkao.chen on 2018/4/20.
 */
public class OkHttpUtilsTest {

    OkHttpUtils mUtils;
    @Before
    public void setup() throws Exception {
        mUtils = new OkHttpUtils();
    }

    @After
    public void release() throws Exception {
        mUtils = null;
    }

    @Test
    public void getDataSynchronous() throws Exception {

    }

}