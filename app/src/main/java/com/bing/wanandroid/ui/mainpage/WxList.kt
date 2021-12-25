package com.bing.wanandroid.ui.mainpage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bing.wanandroid.WanViewModel
import com.bing.wanandroid.WxViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun WxList() {
    val viewModel: WxViewModel = viewModel()
    var pagerState = rememberPagerState(0)
    val scope = rememberCoroutineScope()
    Column(
        Modifier
            .fillMaxSize()
            .padding(vertical = 50.dp)
    ) {
        WxTopTab(
            selectIndex = pagerState.currentPage,
            onSelectedChange = {
                scope.launch {
                    pagerState.animateScrollToPage(it)
                }
            })
        HorizontalPager(count = 13, state = pagerState,modifier = Modifier.padding(horizontal = 6.dp)) { currentPage ->
            when (currentPage) {
                0 -> WxArticleList()
                1 -> WxArticleList()
                2 -> WxArticleList()
                3 -> WxArticleList()
                4 -> WxArticleList()
                5 -> WxArticleList()
                6 -> WxArticleList()
                7 -> WxArticleList()
                8 -> WxArticleList()
                9 -> WxArticleList()
                10 -> WxArticleList()
                11 -> WxArticleList()
                12 -> WxArticleList()
            }
        }
    }
}

@Composable
fun WxTopTab(selectIndex: Int, onSelectedChange: (Int) -> Unit) {
    val viewModel: WxViewModel = viewModel()
    ScrollableTabRow(
        selectedTabIndex = selectIndex,
        Modifier.fillMaxWidth(),
        edgePadding = 10.dp,
    ) {
        Tab(
            selected = selectIndex == 0,
            onClick = { onSelectedChange(0) },
            text = { Text(text = "鸿洋") })
        Tab(
            selected = selectIndex == 1,
            onClick = { onSelectedChange(1) },
            text = { Text(text = "郭霖") })
        Tab(
            selected = selectIndex == 2,
            onClick = { onSelectedChange(2) },
            text = { Text(text = "玉刚说") })
        Tab(
            selected = selectIndex == 3,
            onClick = { onSelectedChange(3) },
            text = { Text(text = "承香墨影") })
        Tab(
            selected = selectIndex == 4,
            onClick = { onSelectedChange(4) },
            text = { Text(text = "Android群英传") })
        Tab(
            selected = selectIndex == 5,
            onClick = { onSelectedChange(5) },
            text = { Text(text = "code小生") })
        Tab(
            selected = selectIndex == 6,
            onClick = { onSelectedChange(6) },
            text = { Text(text = "谷歌开发者") })
        Tab(
            selected = selectIndex == 7,
            onClick = { onSelectedChange(7) },
            text = { Text(text = "奇卓社") })
        Tab(
            selected = selectIndex == 8,
            onClick = { onSelectedChange(8) },
            text = { Text(text = "美团技术团队") })
        Tab(
            selected = selectIndex == 9,
            onClick = { onSelectedChange(9) },
            text = { Text(text = "GcsSloop") })
        Tab(
            selected = selectIndex == 10,
            onClick = { onSelectedChange(10) },
            text = { Text(text = "互联网侦察") })
        Tab(
            selected = selectIndex == 11,
            onClick = { onSelectedChange(11) },
            text = { Text(text = "susion随心") })
        Tab(
            selected = selectIndex == 12,
            onClick = { onSelectedChange(12) },
            text = { Text(text = "程序亦菲猿") })
        Tab(
            selected = selectIndex == 13,
            onClick = { onSelectedChange(13) },
            text = { Text(text = "Gityuan") })
    }
}

@Composable
fun WxArticleList() {
//    val viewModel:WxViewModel = viewModel()
    val viewModel: WanViewModel = viewModel()
    LazyColumn(Modifier.fillMaxSize()) {
        repeat(viewModel.homeDataSize) {
            item {
                HomeItem(article = viewModel.homeData[it])
            }
        }
    }
}
