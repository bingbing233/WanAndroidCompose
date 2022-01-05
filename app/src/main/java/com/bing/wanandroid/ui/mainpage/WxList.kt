package com.bing.wanandroid.ui.mainpage

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                    pagerState.scrollToPage(it)
                }
            })
        HorizontalPager(
            count = viewModel.wxOfficial.size,
            state = pagerState,
            modifier = Modifier.padding(horizontal = 6.dp)
        ) { currentPage ->
            repeat(viewModel.wxOfficial.size) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(Color.White), contentAlignment = Alignment.Center
                ) {
                    Text(text = "这是页面$currentPage", style = MaterialTheme.typography.h6)
                }
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
        repeat(viewModel.wxOfficial.size) {
            Tab(selected = selectIndex == it, onClick = { onSelectedChange.invoke(it) }, text = {
                Text(text = viewModel.wxOfficial[it].name)
            })
        }
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
