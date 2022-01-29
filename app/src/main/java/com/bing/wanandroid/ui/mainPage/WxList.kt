package com.bing.wanandroid.ui.mainPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bing.wanandroid.WxViewModel
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun WxList() {
    val viewModel: WxViewModel = viewModel()
    var pagerState = rememberPagerState(0)
    Column(
        Modifier
            .fillMaxSize()
            .padding(vertical = 50.dp)
    ) {
        WxTopTab(pagerState)
        HorizontalPager(
            count = viewModel.wxOfficial.size,
            state = pagerState,
            modifier = Modifier.padding(horizontal = 6.dp)
        ) {
            repeat(viewModel.wxOfficial.size) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(Color.White), contentAlignment = Alignment.Center
                ) {

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

