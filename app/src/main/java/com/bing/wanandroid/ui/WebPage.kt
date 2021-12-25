package com.bing.wanandroid.ui

import android.annotation.SuppressLint
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bing.wanandroid.WanViewModel
import com.bing.wanandroid.model.Article

@Composable
fun WebPage(data: Article?) {
    Scaffold(topBar = { data?.title?.let { WebTopBar(title = it) } }) {
        data?.link?.let { it1 -> WebView(url = it1, modifier = Modifier.fillMaxSize()) }
    }
}


@Composable
fun WebTopBar(title: String) {
    val viewModel: WanViewModel = viewModel()
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = { viewModel.showWebPage = false }) {
                Icon(Icons.Default.ArrowBack, null)
            }
        })
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebView(url: String, modifier: Modifier = Modifier) {
    AndroidView(modifier = modifier, factory = {
        val webView = android.webkit.WebView(it)
        webView.apply {
            settings.javaScriptEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.domStorageEnabled = true
            settings.loadsImagesAutomatically = true
            settings.mediaPlaybackRequiresUserGesture = true
            webViewClient = object :WebViewClient(){
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    return if(url.startsWith("http"))
                        true
                    else
                        super.shouldOverrideUrlLoading(view, request)
                }
            }
            loadUrl(url)
        }
        webView
    })
}