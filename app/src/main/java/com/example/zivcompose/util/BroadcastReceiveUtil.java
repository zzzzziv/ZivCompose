package com.example.zivcompose.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: ZivCompose
 * @description: 广播监听工具类
 * @author: ziv
 * @create: 2022-10-12 20:22
 **/
public class BroadcastReceiveUtil {
    private Context mContext;
    private BroadcastReceiver mDeviceKeyReceiver = null;
    private OnKeyListener mListener;
    private List<String> mActions;
    public  String currAction;
    public BroadcastReceiveUtil(Context context){
        mContext = context;
    }

    public void setBroadcast(OnKeyListener listener, String... actions) {
        mListener = listener;
        mActions = Arrays.asList(actions);
        mDeviceKeyReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                currAction = intent.getAction();
                if (mActions.contains(currAction)){

                    //某些事件有返回值，可以根据自己业务编写
//                    String callback = intent.getStringExtra("关键字");

                    mListener.onClick();
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        mActions.forEach(filter::addAction);
        mContext.registerReceiver(mDeviceKeyReceiver, filter);
    }

    public void unregister(){
        if (mDeviceKeyReceiver != null){
            mContext.unregisterReceiver(mDeviceKeyReceiver);
            mDeviceKeyReceiver = null;
        }
    }

    public interface OnKeyListener{
        void onClick();
    }


    //用法
    public static void main(String[] args) {
        //监听事件
        String action ="android.intent.action.CLOSE_SYSTEM_DIALOGS";
        //注册了广播
        BroadcastReceiveUtil bcr = new BroadcastReceiveUtil(ShowToast.getContext());
        //监听方法
        OnKeyListener onKeyListener = new OnKeyListener() {
            @Override
            public void onClick() {
                switch (bcr.currAction){
                    case "":
                        ShowToast.show("监测到监听");
                        break;

                }


            }
        };
        bcr.setBroadcast(onKeyListener,action,"","");


        //注销广播
        bcr.unregister();

    }


}
