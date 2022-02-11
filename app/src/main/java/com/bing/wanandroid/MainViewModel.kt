package com.bing.wanandroid

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bing.wanandroid.http.WanCallback
import com.bing.wanandroid.http.WanRepository
import com.bing.wanandroid.model.Article
import com.bing.wanandroid.model.TreeData
import com.bing.wanandroid.model.TreeResult
import com.bing.wanandroid.ui.mainPage.MainPage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 *  @author: liangbinghao
 *  @date:  2021/12/15 23:08
 *  @desc:
 */
class MainViewModel : ViewModel() {
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

    //当前点击的item
    var curItem: Article? = null

    //当前页面
    var pageState = mutableStateOf(PageState.MainPage)

    fun getHomeArticle(): Flow<PagingData<Article>> {
        return WanRepository.getHomeArticle().cachedIn(viewModelScope)
    }

    fun getSquareArticle(): Flow<PagingData<Article>> {
        return WanRepository.getSquareArticle().cachedIn(viewModelScope)
    }

    var treeResult = ArrayList<TreeData>()
    fun getTreeResult(){
        viewModelScope.launch {
            WanRepository.getTreeResult(object :WanCallback<TreeResult>{
                override fun onSuccess(result: TreeResult) {
                    treeResult.clear()
                    treeResult.addAll(result.data)
                }

                override fun onFailed(t: Throwable) {
                    Log.e(TAG, "onFailed: ",t )
                }
            })
        }
    }

}

enum class PageState{
    MainPage,
    WebPage,
    LoginPage
}
