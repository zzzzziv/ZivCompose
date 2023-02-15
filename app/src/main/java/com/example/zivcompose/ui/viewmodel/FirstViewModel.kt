package com.example.zivcompose.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.zivcompose.http.api.BaseApi

class FirstViewModel:ViewModel() {
    //使用mutableStateOf管理属性，可以让界面自动更新
    var username by mutableStateOf("")
    var password by mutableStateOf("")

    var value1 by mutableStateOf("")
    var value2 by mutableStateOf("")
    var value3 by mutableStateOf("")
    var value4 by mutableStateOf("")


    private val api = BaseApi()

    suspend fun httptest(){
        //利用了complete作为异步接收数据，res是返回值，err是错误。
        api.getMusic()?.whenComplete { res, err ->
            if (err==null){//没有抛出错误就获取res
                value1 = res.data.toString()
            }
        }
        api.getMusicEn()?.whenComplete { res, err ->
            if (err==null){//En代表返回是实体，具体能看实现
                value2 = res.data[0].toString()
            }
        }
        api.getMusicY()?.whenComplete { res, err ->
            if (err==null){//没有抛出错误就获取res
                if (res != null) {
                    value3 = res.data.toString()
                }
            }
        }
        api.getMusicEnY()?.whenComplete { res, err ->
            if (err==null){//没有抛出错误就获取res
                if (res != null) {
                    value4 = res.data[0].toString()
                }
            }
        }


    }
}