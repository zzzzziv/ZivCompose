package com.example.zivcompose.mode;

/**
 * @program: ZivCompose
 * @description: 网易云音乐热门评论
 * @author: ziv
 * @create: 2022-10-11 17:39
 **/
public class MusicVO {
    private String name;
    private String url;
    private String picurl;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    @Override
    public String toString() {
        return "MusicVO{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", picurl='" + picurl + '\'' +
                '}';
    }

}
