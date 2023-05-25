package com.acapat.healthforyou.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.ChronoUnit
import java.util.*

private val dateFormat =
  DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
    .withLocale(Locale.US)
    .withZone(ZoneId.systemDefault())

@Composable
fun DayPicker(
  day: Instant,
  onDayChanged: (Instant) -> Unit,
  modifier: Modifier = Modifier,
) {
  Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
    IconButton(
      onClick = {
        val previous = day.minus(1, ChronoUnit.DAYS)
        onDayChanged(previous)
      }
    ) {
      Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Previous day")
    }

    AnimatedContent(
      targetState = day,
      modifier = Modifier.weight(1f),
    ) { instant ->
      Text(text = dateFormat.format(instant), textAlign = TextAlign.Center)
    }

    IconButton(
      onClick = {
        val next = day.plus(1, ChronoUnit.DAYS)
        onDayChanged(next)
      }
    ) {
      Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Next day")
    }
  }
}

@Composable
@Preview
private fun DayPickerPreview() {
  DayPicker(day = Instant.now(), onDayChanged = {})
}
