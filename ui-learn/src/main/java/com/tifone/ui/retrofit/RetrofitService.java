package com.tifone.ui.retrofit;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by tongkao.chen on 2018/5/3.
 */

public interface RetrofitService {
    @GET("blog/{id}")
    Call<ResponseBody> getBlog(@Path("id") int id);
    @HTTP(method = "GET", path = "blog/{id}", hasBody = false)
    Call<ResponseBody> getBlog2(@Path("id") int id);

    // 使用Converter
    @GET("blog/{id}")
    Call<Result<Blog>> getBlog3(@Path("id") int id);

    @POST("blog")
    Call<Result<Blog>> createBlog(@Body Blog blog);

    // 使用Rxjava2
    @POST("/blog")
    Observable<Result<List<Blog>>> getBlogs();
}
