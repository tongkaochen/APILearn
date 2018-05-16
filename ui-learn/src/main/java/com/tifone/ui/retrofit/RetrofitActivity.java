package com.tifone.ui.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import retrofit2.Retrofit;

/**
 * Created by tongkao.chen on 2018/5/3.
 */

public class RetrofitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RetrofitUtils utils = new RetrofitUtils();
        utils.getOriginalJsonData();
    }
}
