package com.bing.wanandroid.ui.mainPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bing.wanandroid.R
import com.bing.wanandroid.MainViewModel
import com.bing.wanandroid.PageState
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
    val viewModel: MainViewModel = viewModel()
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
            3-> TreeList()
        }
    }


}

@Composable
fun WanTopBar(onNavClick: () -> Unit) {
    val viewModel: MainViewModel = viewModel()
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
    val viewModel: MainViewModel = viewModel()
    BottomNavigation {
        repeat(5) {
            BottomNavigationItem(
                selected = it == viewModel.selectedPage,
                onClick = {
                    viewModel.selectedPage = it
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
    val viewModel:MainViewModel = viewModel()
    Box(
        Modifier
            .fillMaxSize()
    ) {
        Column(Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .clickable {
                        viewModel.pageState.value = PageState.LoginPage
                    }
                    .background(color = MaterialTheme.colors.primary)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = ""
                )
                Text(text = "去登录", color = Color.White)
            }
            Column(Modifier.fillMaxSize()) {
                Row(
                    Modifier
                        .clickable { }
                        .padding(20.dp)
                        .fillMaxWidth()) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = "")
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "设置")
                }
                Row(
                    Modifier
                        .clickable { }
                        .padding(20.dp)
                        .fillMaxWidth()) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = "")
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "更多信息")
                }
            }
        }
    }
}



