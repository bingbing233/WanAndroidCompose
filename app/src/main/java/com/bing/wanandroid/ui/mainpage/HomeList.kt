package com.bing.wanandroid.ui.mainpage

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bing.wanandroid.WanViewModel
import com.bing.wanandroid.model.HomeArticle
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@SuppressLint("SimpleDateFormat")
@Composable
fun HomeItem(homeArticle: HomeArticle) {
    val viewModel: WanViewModel = viewModel()
    Card(modifier = Modifier
        .clickable {
            viewModel.showWebPage = true
            viewModel.curItem = homeArticle
        }
        .height(100.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(8.dp), verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = homeArticle.title, style = MaterialTheme.typography.subtitle1)
            Row(horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier.fillMaxWidth()) {
                Text(text =homeArticle.chapterName, style = MaterialTheme.typography.body1, color = Color.Gray)
                Text(text = homeArticle.niceDate,style = MaterialTheme.typography.body2,color = Color.Gray)
            }
        }
    }
    Spacer(Modifier.height(5.dp))
}

@Composable
fun HomeList() {
    val viewModel: WanViewModel = viewModel()
    val isRefreshing = viewModel.isRefreshing.collectAsState()
    SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = isRefreshing.value), onRefresh = {
        viewModel.getHomeArticle()
    }) {
        LazyColumn(
            Modifier
                .padding(horizontal = 8.dp, vertical = 60.dp)
                .fillMaxSize()
        ) {
            repeat(viewModel.homeDataSize) {
                item {
                    val data = viewModel.homeData[it]
                    HomeItem(data)
                }
            }
        }
    }

}

