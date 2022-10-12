package com.example.zivcompose.util;

import com.google.gson.Gson;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.List;

public class MMkvUtil {
    public static <T> void setArray(List<T> list, String name) {
        MMKV kv = MMKV.defaultMMKV();
        if (list == null || list.size() == 0) { //清空
            kv.putInt(name + "size", 0);
            int size = kv.getInt(name + "size", 0);
            for (int i = 0; i < size; i++) {
                if (kv.getString(name + i, null) != null) {
                    kv.remove(name + i);
                }
            }
        } else {
            kv.putInt(name + "size", list.size());
            for (int i = 0; i < list.size(); i++) {
                kv.remove(name + i);
                kv.remove(new Gson().toJson(list.get(i)));//删除重复数据 先删后加
                kv.putString(name + i, new Gson().toJson(list.get(i)));
            }
        }
        kv.sync();
    }


    /**
     * @description:加载sp的数组 name-自定义集合的名称
     * @author: ziv
     * @date: 2022/9/15 8:54
     * @param: [mContext, name, bean]
     * @return: java.util.ArrayList<T>
     **/
    public static <T> ArrayList<T> getArray(String name, Class<T> bean) {
        MMKV kv = MMKV.defaultMMKV();
        ArrayList<T> list = new ArrayList<T>();
        int size = kv.getInt(name + "size", 0);
        for (int i = 0; i < size; i++) {
            if (kv.getString(name + i, null) != null) {
                try {
                    list.add(new Gson().fromJson(kv.getString(name + i, null), bean));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    /**
     * @description:保存对象
     * @author: ziv
     * @date: 2022/9/15 9:11
     * @param: [name, bean]
     * @return: void
     **/
    public static <T> void setBean(String name, T bean) {
        MMKV kv = MMKV.defaultMMKV();
        if (bean != null) {
//            System.out.println("测试BeanToJson：" + new Gson().toJson(bean));
            kv.putString(name, new Gson().toJson(bean));
        } else {
            kv.putString(name, "");
        }

    }

    public static <T> T getBean(String name, Class<T> bean) {
        T t = null;
        MMKV kv = MMKV.defaultMMKV();
        String strBean = kv.getString(name, null);
        if (strBean != null) {
            t = new Gson().fromJson(strBean, bean);
        }
        return t;

    }

}
