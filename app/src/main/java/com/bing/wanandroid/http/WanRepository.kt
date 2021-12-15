package com.bing.wanandroid.http

import com.bing.wanandroid.http.WanAndroidApi.Companion.baseUrl
import com.bing.wanandroid.model.DataItem
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 *  @author: liangbinghao
 *  @date:  2021/12/15 23:43
 *  @desc:
 */
class WanRepository {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .build()
    private val wanAndroidApi: WanAndroidApi = retrofit.create(WanAndroidApi::class.java)

    suspend fun getHomeData(callback: WanCallback){
        kotlin.runCatching {
            wanAndroidApi.getHomeData()
        }.onSuccess {
            callback.onSuccess(it)
        }.onFailure {
            callback.onFailed(it)
        }
    }
}