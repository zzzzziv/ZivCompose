package com.example.zivcompose.http;

import com.example.zivcompose.http.util.ResultJson;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

/**
 * @description:封装api方法，还缺文件上传。
 * @author: ziv
 * @date: 2022/10/11 23:03
 */
public interface MyApi {

    @GET()
    Call<ResultJson> Get(@Url String url, @QueryMap Map<String,String> map);

    @POST()
    Call<ResultJson> Post(@Url String url, @FieldMap Map<String,String> map);
}
