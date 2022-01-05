package com.bing.wanandroid.http

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bing.wanandroid.model.Article
import com.bing.wanandroid.model.HomeResult
import java.lang.Exception

class ArticlePagingSource(private val getHomeResult: suspend (Int)->HomeResult) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try{
            val page = params.key?:1
            val homeResult = getHomeResult.invoke(page)
            val articleItem = homeResult.data.datas
            val preKey = if(page > 1) page -1 else null
            val nextKey = if(articleItem.isNotEmpty()) page +1 else null
            LoadResult.Page(articleItem,preKey,nextKey)
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}