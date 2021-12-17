package com.bing.wanandroid.model

data class WxResult(
    val data: List<WxOfficial>,
    val errorCode: Int,
    val errorMsg: String
)

data class WxOfficial(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)