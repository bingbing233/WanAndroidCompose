package com.bing.wanandroid.http

import com.bing.wanandroid.model.DataItem
import retrofit2.http.GET

/**
 *  @author: liangbinghao
 *  @date:  2021/12/15 22:21
 *  @desc:
 */
interface WanAndroidApi {
    companion object{
        const val baseUrl = "https://www.wanandroid.com"
    }

    //首页文章列表
    @GET("article/list/0/json")
    suspend fun getHomeData():DataItem



}