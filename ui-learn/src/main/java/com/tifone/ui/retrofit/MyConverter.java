package com.tifone.ui.retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by tongkao.chen on 2018/5/3.
 */

public interface MyConverter<F, T> {
    T convert(F value) throws IOException;

    abstract class Factory {
        public MyConverter<ResponseBody, ?> responseBodyMyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            return null;
        }
        public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            return null;
        }
    }

}
