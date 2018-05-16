package com.tifone.ui.retrofit;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tongkao.chen on 2018/5/3.
 */

public class RetrofitUtils {


    public Retrofit getRetrofit(String baseUrl) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                // Gson converter
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    public RetrofitService getService() {
        return getRetrofit("http://toutiao.com/api/article/recent/?source=2&category=news_hot&as=A1D5D87595C3287").create(RetrofitService.class);
    }

    public void callMethod() {
        Call<ResponseBody> call = getService().getBlog(2);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        getService().getBlogs()
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Result<List<Blog>>>() {
                    @Override
                    public void accept(Result<List<Blog>> listResult) throws Exception {

                    }
                });
    }

    public String getOriginalJsonData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.toutiao.com")
                .build();
        DemoService service = retrofit.create(DemoService.class);
        Call<ResponseBody> call = service.getResult();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String jsonStr = new String(response.body().bytes());
                    String jsonMsg = response.message().toString();
                    Log.e("tifone", jsonStr);
                    Log.e("tifone", jsonMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        return null;
    }

}
