package com.example.zivcompose.http.util;
import android.util.Log;
import com.example.zivcompose.util.ShowToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.concurrent.CompletableFuture;

public class JsonCallback implements Callback<ResultJson> {
    private String tag = "JsonCallback";
    CompletableFuture<ResultJson> future;
    public JsonCallback(CompletableFuture<ResultJson> future){
        this.future =future;
    }

    @Override
    public void onResponse(Call<ResultJson> call, Response<ResultJson> response) {
        Log.d(tag,"成功进入异步回调");
        if (response.code()==200){
            future.complete(response.body());
        }else {
            Log.d(tag,"错误码："+response.code());
            ShowToast.show("网络请求失败："+response.code());
            ResultJson result = new ResultJson();
            result.setCode(response.code());
            result.setMsg(response.message());
            future.complete(result);
        }
    }

    @Override
    public void onFailure(Call<ResultJson> call, Throwable t) {
        ShowToast.show("系统出错："+t.getMessage());
        Log.d(tag,"系统出错:"+t);
        ResultJson result = new ResultJson();
        //-111系统出错
        result.setCode(-111);
        result.setErrMsg(t.getMessage());
        future.complete(result);
    }
}