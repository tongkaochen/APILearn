package com.tifone.ui.okhttp;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * Created by tongkao.chen on 2018/4/20.
 */

public class OkHttpUtils {
    interface ResultCallback {
        void onResult(Object object);
    }
    public void getDataSynchronous(final Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                // 创建请求
                Request request = new Request.Builder()
                        .url("http://www.baidu.com")
                        .build();

                try {
                    // 请求响应
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.e("tifone", "response code = " + response.code());
                        Log.e("tifone", "response message = " + response.message());
                        //Log.e("tifone", "response res = " + response.body().string());
                        Message msg = Message.obtain();
                        msg.obj = response.body().string();
                        handler.sendMessage(msg);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public void getDataAsync(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.e("tifone", "response code = " + response.code());
                    Log.e("tifone", "response message = " + response.message());
                    Log.e("tifone", "response res = " + response.body().string());
                }
            }
        });
    }

    public void postData(final Handler handler) {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("username", "tongkao");
        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .post(builder.build())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Message msg = Message.obtain();
                    msg.obj = response.body().string();
                    handler.sendMessage(msg);
                }
            }
        });
    }

    public void postJson() {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String jsonData = "\"username\":\"tongkao.chen\",\"age\":27";
        RequestBody body = RequestBody.create(JSON, jsonData);
        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    public void postFile() {
        OkHttpClient client = new OkHttpClient();

        MediaType FILE = MediaType.parse("file/*");
        File fileName = new File("fileName");
        RequestBody body = RequestBody.create(FILE, fileName);
        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    public void postMultipartBody() {
        OkHttpClient client = new OkHttpClient();
        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("key", "123")
                .addFormDataPart("file", "fileName", RequestBody.create(MediaType.parse("file/*"), "fileName"))
                .build();
    }

    class MyRequestBody extends RequestBody {

        @Nullable
        @Override
        public MediaType contentType() {
            return null;
        }

        @Override
        public void writeTo(BufferedSink sink) throws IOException {
            FileInputStream fis = new FileInputStream(new File("fileName"));


        }
    }

    private String getcacheDir() {
        return "";
    }
    public void clientConfig() {
        File cacheDir = new File(getcacheDir(), "http_cache");
        Cache cache = new Cache(cacheDir, 10 * 1024 * 1024);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(10 * 1000, TimeUnit.MILLISECONDS)
                .writeTimeout(10 * 1000, TimeUnit.MILLISECONDS)
                .addInterceptor(new HttpHeadInterceptor())
                .addNetworkInterceptor(new NetworkInterceptor())
                .addInterceptor(new LoggerInterceptor())
                .cache(cache)
                .build();

    }

    class LoggerInterceptor implements  Interceptor {

        private static final String TAG = "OkHttp";
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            Log.d(TAG, String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            return null;
        }
    }

    class NetworkInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            return null;
        }
    }
    class HttpHeadInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            return null;
        }
    }
}
