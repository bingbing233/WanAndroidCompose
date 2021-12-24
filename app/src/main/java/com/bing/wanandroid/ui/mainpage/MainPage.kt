package com.bing.wanandroid.ui.mainpage

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
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

@Composable
fun MainPage() {
    val viewModel:WanViewModel = viewModel()
    Scaffold(topBar = { WanTopBar() }, bottomBar = { WanBottomBar() }) {
        when(viewModel.selectedPage){
            0-> HomeList()
            2-> WxList()
        }
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
        }, actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Search, null)
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
                onClick = {
                    viewModel.selectedPage = it
                    when(it){
                        0-> viewModel.getHomeArticle()
                        2-> viewModel.getWxArticle()
                    }
                },
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



