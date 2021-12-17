package com.bing.wanandroid

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bing.wanandroid.http.WanCallback
import com.bing.wanandroid.http.WanRepository
import com.bing.wanandroid.model.HomeResult
import com.bing.wanandroid.model.HomeArticle
import com.bing.wanandroid.model.WxOfficial
import com.bing.wanandroid.model.WxResult
import com.google.accompanist.swiperefresh.SwipeRefreshState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 *  @author: liangbinghao
 *  @date:  2021/12/15 23:08
 *  @desc:
 */
class WanViewModel : ViewModel() {
    private val repository = WanRepository()
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

    //数据量变化时重组
    var homeDataSize by mutableStateOf(0)
    var wxDataSize by mutableStateOf(0)

    //主页
    var homeData = ArrayList<HomeArticle>()

    //公众号
    var wxData = ArrayList<WxOfficial>()

    //是否展示webpage
    var showWebPage by mutableStateOf(false)

    //当前点击的item
    var curItem: HomeArticle? = null

    //是否正在刷新
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    fun getHomeArticle() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            repository.getHomeArticle(object : WanCallback<HomeResult> {
                override fun onSuccess(result: HomeResult) {
                    homeData.clear()
                    homeData.addAll(result.data.datas)
                    homeDataSize = homeData.size
                    viewModelScope.launch {
                        _isRefreshing.emit(false)
                    }
                }

                override fun onFailed(t: Throwable) {
                    viewModelScope.launch {
                        _isRefreshing.emit(false)
                    }
                }
            })
        }
    }

    fun getWxArticle() {
        viewModelScope.launch {
            repository.getWxArticle(object : WanCallback<WxResult> {
                override fun onSuccess(result: WxResult) {
                    wxData.clear()
                    wxData.addAll(result.data)
                    wxDataSize = wxData.size
                }

                override fun onFailed(t: Throwable) {
                }

            })
        }
    }
}