package com.example.zivcompose.http.api

import android.util.Log
import com.example.zivcompose.http.MyHttp.Get
import com.example.zivcompose.http.MyHttp.Post
import com.example.zivcompose.http.MyRetrofit
import com.example.zivcompose.http.util.*
import com.example.zivcompose.mode.MusicVO
import java.util.concurrent.CompletableFuture

class BaseApi {
    private var myRetrofit: MyRetrofit = MyRetrofit.getRetrofitClient()
    var http: MyApiKotlin = myRetrofit.myApiKotlin()


    /**
     * @description:普通写法，返回Json
     * @author: ziv
     * @date: 2022/10/11 22:58
     */
    suspend fun getMusic(): CompletableFuture<ResultJson>? {
        val map: MutableMap<String, String> = HashMap()
        val future = CompletableFuture<ResultJson>()
        map["format"] = "json"
        val res = http.get("comments.163", map).data

        Log.d("====",res.toString())

        return future
    }

    /**
     * @description:普通写法,返回实体
     * @author: ziv
     * @date: 2022/10/11 22:58
     */
    suspend fun getMusicEn(): CompletableFuture<ResultEntity<MusicVO>>? {
        val map: MutableMap<String, String> = HashMap()
        val future = CompletableFuture<ResultEntity<MusicVO>>()
        map["format"] = "json"
        http.get("comments.163", map).data
        return future
    }


    /**
     * @description:利用帮助类，返回Json
     * @author: ziv
     * @date: 2022/10/11 22:58
     */
    suspend fun getMusicY(): CompletableFuture<ResultJson?>? {
        return Get("comments.163")
            .queryKey("format", "key2", "key3")
            .queryParams("json", "123", "321")
            .toJson()
            .jsonData
    }

    /**
     * @description:利用帮助类，转换实体
     * @author: ziv
     * @date: 2022/10/11 22:58
     */
    suspend fun getMusicEnY(): CompletableFuture<ResultEntity<MusicVO?>?>? {
        return Post("comments.163")
            .postKey("format")
            .postParams("json")
            .toEntity(MusicVO::class.java)
            .getEntityData()
    }

}