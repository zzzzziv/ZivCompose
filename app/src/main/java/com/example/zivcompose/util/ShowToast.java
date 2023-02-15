package com.example.zivcompose.util;

import android.content.Context;
import android.widget.Toast;

/**
 * @author ziv
 */
public class ShowToast {


    public static void show(String str){
        Toast.makeText(AppGlobalUtils.getApplication(),str,Toast.LENGTH_SHORT).show();
    }


    public static void showL(String str){
        Toast.makeText(AppGlobalUtils.getApplication(),str,Toast.LENGTH_LONG).show();
    }

}

