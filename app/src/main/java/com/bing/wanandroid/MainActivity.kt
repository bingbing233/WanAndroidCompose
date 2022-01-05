package com.bing.wanandroid

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bing.wanandroid.ui.mainpage.MainPage
import com.bing.wanandroid.ui.WebPage
import com.bing.wanandroid.ui.theme.WanAndroidTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val homeViewModel: WanViewModel by viewModels()
    private val wxViewModel:WxViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WanAndroidTheme {
                SideEffect {
                    lifecycleScope.launch {
                        wxViewModel.getWxOfficial()
                    }
                }

                Box {
                    MainPage()
                    AnimatedVisibility(
                        visible = homeViewModel.showWebPage,
                        enter = slideInHorizontally { it },
                        exit = slideOutHorizontally { it }) {
                        WebPage(data = homeViewModel.curItem)
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        if (homeViewModel.showWebPage) {
            homeViewModel.showWebPage = false
        } else {
            super.onBackPressed()
        }
    }
}
