package com.tifone.ui.okhttp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.tifone.ui.R;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by tongkao.chen on 2018/4/20.
 */

public class OkhttpActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_layout);
        webView = findViewById(R.id.web_view);
        OkHttpUtils utils = new OkHttpUtils();
        RequestHandler handler = new RequestHandler();
        //utils.getDataSynchronous(handler);
        utils.getDataAsync("http://toutiao.com/api/article/recent/?source=2&category=news_hot&as=A1D5D87595C3287");
    }

    class RequestHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            String response = (String) msg.obj;
            webView.loadData(response, "text/html", "utf-8");
            super.handleMessage(msg);
        }
    }
}
