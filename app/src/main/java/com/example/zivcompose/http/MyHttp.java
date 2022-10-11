package com.example.zivcompose.http;

import com.example.zivcompose.http.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @program: ZivCompose
 * @description: 网络请求帮助类
 * @author: ziv
 * @create: 2022-10-11 15:50
 **/
public class MyHttp {
    MyApi http = MyRetrofit.getRetrofitClient().myApi();
    private String url;//请求url
    private Map<String,String> map;//参数集合
    private CompletableFuture<? extends ResponseResult> future; //返回封装

    private boolean toEntity = false;//是否转换成对象

    /**
    * @Description: get，这里使用了强转，有点不优雅
    * @Author: ziv
    * @Date: 2022/10/11
    */
    private <T>MyHttp(Get get,Class<T> bean){
        this.url = get.url;
        this.map = get.map;
        this.toEntity = get.toEntity;
        if (toEntity){
            future = new CompletableFuture<ResultEntity<T>>();
            http.Get(url,map).enqueue(new EntityCallback<>((CompletableFuture<ResultEntity<T>>)future,bean));
        }else {
            future = new CompletableFuture<ResultJson>();
            http.Get(url,map).enqueue(new JsonCallback((CompletableFuture<ResultJson>) future));
        }
    }

    /**
     * @description:post
     * @author: ziv
     * @date: 2022/10/11 23:28
     */
    private <T>MyHttp(Post post,Class<T> bean){
        this.url = post.url;
        this.map = post.map;
        this.toEntity = post.toEntity;
        if (toEntity){
            future = new CompletableFuture<ResultEntity<T>>();
            http.Get(url,map).enqueue(new EntityCallback<>((CompletableFuture<ResultEntity<T>>)future,bean));
        }else {
            future = new CompletableFuture<ResultJson>();
            http.Get(url,map).enqueue(new JsonCallback((CompletableFuture<ResultJson>) future));
        }
    }



    public String getUrl() {
        return url;
    }

    public Map<String, String> getMap() {
        return map;
    }

    /**
     * @description:获取Json返回值
     * @author: ziv
     * @date: 2022/10/11 22:53
     */
    public CompletableFuture<ResultJson> getJsonData() {
        return (CompletableFuture<ResultJson>) future;
    }

    /**
     * @description:获取实体返回值
     * @author: ziv
     * @date: 2022/10/11 22:53
     */
    public <T>CompletableFuture<ResultEntity<T>> getEntityData() {return (CompletableFuture<ResultEntity<T>>)future;}

    public static class Get{
        private String url;//请求url

        private String[] queryKey;//查询关键字

        private String[] queryParams;//查询值
        private Map<String,String> map;//参数集合

        private boolean toEntity;//是否转换对象
        public Get(String url){
            this.url = url;
        }

        /**
        * @Description: 输入参数，可多个，要对应key值。
        * @Param: 参数
        * @Author: ziv
        * @Date: 2022/10/11
        */
        public Get queryParams(String ...params){
            this.queryParams = params;
            return this;
        }

        /**
        * @Description: 服务端规定的请求key值，与param对应。
        * @Param:key
        * @Author: ziv
        * @Date: 2022/10/11
        */
        public Get queryKey(String ...key){
            this.queryKey = key;
            return this;
        }
        
        /**
        * @Description: 转换成对应实体，需要给定实体对象
        * @Author: ziv
        * @Date: 2022/10/11
        */
        public <T>MyHttp toEntity(Class<T> bean){
            this.toEntity = true;
            Map<String,String> map = new HashMap<>();
            for (int i=0;i<queryKey.length;i++){
                map.put(queryKey[i],queryParams[i]);
            }
            this.map = map;
            return new MyHttp(this,bean);
        }

        /**
        * @Description: 返回json对象
        * @Author: ziv
        * @Date: 2022/10/11
        */
        public MyHttp toJson(){
            Map<String,String> map = new HashMap<>();
            for (int i=0;i<queryKey.length;i++){
                map.put(queryKey[i],queryParams[i]);
            }
            this.map = map;
            return new MyHttp(this,null);
        }

    }

    public static class Post{
        private String url;//请求url

        private String[] postKey;//查询关键字

        private String[] postParams;//查询值
        private Map<String,String> map;//参数集合

        private boolean toEntity;//是否转换对象
        public Post(String url){
            this.url = url;
        }

        /**
         * @Description: 输入参数，可多个，要对应key值。
         * @Param: 参数
         * @Author: ziv
         * @Date: 2022/10/11
         */
        public Post postParams(String ...params){
            this.postParams = params;
            return this;
        }

        /**
         * @Description: 服务端规定的请求key值，与param对应。
         * @Param:key
         * @Author: ziv
         * @Date: 2022/10/11
         */
        public Post postKey(String ...key){
            this.postKey = key;
            return this;
        }

        /**
         * @Description: 转换成对应实体，需要给定实体对象
         * @Author: ziv
         * @Date: 2022/10/11
         */
        public <T>MyHttp toEntity(Class<T> bean){
            this.toEntity = true;
            Map<String,String> map = new HashMap<>();
            for (int i=0;i<postKey.length;i++){
                map.put(postKey[i],postParams[i]);
            }
            this.map = map;
            return new MyHttp(this,bean);
        }

        /**
         * @Description: 返回json对象
         * @Author: ziv
         * @Date: 2022/10/11
         */
        public MyHttp toJson(){
            Map<String,String> map = new HashMap<>();
            for (int i=0;i<postKey.length;i++){
                map.put(postKey[i],postParams[i]);
            }
            this.map = map;
            return new MyHttp(this,null);
        }

    }

}
