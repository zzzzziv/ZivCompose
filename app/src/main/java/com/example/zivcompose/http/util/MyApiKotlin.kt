package com.example.zivcompose.http.util

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface MyApiKotlin {

    @GET
    suspend fun get(@Url url: String?, @QueryMap map: MutableMap<String, String>): ResultJson
}