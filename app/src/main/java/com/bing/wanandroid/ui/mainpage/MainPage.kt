package com.bing.wanandroid.ui.mainpage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bing.wanandroid.R
import com.bing.wanandroid.WanViewModel
import com.bing.wanandroid.WxViewModel
import com.google.accompanist.insets.ui.Scaffold
import com.google.accompanist.insets.ui.TopAppBar
import kotlinx.coroutines.launch

/**
 *  @author: liangbinghao
 *  @date:  2021/12/15 22:42
 *  @desc:  主页
 */

@Composable
fun MainPage() {
    val scaffoldState = rememberScaffoldState()
    val viewModel: WanViewModel = viewModel()
    val scope = rememberCoroutineScope()
    Scaffold(topBar = {
        WanTopBar(onNavClick = {
            scope.launch {
                scaffoldState.drawerState.open()
            }
        })
    }, bottomBar = { WanBottomBar() }, drawerContent = {
        WanDrawer()
    }, scaffoldState = scaffoldState) {
        when (viewModel.selectedPage) {
            0 -> HomeList()
            1 -> SquareList()
            2 -> WxList()
        }
    }
}

@Composable
fun WanTopBar(onNavClick: () -> Unit) {
    val viewModel: WanViewModel = viewModel()
    TopAppBar(
        title = { Text(text = viewModel.bottomTitles[viewModel.selectedPage]) },
        navigationIcon = {
            IconButton(onClick = { onNavClick.invoke() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_menu_24),
                    contentDescription = null
                )
            }
        }, actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Search, null)
            }
        }, elevation = 0.dp
    )
}

@Composable
fun WanBottomBar() {
    val viewModel: WanViewModel = viewModel()
    val wxViewModel: WxViewModel = viewModel()
    BottomNavigation {
        repeat(5) {
            BottomNavigationItem(
                selected = it == viewModel.selectedPage,
                onClick = {
                    viewModel.selectedPage = it
                    when (it) {
                       // 0 -> viewModel.getHomeArticle()
                       // 2 -> wxViewModel.getWxArticle(id = 408)
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

@Composable
fun WanDrawer() {
    Box(
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primary)
    ) {
        Column(Modifier.fillMaxSize()) {

        }
    }
}



