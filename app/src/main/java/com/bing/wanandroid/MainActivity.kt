package com.bing.wanandroid

import android.graphics.pdf.PdfDocument
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.SideEffect
import androidx.lifecycle.lifecycleScope
import com.bing.wanandroid.ui.mainPage.MainPage
import com.bing.wanandroid.ui.WebPage
import com.bing.wanandroid.ui.loginPage.LoginPage
import com.bing.wanandroid.ui.theme.WanAndroidTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private val wxViewModel: WxViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WanAndroidTheme {
                SideEffect {
                    lifecycleScope.launch {
                        //首次onCommit时请求公众号数据
                        wxViewModel.getWxOfficial()
                    }
                }

                Box {
                    MainPage()
                    AnimatedVisibility(
                        visible = mainViewModel.pageState.value == PageState.WebPage,
                        enter = slideInHorizontally { it },
                        exit = slideOutHorizontally { it }) {
                        WebPage(data = mainViewModel.curItem)
                    }
                    AnimatedVisibility(
                        visible = mainViewModel.pageState.value == PageState.LoginPage,
                        enter = slideInHorizontally { it },
                        exit = slideOutHorizontally { it }) {
                        LoginPage()
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        if (mainViewModel.pageState.value == PageState.MainPage) {
            super.onBackPressed()
        } else {
         mainViewModel.pageState.value = PageState.MainPage
        }
    }
}
