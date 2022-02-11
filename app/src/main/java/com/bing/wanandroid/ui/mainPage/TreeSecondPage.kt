package com.bing.wanandroid.ui.mainPage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bing.wanandroid.MainViewModel
import com.bing.wanandroid.PageState
import com.bing.wanandroid.model.TreeData
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

/**
 * 二级文章页
 */

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SecondArticlePage(treeData: TreeData) {

    val mainViewModel: MainViewModel = viewModel()
    val pageState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = treeData.name) }, navigationIcon = {
            IconButton(onClick = { mainViewModel.pageState.value = PageState.MainPage }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
            }
        })
    }) {
        Column(Modifier.fillMaxSize()) {
            ScrollableTabRow(selectedTabIndex = pageState.currentPage) {
                repeat(treeData.children.size) {
                    Tab(selected = pageState.currentPage == it, onClick = {
                        scope.launch {
                            pageState.scrollToPage(it)
                        }
                    }, text = { Text(text = treeData.children[it].name) })
                }
            }

            LazyColumn(Modifier.fillMaxSize()){
            }

        }
    }
}