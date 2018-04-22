package com.tifone.ui.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by tongkao.chen on 2018/4/20.
 */

public class RxActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxUtils rxUtils = new RxUtils();
        rxUtils.create();
    }
}
