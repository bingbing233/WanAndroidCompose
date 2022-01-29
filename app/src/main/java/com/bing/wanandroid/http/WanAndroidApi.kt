package com.bing.wanandroid.http

import com.bing.wanandroid.model.HomeResult
import com.bing.wanandroid.model.WxResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *  @author: liangbinghao
 *  @date:  2021/12/15 22:21
 *  @desc:
 */
interface WanAndroidApi {
    companion object {
        const val baseUrl = "https://www.wanandroid.com"
        fun create(): WanAndroidApi {
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WanAndroidApi::class.java)
        }
    }

    //首页文章列表
    @GET("article/list/{page}/json")
    suspend fun getHomeArticle(@Path("page") page: Int? = 0): HomeResult
    //获取公众号列表
    @GET("wxarticle/chapters/json ")
    suspend fun getWxOfficial(): WxResult
    //获取某个公众号的历史文章
    @GET("wxarticle/list/{id}}/{page}/json")
    suspend fun getWxArticle(@Path("id") id: Int, @Path("page") page: Int? = 0): HomeResult
    //获取广场文章
    @GET("user_article/list/{page}/json")
    suspend fun getSquareArticle(@Path("page")page: Int?=0):HomeResult
}