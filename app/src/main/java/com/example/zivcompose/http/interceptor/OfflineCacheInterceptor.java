package com.example.zivcompose.http.interceptor;

import com.blankj.utilcode.util.NetworkUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by ziv
 * on 2022/7/25.
 * 这个会比网络拦截器先 运行
 * 在没有网络连接的时候，会取的缓存
 * 重点 : 一般okhttp只缓存不大改变的数据适合get。（个人理解，无网络的时候可以将无网络有效期改长点）
 * 这里和前面的不同，立即设置，立即生效。例，你一个接口设置1个小时的离线缓存有效期，立即设置0.下次进入后，则无效
 */
public class OfflineCacheInterceptor implements Interceptor {
    private static OfflineCacheInterceptor offlineCacheInterceptor;
    //离线的时候的缓存的过期时间
    private int offlineCacheTime;

    private OfflineCacheInterceptor() {

    }

    public static OfflineCacheInterceptor getInstance() {
        if (offlineCacheInterceptor == null) {
            synchronized (OfflineCacheInterceptor.class) {
                if (offlineCacheInterceptor == null) {
                    offlineCacheInterceptor = new OfflineCacheInterceptor();
                }
            }
        }
        return offlineCacheInterceptor;
    }

    public void setOfflineCacheTime(int time) {
        this.offlineCacheTime = time;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (!NetworkUtils.isConnected()) {
            if (offlineCacheTime != 0) {
                int temp = offlineCacheTime;
                request = request.newBuilder()

                        .header("Cache-Control", "public, only-if-cached, max-stale=" + temp)
                        .build();
                offlineCacheTime = 0;
            } else {
                request = request.newBuilder()

                        .header("Cache-Control", "no-cache")
                        .build();
            }
        }
        return chain.proceed(request);
    }
}
