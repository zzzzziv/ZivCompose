package com.example.zivcompose.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;

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

    private String mAction;

    public BroadcastReceiveUtil (Context context, String action,OnKeyListener listener) {
        mContext = context;
        mListener = listener;
        mAction = action;
        mDeviceKeyReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().contains(mAction)){
                    //某些事件有返回值，可以根据自己业务编写
//                    String callback = intent.getStringExtra("关键字");
                    mListener.onClick();
                }
            }
        };
        mContext.registerReceiver(mDeviceKeyReceiver, new IntentFilter(mAction));
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
        //监听方法
        OnKeyListener onKeyListener = new OnKeyListener() {
            @Override
            public void onClick() {
                ShowToast.show("监测到监听");
            }
        };
        //注册了广播
        BroadcastReceiveUtil bcr = new BroadcastReceiveUtil(ShowToast.getContext(),action,onKeyListener);

        //注销广播
        bcr.unregister();

    }


}
