package com.bing.wanandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import com.bing.wanandroid.ui.mainpage.MainPage
import com.bing.wanandroid.ui.WebPage
import com.bing.wanandroid.ui.theme.WanAndroidTheme

class MainActivity : ComponentActivity() {
    private val viewModel: WanViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WanAndroidTheme {
                Box {
                    viewModel.getHomeArticle()
                    MainPage()
                    AnimatedVisibility(
                        visible = viewModel.showWebPage,
                        enter = slideInHorizontally { it },
                        exit = slideOutHorizontally { it }) {
                        WebPage(data = viewModel.curItem)
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        if (viewModel.showWebPage) {
            viewModel.showWebPage = false
        } else {
            super.onBackPressed()
        }
    }
}
