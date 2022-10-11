package com.example.zivcompose.util;

import com.blankj.utilcode.util.StringUtils;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: ZivCompose
 * @description: 编码工具类
 * @author: ziv
 * @create: 2022-10-12 02:53
 **/
public class CodeUtil {
    public static final String START_ARRAY = "[";
    public static final String END_ARRAY = "]";
    /**
     * Gson获取json中的数组节点.转换为list集合<泛型>.<br/>
     * @param <T> 声明一个泛型T
     * @param <T>list<T> 指明该方法的返回值为泛型T代表类型的list集合
     * @param jsonStr json中的数组节点
     * @param tC 指明泛型T的具体类型，用来创建泛型T代表的类的对象
     * @return 1:正常返回泛型代表具体类型的list集合，2:转换错误则返回null
     * */
    @SuppressWarnings("unchecked")
    public static final <T> List<T> json2listT(String jsonStr, Class<T> tC) {
        //json字符串不能为空
        if(StringUtils.isEmpty(jsonStr)) return null;
        //json字符串必须为数组节点类型
        if(!(jsonStr.startsWith(START_ARRAY) && jsonStr.endsWith(END_ARRAY))) return null;
        List<T> listT = null;
        try {
            //创建泛型对象
            T t =  tC.newInstance();
            //利用类加载加载泛型的具体类型
            Class<T> classT = (Class<T>) Class.forName(t.getClass().getName());
            List<Object> listObj = new ArrayList<Object>();
            //将数组节点中json字符串转换为object对象到Object的list集合
            listObj = new GsonBuilder().create().fromJson(jsonStr, new TypeToken<List<Object>>(){}.getType());
            //转换未成功
            if(listObj == null || listObj.isEmpty()) return null;
            listT = new ArrayList<T>();
            //将Object的list中的的每一个元素中的json字符串转换为泛型代表的类型加入泛型代表的list集合返回
            for (Object obj : listObj) {
                T perT = new GsonBuilder().create().fromJson(obj.toString(), classT);
                listT.add(perT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listT;
    }
}
