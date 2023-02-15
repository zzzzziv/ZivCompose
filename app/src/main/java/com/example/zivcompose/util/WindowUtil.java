package com.example.zivcompose.util;

import android.content.Context;

/**
 * @program: ZivCompose
 * @description: 窗口工具类
 * @author: ziv
 * @create: 2022-10-13 00:50
 **/
public class WindowUtil {
    public static int getWindowWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getWindowHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

}
