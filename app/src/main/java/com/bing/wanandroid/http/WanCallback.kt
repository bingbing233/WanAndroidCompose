package com.bing.wanandroid.http

import com.bing.wanandroid.model.HomeResult


/**
 *  @author: liangbinghao
 *  @date:  2021/12/15 23:54
 *  @desc:
 */
interface WanCallback<T> {
    fun onSuccess(result: T)
    fun onFailed(t:Throwable)
}