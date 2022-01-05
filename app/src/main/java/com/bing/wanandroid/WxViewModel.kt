package com.bing.wanandroid

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bing.wanandroid.http.WanCallback
import com.bing.wanandroid.http.WanRepository
import com.bing.wanandroid.model.Article
import com.bing.wanandroid.model.HomeResult
import com.bing.wanandroid.model.WxOfficial
import com.bing.wanandroid.model.WxResult
import kotlinx.coroutines.launch

/**
 * 用于存放微信公众号的数据
 */

class WxViewModel: ViewModel() {


    //公众号
    var wxOfficial = ArrayList<WxOfficial>()

    //公众号文章
    val hongyang = ArrayList<Article>()
    var hongyangSize by mutableStateOf(0)
    val guolin = ArrayList<Article>()
    val yugangshuo = ArrayList<Article>()

    //公众号页面当前选中item
    var wxCurItem by mutableStateOf(0)

    fun getWxOfficial() {
        viewModelScope.launch {
            WanRepository.getWxOfficial(object : WanCallback<WxResult> {
                override fun onSuccess(result: WxResult) {
                    wxOfficial.clear()
                    wxOfficial.addAll(result.data)
                }

                override fun onFailed(t: Throwable) {
                }

            })
        }
    }

    fun getWxArticle(id:Int,page:Int?=null){
        viewModelScope.launch {
            WanRepository.getWxArticle(id,page,object :WanCallback<HomeResult>{
                override fun onSuccess(result: HomeResult) {
                    hongyang.clear()
                    hongyang.addAll(result.data.datas)
                    hongyangSize = hongyang.size
                }

                override fun onFailed(t: Throwable) {
                }

            })
        }
    }
}