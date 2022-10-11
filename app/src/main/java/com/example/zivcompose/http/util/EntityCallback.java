package com.example.zivcompose.http.util;

import android.util.Log;
import com.example.zivcompose.util.ShowToast;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.concurrent.CompletableFuture;

public class EntityCallback<T> implements Callback<ResultJson> {
    private String tag = "EntityCallback";
    private T bean;
    CompletableFuture<ResultEntity<T>> future;
    public EntityCallback(CompletableFuture<ResultEntity<T>> future,T bean){
        this.future =future;
        this.bean = bean;
    }

    @Override
    public void onResponse(Call<ResultJson> call, Response<ResultJson> response) {
        Log.d(tag,"成功进入异步回调");
        if (response.code()==200){
            bean = (T) new Gson().fromJson(response.body().getData(), bean.getClass());
            future.complete(new ResultEntity<>(response.body().getCode(),response.body().getMsg(),bean));
        }else {
            Log.d(tag,"错误码："+response.code());
            ShowToast.show("网络请求失败："+response.code());
            future.complete(new ResultEntity<>(response.code(), response.message(),bean));
        }
    }

    @Override
    public void onFailure(Call<ResultJson> call, Throwable t) {
        ShowToast.show("系统出错："+t.getMessage());
        Log.d(tag,"系统出错:"+t);
        //-111系统出错
        future.complete(new ResultEntity<>(-111,t.getMessage(),bean));
    }
}