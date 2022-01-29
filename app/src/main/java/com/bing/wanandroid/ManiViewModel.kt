package com.bing.wanandroid

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bing.wanandroid.http.WanRepository
import com.bing.wanandroid.model.Article
import kotlinx.coroutines.flow.Flow

/**
 *  @author: liangbinghao
 *  @date:  2021/12/15 23:08
 *  @desc:
 */
class ManiViewModel : ViewModel() {
    private val TAG = "WanViewModel"
    val bottomTitles = arrayListOf("主页", "广场", "公众号", "体系", "项目")
    val bottomIcons = arrayListOf(
        R.drawable.ic_baseline_home_24,
        R.drawable.ic_baseline_android_24,
        R.drawable.ic_baseline_android_24,
        R.drawable.ic_baseline_list_alt_24,
        R.drawable.ic_baseline_folder_24
    )

    //当前选中的page
    var selectedPage by mutableStateOf(0)

    //是否展示webpage
    var showWebPage by mutableStateOf(false)

    //当前点击的item
    var curItem: Article? = null

    fun getHomeArticle(): Flow<PagingData<Article>> {
        return WanRepository.getHomeArticle().cachedIn(viewModelScope)
    }

    fun getSquareArticle(): Flow<PagingData<Article>> {
        return WanRepository.getSquareArticle().cachedIn(viewModelScope)
    }

}
