package com.example.zivcompose.http.util;

import android.util.Log;
import com.example.zivcompose.entity.MusicVO;
import com.example.zivcompose.http.service.TestService;
import com.example.zivcompose.util.CodeUtil;
import com.example.zivcompose.util.ShowToast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class EntityCallback<T> implements Callback<ResultJson> {
    private String tag = "EntityCallback";
    private Class<T> bean;
    CompletableFuture<ResultEntity<T>> future;
    public EntityCallback(CompletableFuture<ResultEntity<T>> future,Class<T> bean){
        this.future =future;
        this.bean = bean;
    }

    @Override
    public void onResponse(Call<ResultJson> call, Response<ResultJson> response) {
        Log.d(tag,"成功进入异步回调");
        if (response.code()==200){
            String jsonStr = response.body().getData().toString();
            List<T> list = new ArrayList<>();
            if(!(jsonStr.startsWith("[") && jsonStr.endsWith("]"))){
                T t = new Gson().fromJson(response.body().getData(),bean);
                list.add(t);
            }else {
                list.addAll(Objects.requireNonNull(CodeUtil.json2listT(jsonStr, bean)));
            }
            future.complete(new ResultEntity<>(response.body().getCode(),response.body().getMsg(),list));
        }else {
            Log.d(tag,"错误码："+response.code());
            ShowToast.show("网络请求失败："+response.code());
            future.complete(new ResultEntity<>(response.code(), response.message(),new ArrayList<>()));
        }
    }

    @Override
    public void onFailure(Call<ResultJson> call, Throwable t) {
        ShowToast.show("系统出错："+t.getMessage());
        Log.d(tag,"系统出错:"+t);
        //-111系统出错
        future.complete(new ResultEntity<>(-111,t.getMessage(),new ArrayList<>()));
    }

}