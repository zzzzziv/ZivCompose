package com.example.zivcompose.http.util;
import com.google.gson.JsonObject;

/**
 * @program: ZivCompose
 * @description: 统一返回封装
 * @author: ziv
 * @create: 2022-10-11 00:39
 **/
public class ResultEntity<T> implements ResponseResult {
    private int code;
    private String msg;
    private String errMsg;
    private T data;

    public ResultEntity(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", errMsg='" + errMsg + '\'' +
                ", data=" + data +
                '}';
    }

}
