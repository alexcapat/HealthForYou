package com.acapat.healthforyou.ui.main.home.sleep

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.acapat.healthforyou.db.entity.SleepEntity

private val sleepModifier = Modifier.fillMaxWidth()

@Composable
fun SleepScreen(
  onAddSleepClick: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: SleepViewModel = hiltViewModel()
) {
  val uiData = viewModel.uiData.collectAsState().value

  Box(modifier = modifier) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
      items(uiData.sleeps) { sleep ->
        SleepItem(
          sleep = sleep,
          onDelete = { viewModel.deleteSleep(sleep) },
          modifier = sleepModifier
        )
      }
    }
    Button(
      onClick = onAddSleepClick,
      modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp)
    ) {
      Text("Add")
    }
  }
}

@Composable
fun SleepItem(sleep: SleepEntity, onDelete: () -> Unit, modifier: Modifier = Modifier) {
  Box(
    modifier =
      modifier.border(
        BorderStroke(1.dp, Color.Black),
        shape = RectangleShape,
      )
  ) {
    ListItem(
      modifier = Modifier.fillMaxWidth(),
      headlineContent = { Text(text = "Sleep #${sleep.id} Total: ${sleep.total}") },
      supportingContent = {
        Column {
          Text(
            text = "Sleep ${sleep.sleep}",
          )
        }
      },
      trailingContent = {
        IconButton(onClick = onDelete) {
          Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Sleep")
        }
      }
    )
  }
}
