package com.example.zivcompose.util;

import android.annotation.SuppressLint;
import android.app.Application;

import com.example.zivcompose.APP;

import java.lang.reflect.Method;


public class AppGlobalUtils {
    @SuppressLint("StaticFieldLeak")
    private static APP myApp;
    public static Application getApplication() {
        if(myApp==null){
            try {
                @SuppressLint({"DiscouragedPrivateApi", "PrivateApi"})
                Method currentApplication = Class.forName("android.app.ActivityThread").
                        getDeclaredMethod("currentApplication");
                myApp = (APP) currentApplication.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return myApp;
    }
}
