package com.bing.wanandroid.http

import com.bing.wanandroid.model.DataItem

/**
 *  @author: liangbinghao
 *  @date:  2021/12/15 23:54
 *  @desc:
 */
interface WanCallback {
    fun onSuccess(dataItem: DataItem)
    fun onFailed(t:Throwable)
}