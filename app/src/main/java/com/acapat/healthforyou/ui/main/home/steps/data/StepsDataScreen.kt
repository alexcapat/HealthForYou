package com.acapat.healthforyou.ui.main.home.steps.data

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
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
import com.acapat.healthforyou.ui.components.DayPicker
import java.text.DecimalFormat

private val decimalFormat = DecimalFormat("0.##")

@Composable
fun StepsDataScreen(viewModel: StepsDataViewModel = hiltViewModel()) {
  val uiData = viewModel.uiData.collectAsState().value
  Column(
    modifier = Modifier.fillMaxSize().padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Top
  ) {
    DayPicker(day = uiData.instant, onDayChanged = viewModel::selectDay)

    AnimatedVisibility(uiData.isLoading) {
      LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
    }

    Item(title = "Steps Count", value = uiData.data.steps.toString())
    Item(title = "Distance", value = decimalFormat.format(uiData.data.distance))
  }
}

@Composable
private fun Item(title: String, value: String) {
  ListItem(
    headlineContent = { Text(text = title) },
    supportingContent = { AnimatedContent(targetState = value) { text -> Text(text = text) } },
    modifier = Modifier.fillMaxWidth()
  )
}
