package com.example.zivcompose.util;

import android.content.Context;
import android.widget.Toast;

/**
 * @author ziv
 */
public class ShowToast {

    private static Context mContext;
    private static volatile ShowToast showToast;
    public static ShowToast getInstance() {
        if (showToast == null){
            synchronized (ShowToast.class){
                showToast = new ShowToast();
            }
        }
        return showToast;
    }
    public void init(Context context){
        mContext = context;
   }
   public static Context getContext(){
        return mContext;
   }

   public static void show(String str){
       Toast.makeText(mContext,str,Toast.LENGTH_LONG).show();
   }

}
