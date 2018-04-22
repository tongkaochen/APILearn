package com.tifone.ui.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by tongkao.chen on 2018/4/20.
 */

public class RxActivity extends AppCompatActivity{
    RxUtils rxUtils;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rxUtils = new RxUtils();
        //rxUtils.create();
        //rxUtils.zip();
        //rxUtils.interval();
        //rxUtils.repeat();
        //rxUtils.range();
        rxUtils.fromArray();
        rxUtils.fromIterable();
    }

    @Override
    protected void onPause() {
        super.onPause();
        rxUtils.unSubscribe();
    }
}
