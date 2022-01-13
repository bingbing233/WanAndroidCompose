package com.bing.wanandroid.ui.mainpage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.bing.wanandroid.ManiViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun SquareList() {
    val viewModel: ManiViewModel = viewModel()
    val isRefreshing = rememberSwipeRefreshState(isRefreshing = false)
    val article = viewModel.getSquareArticle().collectAsLazyPagingItems()

    SwipeRefresh(state = isRefreshing, onRefresh = {
        article.retry()
    }) {
        when (article.loadState.refresh) {
            is LoadState.NotLoading -> {
                LazyColumn(
                    Modifier
                        .padding(horizontal = 8.dp, vertical = 60.dp)
                        .fillMaxSize()
                ) {
                    repeat(article.itemCount) {
                        item {
                            article[it]?.let { it1 -> HomeItem(it1) }
                        }
                    }
                }
            }

            is LoadState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is LoadState.Error -> {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Error", style = MaterialTheme.typography.h6)
                }
            }
        }


    }

}
