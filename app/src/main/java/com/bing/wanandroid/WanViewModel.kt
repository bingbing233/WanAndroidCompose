package com.bing.wanandroid

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bing.wanandroid.http.WanCallback
import com.bing.wanandroid.http.WanRepository
import com.bing.wanandroid.model.DataItem
import com.bing.wanandroid.model.DataX
import kotlinx.coroutines.launch

/**
 *  @author: liangbinghao
 *  @date:  2021/12/15 23:08
 *  @desc:
 */
class WanViewModel: ViewModel(){
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
    var selectedPage by mutableStateOf(0)
    var dataSize by mutableStateOf(0)
    var homeData = ArrayList<DataX>()

    fun getHomeData(){
        viewModelScope.launch {
            repository.getHomeData(object :WanCallback{
                override fun onSuccess(dataItem: DataItem) {
                    homeData.addAll(dataItem.data.datas)
                }

                override fun onFailed(t: Throwable) {
                    Log.e(TAG, "onFailed: ",t )
                }
            })
        }
    }
}