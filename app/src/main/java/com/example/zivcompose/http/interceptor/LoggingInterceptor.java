package com.example.zivcompose.http.interceptor;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;

/**
 * 日志拦截器
 */
public class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor.intercept(chain);
    }

}
