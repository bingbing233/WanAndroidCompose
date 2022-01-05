package com.bing.wanandroid.http

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bing.wanandroid.http.WanAndroidApi.Companion.create
import com.bing.wanandroid.model.Article
import com.bing.wanandroid.model.HomeResult
import com.bing.wanandroid.model.WxResult
import kotlinx.coroutines.flow.Flow

/**
 *  @author: liangbinghao
 *  @date:  2021/12/15 23:43
 *  @desc: 网络请求仓库类
 */
object WanRepository {
    private const val TAG = "WanRepository"
    private val wanAndroidApi: WanAndroidApi = create()

    fun getHomeArticle(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(15),
            pagingSourceFactory = { ArticlePagingSource{ wanAndroidApi.getHomeArticle(it) } }
        ).flow
    }

    fun getSquareArticle(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(15),
            pagingSourceFactory = {
                ArticlePagingSource{ wanAndroidApi.getSquareArticle(it)}
            }
        ).flow
    }

    suspend fun getHomeArticle(callback: WanCallback<HomeResult>) {
        kotlin.runCatching {
            wanAndroidApi.getHomeArticle()
        }.onSuccess {
            callback.onSuccess(it)
            Log.d(TAG, "getHomeArticle: $it")
        }.onFailure {
            callback.onFailed(it)
        }
    }

    suspend fun getWxOfficial(callback: WanCallback<WxResult>) {
        kotlin.runCatching {
            wanAndroidApi.getWxOfficial()
        }.onSuccess {
            callback.onSuccess(it)
            Log.d(TAG, "getWxOfficial: $it")
        }.onFailure {
            callback.onFailed(it)
        }
    }

    suspend fun getWxArticle(id: Int, page: Int? = null, callback: WanCallback<HomeResult>) {
        kotlin.runCatching {
            wanAndroidApi.getWxArticle(id, page)
        }.onSuccess {
            Log.d(TAG, "getWxArticle: $it")
            callback.onSuccess(it)
        }.onFailure {
            callback.onFailed(it)
        }
    }
}