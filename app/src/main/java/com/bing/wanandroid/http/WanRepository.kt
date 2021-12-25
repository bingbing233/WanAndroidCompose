package com.bing.wanandroid.http

import android.util.Log
import com.bing.wanandroid.http.WanAndroidApi.Companion.baseUrl
import com.bing.wanandroid.model.HomeResult
import com.bing.wanandroid.model.WxResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *  @author: liangbinghao
 *  @date:  2021/12/15 23:43
 *  @desc: 网络请求仓库类
 */
class WanRepository {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val wanAndroidApi: WanAndroidApi = retrofit.create(WanAndroidApi::class.java)

    suspend fun getHomeArticle(callback: WanCallback<HomeResult>){
        kotlin.runCatching {
            wanAndroidApi.getHomeArticle()
        }.onSuccess {
            callback.onSuccess(it)
            Log.d("binghao", "getHomeArticle: $it")
        }.onFailure {
            callback.onFailed(it)
        }
    }

    suspend fun getWxOfficial(callback: WanCallback<WxResult>){
        kotlin.runCatching {
            wanAndroidApi.getWxOfficial()
        }.onSuccess {
            callback.onSuccess(it)
        }.onFailure {
            callback.onFailed(it)
        }
    }

    suspend fun getWxArticle(id:Int,page:Int?=null,callback: WanCallback<HomeResult>){
        kotlin.runCatching {
            wanAndroidApi.getWxArticle(id,page)
        }.onSuccess {
            Log.d("binghao", "getWxArticle: $it")
            callback.onSuccess(it)
        }.onFailure {
            callback.onFailed(it)
        }
    }
}