package com.bing.wanandroid.ui.mainPage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.bing.wanandroid.ManiViewModel
import com.bing.wanandroid.model.Article

@Composable
fun HomeItem(article: Article) {
    val viewModel: ManiViewModel = viewModel()
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
                    color = Color.Blue
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
    val viewModel: ManiViewModel = viewModel()
    val article = viewModel.getHomeArticle().collectAsLazyPagingItems()
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
