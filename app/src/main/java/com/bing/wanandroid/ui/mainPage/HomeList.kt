package com.bing.wanandroid.ui.mainPage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.bing.wanandroid.MainViewModel
import com.bing.wanandroid.PageState
import com.bing.wanandroid.model.Article


@Composable
fun HomeList() {
    val viewModel: MainViewModel = viewModel()
    val article = viewModel.getHomeArticle().collectAsLazyPagingItems()
    LazyColumn(
        Modifier
            .padding(horizontal = 8.dp, vertical = 60.dp)
            .fillMaxSize()
    ) {
        repeat(article.itemCount) {
            item {
                article[it]?.let { article ->
                    HomeItem(article) {
                        viewModel.curItem = article
                        viewModel.pageState.value = PageState.WebPage
                    }
                }
                Spacer(Modifier.height(3.dp))
            }
        }
    }
}

@Composable
fun HomeItem(article: Article, onClick: () -> Unit) {
    Card(modifier = Modifier
        .clickable {
            onClick.invoke()
        }
        .height(100.dp), elevation = 5.dp
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(10.dp), verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = article.title, style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
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
}
