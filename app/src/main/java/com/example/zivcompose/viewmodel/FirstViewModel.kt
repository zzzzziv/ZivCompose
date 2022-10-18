package com.example.zivcompose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.zivcompose.service.TestService

class FirstViewModel:ViewModel() {
    //使用mutableStateOf管理属性，可以让界面自动更新
    var username by mutableStateOf("")
    var password by mutableStateOf("")

    var value1 by mutableStateOf("")
    var value2 by mutableStateOf("")
    var value3 by mutableStateOf("")
    var value4 by mutableStateOf("")


    private val testService = TestService()

    fun httptest(){
        //利用了complete作为异步接收数据，res是返回值，err是错误。
        testService.music.whenComplete { res, err ->
            if (err==null){//没有抛出错误就获取res
                value1 = res.data.toString()
            }
        }
        testService.musicEn.whenComplete { res, err ->
            if (err==null){//En代表返回是实体，具体能看实现
                value2 = res.data[0].toString()
            }
        }
        testService.musicY.whenComplete { res, err ->
            if (err==null){//没有抛出错误就获取res
                value3 = res.data.toString()
            }
        }
        testService.musicEnY.whenComplete { res, err ->
            if (err==null){//没有抛出错误就获取res
                value4 = res.data[0].toString()
            }
        }


    }
}