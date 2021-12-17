package com.bing.wanandroid.http

import com.bing.wanandroid.model.HomeResult
import com.bing.wanandroid.model.WxResult
import retrofit2.http.GET
import retrofit2.http.Path

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
    @GET("article/list/{page}/json")
    suspend fun getHomeArticle(@Path("page") page:Int?=0): HomeResult

    //公众号
    @GET("wxarticle/chapters/json ")
    suspend fun getWxArticle():WxResult

}