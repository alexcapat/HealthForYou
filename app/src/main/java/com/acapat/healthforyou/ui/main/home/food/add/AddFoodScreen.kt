package com.acapat.healthforyou.ui.main.home.food.add

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.acapat.healthforyou.R

@Composable
fun AddFoodScreen(
  modifier: Modifier = Modifier,
  viewModel: AddFoodViewModel = hiltViewModel()
) {
  val uiData = viewModel.uiData.collectAsState().value

  Column(modifier = modifier) {
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Image(
        painter = painterResource(id = R.drawable.food),
        contentDescription = "Food Image",
      )
      Spacer(Modifier.height(16.dp))

      OutlinedTextField(
        value = uiData.breakfast,
        onValueChange = viewModel::setBreakfast,
        label = { Text(text = "Breakfast") },
        keyboardOptions =
          KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
        isError = !uiData.isBreakfastValid
      )

      OutlinedTextField(
        value = uiData.lunch,
        onValueChange = viewModel::setLunch,
        label = { Text(text = "Lunch") },
        keyboardOptions =
          KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
        isError = !uiData.isLunchValid
      )

      OutlinedTextField(
        value = uiData.dinner,
        onValueChange = viewModel::setDinner,
        label = { Text(text = "Dinner") },
        keyboardOptions =
          KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
        isError = !uiData.isDinnerValid
      )

      Text(text = "Total: ${uiData.total?:0}")

      Button(onClick = viewModel::insertFood, enabled = uiData.isValid && !uiData.isLoading) {
        Text(text = "Add")
      }
    }
  }
}
