package com.bing.wanandroid.ui.loginPage

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bing.wanandroid.R

@Composable
fun LoginPage(navController: NavController) {
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "登录") }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back"
                )
            }
        }, modifier = Modifier.background(color = MaterialTheme.colors.primary))
    }) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 100.dp, horizontal = 50.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "")
            OutlinedTextField(
                label = { Text(text = "账号") },
                modifier = Modifier.fillMaxWidth(),
                value = username,
                onValueChange = { username = it })
            Spacer(Modifier.height(10.dp))
            OutlinedTextField(
                label = { Text(text = "密码") },
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { password = it })
            Spacer(Modifier.height(10.dp))
            Button(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "登录",Modifier.padding(10.dp))
            }
        }
    }
}