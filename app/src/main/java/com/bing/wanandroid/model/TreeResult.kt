package com.bing.wanandroid.model

data class TreeResult(
    val `data`: List<TreeData>,
    val errorCode: Int,
    val errorMsg: String
)

data class TreeData(
    val children: List<TreeChildren>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)

data class TreeChildren(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)