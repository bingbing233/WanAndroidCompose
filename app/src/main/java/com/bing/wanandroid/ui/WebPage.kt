package com.bing.wanandroid.ui

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bing.wanandroid.WanViewModel
import com.bing.wanandroid.model.Article

@Composable
fun WebPage(data: Article?) {
    var progress = remember {
        mutableStateOf(0f)
    }
    var doRefresh = remember {
        mutableStateOf(false)
    }
    Scaffold(topBar = {
        data?.title?.let {
            WebTopBar(title = it, onRefreshClick = {
                doRefresh.value = true
            })
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (progress.value < 100) {
                LinearProgressIndicator(
                    progress = progress.value,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            data?.link?.let { it1 ->
                WebView(url = it1, modifier = Modifier.fillMaxSize(), onProgressUpdate = {
                    progress.value = it / 100f
                })
            }
        }
    }
}


@Composable
fun WebTopBar(title: String, onRefreshClick: (() -> Unit)? = null) {
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
        }, actions = {
            IconButton(onClick = {
                onRefreshClick?.invoke()
            }) {
                Icon(Icons.Default.Refresh, "")
            }
        })
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebView(url: String, modifier: Modifier = Modifier, onProgressUpdate: ((Int) -> Unit)? = null) {
    AndroidView(modifier = modifier, factory = {
        val webView = WebView(it)
        webView.apply {
            settings.javaScriptEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.domStorageEnabled = true
            settings.loadsImagesAutomatically = true
            settings.mediaPlaybackRequiresUserGesture = true
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    return if (url.startsWith("http"))
                        true
                    else
                        super.shouldOverrideUrlLoading(view, request)
                }
            }
            loadUrl(url)
        }
        webView
    }, update = {
        it.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                onProgressUpdate?.invoke(newProgress)
            }
        }
    })
}