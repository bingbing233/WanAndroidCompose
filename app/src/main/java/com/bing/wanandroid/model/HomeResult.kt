package com.bing.wanandroid.model

/**
 *  @author: liangbinghao
 *  @date:  2021/12/15 22:28
 *  @desc: 帖子
 */

data class HomeResult(
    val data: Data,
    val errorCode: Int,
    val errorMsg: String
)

data class Data(
    val curPage: Int,
    val datas: List<HomeArticle>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

data class HomeArticle(
    val apkLink: String="",
    val audit: Int=0,
    val author: String="",
    val canEdit: Boolean=false,
    val chapterId: Int=0,
    val chapterName: String="",
    val collect: Boolean=false,
    val courseId: Int=0,
    val desc: String="",
    val descMd: String="",
    val envelopePic: String="",
    val fresh: Boolean=false,
    val host: String="",
    val id: Int=0,
    val link: String="",
    val niceDate: String="",
    val niceShareDate: String="",
    val origin: String="",
    val prefix: String="",
    val projectLink: String="",
    val publishTime: Long=0L,
    val realSuperChapterId: Int=0,
    val selfVisible: Int=0,
    val shareDate: Long=0,
    val shareUser: String="",
    val superChapterId: Int=0,
    val superChapterName: String="",
    val tags: List<Tag> = ArrayList<Tag>(),
    val title: String="",
    val type: Int=0,
    val userId: Int=0,
    val visible: Int=0,
    val zan: Int=0
)

data class Tag(
    val name: String,
    val url: String
)