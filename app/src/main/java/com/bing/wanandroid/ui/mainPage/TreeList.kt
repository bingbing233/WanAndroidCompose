package com.bing.wanandroid.ui.mainPage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bing.wanandroid.MainViewModel
import com.bing.wanandroid.model.TreeData
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun TreeList() {
    val mainViewModel:MainViewModel = viewModel()
    val treeResult = mainViewModel.treeResult
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(vertical = 60.dp, horizontal = 8.dp)){
        repeat(treeResult.size){
            item {
                TreeItem(treeData = treeResult[it])
                Spacer(modifier = Modifier.height(3.dp))
            }
        }
    }
}

@Composable
fun TreeItem(treeData: TreeData) {
    Card(modifier = Modifier
        .clickable { }
        .fillMaxWidth()
        , elevation = 5.dp) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(10.dp)) {
            Text(
                text = treeData.name,
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            FlowRow(Modifier.fillMaxWidth(), mainAxisSpacing = 5.dp, crossAxisSpacing = 3.dp) {
                repeat(treeData.children.size) {
                    Text(text = treeData.children[it].name)
                }
            }
        }
    }
}