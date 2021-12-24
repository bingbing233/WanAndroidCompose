package com.bing.wanandroid.ui.mainpage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
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
    Column(Modifier.fillMaxSize()) {
        WxTopTab()
    }
    /*LazyColumn(
        Modifier
            .padding(horizontal = 8.dp, vertical = 60.dp)
            .fillMaxSize()
    ) {

    }*/

}

@Composable
fun WxTopTab() {
    val viewModel:WanViewModel = viewModel()
    TabRow(selectedTabIndex = viewModel.wxCurItem) {
        Tab(selected = viewModel.wxCurItem==0 , onClick = { /*TODO*/ }) {
            Text(text = "鸿洋")
        }
        Tab(selected = viewModel.wxCurItem==1 , onClick = { /*TODO*/ }) {
            Text(text = "郭霖")
        }
        Tab(selected = viewModel.wxCurItem==2 , onClick = { /*TODO*/ }) {
            Text(text = "玉刚说")
        }
        Tab(selected = viewModel.wxCurItem==3 , onClick = { /*TODO*/ }) {
            Text(text = "承香墨影")
        }
        Tab(selected = viewModel.wxCurItem==4 , onClick = { /*TODO*/ }) {
            Text(text = "Android群英传")
        }
    }
}
