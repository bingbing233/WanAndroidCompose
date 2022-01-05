package com.bing.wanandroid.ui.mainpage

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.bing.wanandroid.WanViewModel
import com.bing.wanandroid.model.Article
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeItem(article: Article) {
    val viewModel: WanViewModel = viewModel()
    Card(modifier = Modifier
        .clickable {
            viewModel.showWebPage = true
            viewModel.curItem = article
        }
        .height(100.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(8.dp), verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = article.title, style = MaterialTheme.typography.subtitle1)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = article.chapterName,
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray
                )
                Text(
                    text = article.niceDate,
                    style = MaterialTheme.typography.body2,
                    color = Color.Gray
                )
            }
        }
    }
    Spacer(Modifier.height(5.dp))
}

@Composable
fun HomeList() {
    val viewModel: WanViewModel = viewModel()
    val isRefreshing = viewModel.isRefreshing.collectAsState()
    val article = viewModel.getHomeArticle2().collectAsLazyPagingItems()

    SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = isRefreshing.value), onRefresh = {
        article.retry()
    }) {
        Log.d("binghao", "发生重组：current load state-> ${article.loadState.refresh}")
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

