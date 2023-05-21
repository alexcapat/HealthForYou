package com.acapat.healthforyou.ui.main.home.steps.data

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun StepsDataScreen(viewModel: StepsDataViewModel = hiltViewModel()) {
  val uiData = viewModel.uiData.collectAsState().value
  Column(
    modifier = Modifier.fillMaxSize().padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    if (uiData.isLoading) {
      LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
    }
    Item(title = "Steps Count", value = uiData.data.steps.toString())
    Item(title = "Distance", value = uiData.data.distance.toString())
  }
}

@Composable
private fun Item(title: String, value: String) {
  ListItem(
    headlineContent = { Text(text = title) },
    supportingContent = { Text(text = value) },
    modifier = Modifier.fillMaxWidth()
  )
}
