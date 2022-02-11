package com.bing.wanandroid.http

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bing.wanandroid.http.WanAndroidApi.Companion.create
import com.bing.wanandroid.model.Article
import com.bing.wanandroid.model.HomeResult
import com.bing.wanandroid.model.TreeResult
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
            pagingSourceFactory = { ArticlePagingSource { wanAndroidApi.getHomeArticle(it) } }
        ).flow
    }

    fun getSquareArticle(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(15),
            pagingSourceFactory = {
                ArticlePagingSource { wanAndroidApi.getSquareArticle(it) }
            }
        ).flow
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

    fun getWxArticle(id: Int): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(15),
            pagingSourceFactory = {
                ArticlePagingSource {
                    wanAndroidApi.getWxArticle(
                        id = id,
                        page = it
                    )
                }
            }
        ).flow
    }

    suspend fun getTreeResult(callback: WanCallback<TreeResult>){
        kotlin.runCatching {
            wanAndroidApi.getTree()
        }.onSuccess {
            callback.onSuccess(it)
        }.onFailure {
            callback.onFailed(it)
        }
    }
}