package com.example.zivcompose.http;

import android.os.Environment;
import com.example.zivcompose.config.HttpConst;
import com.example.zivcompose.http.interceptor.LoggingInterceptor;
import com.example.zivcompose.http.interceptor.NetCacheInterceptor;
import com.example.zivcompose.http.interceptor.OfflineCacheInterceptor;
import com.example.zivcompose.http.util.MyApiKotlin;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @program: ZivCompose
 * @description: 网络请求类
 * @author: Mr.Wang
 * @create: 2022-10-10 23:53
 **/

public class MyRetrofit {

    private static MyRetrofit retrofitClient;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;

    /**
     * @description 初始化Retrofit
     * @author ziv
     * @date 2022/9/21 23:02
     */
    private MyRetrofit() {
        initOkHttpClient();
        initRetrofit();
    }

    public static MyRetrofit getRetrofitClient() {
        if (retrofitClient == null) {
            synchronized (MyRetrofit.class) {
                if (retrofitClient == null) {
                    retrofitClient = new MyRetrofit();
                }
            }
        }
        return retrofitClient;
    }
    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(HttpConst.BASIC_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

    }
    private void initOkHttpClient() {
        okHttpClient = new OkHttpClient.Builder()
                //设置缓存文件路径，和文件大小
                .cache(new Cache(new File(Environment.getExternalStorageDirectory() + "/okhttp_cache/"), 50 * 1024 * 1024))
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                //设置在线和离线缓存
                .addInterceptor(OfflineCacheInterceptor.getInstance())
                .addNetworkInterceptor(NetCacheInterceptor.getInstance())
                .build();
    }


    public  MyApi myApi(){
        return retrofit.create(MyApi.class);
    }

    public MyApiKotlin myApiKotlin(){return  retrofit.create(MyApiKotlin.class);}

}
