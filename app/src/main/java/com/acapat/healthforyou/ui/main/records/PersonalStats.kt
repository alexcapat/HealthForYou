package com.acapat.healthforyou.ui.main.records

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PersonalStatsScreen(
  modifier: Modifier = Modifier,
  viewModel: PersonalStatsViewModel = hiltViewModel()
) {
  val personalStats = viewModel.personalStats.collectAsState().value

  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(16.dp),
  ) {
    Text(
      text = "Personal Best Stats",
      modifier = Modifier.padding(vertical = 8.dp),
    )
    personalStats.forEach { personalStat ->
      PersonalStat(
        personalStat = personalStat,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
      )
    }
  }
}

@Composable
private fun PersonalStat(
  personalStat: PersonalStat,
  modifier: Modifier = Modifier,
) {
  Card(
    modifier = modifier,
    elevation = CardDefaults.cardElevation(3.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
  ) {
    ListItem(
      modifier = Modifier.fillMaxWidth(),
      headlineContent = {
        Text(
          text = personalStat.title,
          style = MaterialTheme.typography.headlineSmall,
        )
      },
      supportingContent = {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
          personalStat.entries.forEachIndexed { index, entry ->
            Text(
              text = "${index + 1}. $entry",
              style = MaterialTheme.typography.bodyMedium,
            )
          }
        }
      }
    )
  }
}
