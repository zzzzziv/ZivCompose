package com.example.zivcompose;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.tencent.mmkv.MMKV;
import java.util.ArrayList;


public class APP extends Application {
    public static ArrayList<Activity> activities = new ArrayList<>();
    public Context context;
    @SuppressLint("StaticFieldLeak")
    public static Activity currentActivity;



    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Thread.setDefaultUncaughtExceptionHandler(restartHandler);//重启App，安全性
        MMKV.initialize(this);
        registerActivityListener();
    }

    public Thread.UncaughtExceptionHandler restartHandler = (thread, ex) -> {
        for (int i = 0; i < activities.size(); i++) {
            if (activities.get(i) != null) {
                activities.get(i).finish();
            }
        }
        restartApp();
        android.os.Process.killProcess(android.os.Process.myPid());
    };

    private void registerActivityListener() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.d("===", "创建了" + activity);
                currentActivity = activity;
                activities.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                currentActivity = activity;
            }

            @Override
            public void onActivityResumed(Activity activity) {
                currentActivity = activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.d("===", "不可见" + activity);
            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.d("===", "销毁了" + activity);
                if (null == activities || activities.size() == 0) {
                    return;
                }
                activities.remove(activity);
            }
        });
    }


    public Context getContext(){
        return context;
    }
    public void restartApp() {
        Intent intent = new Intent();
        intent.setClassName(getPackageName(), "com.example.zivcompose");
        PendingIntent restartIntent = PendingIntent.getActivity(
                getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager mgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
                restartIntent);

    }
}
