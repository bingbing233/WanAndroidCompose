package com.bing.wanandroid

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bing.wanandroid.http.WanCallback
import com.bing.wanandroid.http.WanRepository
import com.bing.wanandroid.model.Article
import com.bing.wanandroid.model.HomeResult
import com.bing.wanandroid.model.WxOfficial
import com.bing.wanandroid.model.WxResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * 用于存放微信公众号的数据
 */

class WxViewModel : ViewModel() {

    //公众号
    var wxOfficial = ArrayList<WxOfficial>()

    fun getWxOfficial() {
        viewModelScope.launch {
            WanRepository.getWxOfficial(object : WanCallback<WxResult> {
                override fun onSuccess(result: WxResult) {
                    wxOfficial.clear()
                    wxOfficial.addAll(result.data)
                }

                override fun onFailed(t: Throwable) {}
            })
        }
    }

    fun getWxArticle(id:Int): Flow<PagingData<Article>> {
      return WanRepository.getWxArticle(id).cachedIn(viewModelScope)
    }

}