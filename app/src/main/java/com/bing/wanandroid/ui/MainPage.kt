package com.bing.wanandroid.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bing.wanandroid.R
import com.bing.wanandroid.WanViewModel
import com.google.accompanist.insets.ui.Scaffold
import com.google.accompanist.insets.ui.TopAppBar

/**
 *  @author: liangbinghao
 *  @date:  2021/12/15 22:42
 *  @desc:  主页
 */

@Preview(
    showBackground = true
)
@Composable
fun MainPage() {
    Scaffold(topBar = { WanTopBar() }, bottomBar = { WanBottomBar() }) {
        HomeList()
    }
}

@Composable
fun WanTopBar() {
    val viewModel: WanViewModel = viewModel()
    TopAppBar(
        title = { Text(text = viewModel.bottomTitles[viewModel.selectedPage]) },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_menu_24),
                    contentDescription = null
                )
            }
        })
}

@Composable
fun WanBottomBar() {
    val viewModel: WanViewModel = viewModel()
    BottomNavigation {
        repeat(5) {
            BottomNavigationItem(
                selected = it == viewModel.selectedPage,
                onClick = { viewModel.selectedPage = it },
                icon = {
                    Icon(
                        painter = painterResource(id = viewModel.bottomIcons[it]),
                        contentDescription = null
                    )
                },
                label = { Text(text = viewModel.bottomTitles[it]) }
            )
        }
    }
}

@Composable
fun WanItem(title: String, author: String) {
    Card(modifier = Modifier.height(50.dp)) {
        Column(Modifier.fillMaxSize()) {
            Text(text = title)
            Text(text = author)
        }
    }
}

@Composable
fun HomeList() {
    val viewModel:WanViewModel = viewModel()
    LazyColumn(){
        repeat(viewModel.dataSize){
            item{
                val data = viewModel.homeData[it]
                WanItem(title = data.title, author =data.author)
            }
        }
    }

}
