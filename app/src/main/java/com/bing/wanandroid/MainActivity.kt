package com.bing.wanandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bing.wanandroid.ui.MainPage
import com.bing.wanandroid.ui.theme.WanAndroidTheme

class MainActivity : ComponentActivity() {
    val viewModel:WanViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WanAndroidTheme {
                Box {
                    MainPage()
                    viewModel.getHomeData()
                }
            }
        }
    }
}
