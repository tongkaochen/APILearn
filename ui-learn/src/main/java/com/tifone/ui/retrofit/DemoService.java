package com.tifone.ui.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by tongkao.chen on 2018/5/4.
 */

public interface DemoService {
    // 定义网络请求接口
    // http://iu.snssdk.com/api/news/feed/v58/?
    @GET("http://iu.snssdk.com/api/news/feed/v58/?")
    Call<ResponseBody> getResult();
}
