package com.example.zivcompose.http.service;

import com.example.zivcompose.http.MyApi;
import com.example.zivcompose.http.MyRetrofit;

/**
 * @program: ZivCompose
 * @description: 基础服务类
 * @author: ziv
 * @create: 2022-10-11 02:06
 **/
public class BaseService {
    MyRetrofit myRetrofit = MyRetrofit.getRetrofitClient();
    MyApi http = myRetrofit.myApi();
}
