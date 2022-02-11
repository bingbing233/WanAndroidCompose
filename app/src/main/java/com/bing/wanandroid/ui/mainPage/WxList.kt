package com.bing.wanandroid.ui.mainPage

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bing.wanandroid.MainViewModel
import com.bing.wanandroid.PageState
import com.bing.wanandroid.WxViewModel
import com.bing.wanandroid.model.Article
import com.google.accompanist.pager.*
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun WxList() {
    val wxViewModel: WxViewModel = viewModel()
    val mainViewModel: MainViewModel = viewModel()
    val official = wxViewModel.wxOfficial
    val articleList = HashMap<Int, LazyPagingItems<Article>>()

    repeat(official.size) {
        articleList[official[it].id] =
            wxViewModel.getWxArticle(official[it].id).collectAsLazyPagingItems()
    }

    var pagerState = rememberPagerState(0)

    Column(
        Modifier
            .fillMaxSize()
            .padding(vertical = 50.dp)
    ) {
        WxTopTab(pagerState)
        HorizontalPager(
            count = wxViewModel.wxOfficial.size,
            state = pagerState,
            modifier = Modifier.padding(horizontal = 6.dp)
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.White), contentAlignment = Alignment.Center
            ) {
                Text(text = "$it")
                LazyColumn(Modifier.fillMaxSize()){
                    val articles = articleList[official[it].id]
                    repeat(articles?.itemCount?:0){ itemIndex->
                        item {
                            articles?.get(itemIndex)?.let { it1 ->
                                HomeItem(article = it1) {
                                    mainViewModel.pageState.value = PageState.WebPage
                                    mainViewModel.curItem = it1
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WxTopTab(pagerState: PagerState) {
    val viewModel: WxViewModel = viewModel()
    val scope = rememberCoroutineScope()
    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        Modifier.fillMaxWidth(),
        edgePadding = 10.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        repeat(viewModel.wxOfficial.size) {
            Tab(selected = pagerState.currentPage == it, onClick = {
                scope.launch {
                    pagerState.scrollToPage(it)
                }
            }, text = {
                Text(text = viewModel.wxOfficial[it].name)
            })
        }
    }
}

