package com.bing.wanandroid.ui.mainpage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bing.wanandroid.WanViewModel
import com.bing.wanandroid.model.WxOfficial


@Composable
fun WxItem(wxOfficial: WxOfficial) {
    val viewModel: WanViewModel = viewModel()
    Card(modifier = Modifier
        .clickable {
            viewModel.showWebPage = true
           // viewModel.curItem = homeArticle
        }
        .height(100.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(8.dp), verticalArrangement = Arrangement.SpaceEvenly
        ) {
            //Text(text = homeArticle.title, style = MaterialTheme.typography.subtitle1)
            //Text(text = homeArticle.author, style = MaterialTheme.typography.body1, color = Color.Gray)
        }
    }
    Spacer(Modifier.height(5.dp))
}

@Composable
fun WxList() {
    val viewModel: WanViewModel = viewModel()
    LazyColumn(
        Modifier
            .padding(horizontal = 8.dp, vertical = 60.dp)
            .fillMaxSize()
    ) {
        repeat(viewModel.wxData.size) {
            item {
                val data = viewModel.homeData[it]
                HomeItem(data)
            }
        }
    }

}
