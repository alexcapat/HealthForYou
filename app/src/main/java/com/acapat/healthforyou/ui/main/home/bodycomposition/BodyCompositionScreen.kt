package com.acapat.healthforyou.ui.main.home.bodycomposition

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.acapat.healthforyou.R

@Composable
fun BodyCompositionScreen(viewModel: BodyCompositionViewModel = hiltViewModel()) {
  val uiData = viewModel.uiData.collectAsState().value
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Image(
      painter = painterResource(id = R.drawable.male_body_goal_512),
      contentDescription = "Body Composition",
    )
    Spacer(Modifier.height(16.dp))

    OutlinedTextField(
      value = uiData.weight,
      onValueChange = viewModel::setWeight,
      label = { Text(text = "Weight") },
      keyboardOptions =
        KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
      isError = !uiData.isWeightValid
    )
    OutlinedTextField(
      value = uiData.height,
      onValueChange = viewModel::setHeight,
      label = { Text(text = "Height") },
      keyboardOptions =
        KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
      isError = !uiData.isHeightValid
    )

    uiData.bmi?.let { bmi ->
      Text(text = "Total: $bmi")
      BmiResultsText(bmi)
    }
  }
}

@Composable
fun BmiResultsText(bmi: Float) {
  val (text, color) =
    when {
      bmi.isNaN() -> "Enter Values" to Color.White
      bmi < 18.5 -> "Underweight" to Color.Red
      bmi < 24.9 -> "Normal" to Color.Green
      bmi < 30 -> "Overweight" to Color.Yellow
      else -> "Obese" to Color.Red
    }
  Text(
    text = text,
    color = color,
    style = MaterialTheme.typography.bodyMedium,
    modifier = Modifier.padding(8.dp)
  )
}
