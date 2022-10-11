package com.example.zivcompose.http.service;

import com.blankj.utilcode.util.StringUtils;
import com.example.zivcompose.entity.MusicVO;
import com.example.zivcompose.http.MyHttp;
import com.example.zivcompose.http.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @program: ZivCompose
 * @description: 测试服务,继承BaseService，可以直接调用http，如用使用帮助类则不需要。
 * @author: Mr.Wang
 * @create: 2022-10-11 00:25
 **/
public class TestService extends BaseService {

    /**
     * @description:普通写法，返回Json
     * @author: ziv
     * @date: 2022/10/11 22:58
     */
    public CompletableFuture<ResultJson> getMusic() {
        Map<String, String> map = new HashMap<>();
        CompletableFuture<ResultJson> future = new CompletableFuture<>();
        map.put("format", "json");
        http.Get("comments.163", map).enqueue(new JsonCallback(future));
        return future;
    }

    /**
     * @description:普通写法,返回实体
     * @author: ziv
     * @date: 2022/10/11 22:58
     */
    public CompletableFuture<ResultEntity<MusicVO>> getMusicEn() {
        Map<String, String> map = new HashMap<>();
        CompletableFuture<ResultEntity<MusicVO>> future = new CompletableFuture<>();
        map.put("format", "json");
        http.Get("comments.163", map).enqueue(new EntityCallback<>(future,MusicVO.class));
        return future;
    }


    /**
     * @description:利用帮助类，返回Json
     * @author: ziv
     * @date: 2022/10/11 22:58
     */
    public CompletableFuture<ResultJson> getMusicY() {
        CompletableFuture<ResultJson> future = new MyHttp.Get("comments.163")
                .queryKey("format")
                .queryParams("json")
                .toJson()
                .getJsonData();
        return future;
    }

    /**
     * @description:利用帮助类，转换实体
     * @author: ziv
     * @date: 2022/10/11 22:58
     */
    public CompletableFuture<ResultEntity<MusicVO>> getMusicEnY() {
        return new MyHttp.Get("comments.163")
                .queryKey("format")
                .queryParams("json")
                .toEntity(MusicVO.class)
                .getEntityData();
    }


}

