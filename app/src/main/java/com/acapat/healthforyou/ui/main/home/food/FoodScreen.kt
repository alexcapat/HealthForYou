package com.acapat.healthforyou.ui.main.home.food

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
import com.acapat.healthforyou.db.entity.FoodEntity

private val foodModifier = Modifier.fillMaxWidth()

@Composable
fun FoodScreen(
  onAddFoodClick: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: FoodViewModel = hiltViewModel()
) {
  val uiData = viewModel.uiData.collectAsState().value

  Box(modifier = modifier) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
      items(uiData.foods) { food ->
        FoodItem(food = food, onDelete = { viewModel.deleteFood(food) }, modifier = foodModifier)
      }
    }
    Button(
      onClick = onAddFoodClick,
      modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp)
    ) {
      Text("Add")
    }
  }
}
@Composable
fun FoodItem(food: FoodEntity, onDelete: () -> Unit, modifier: Modifier = Modifier) {
  Box(
    modifier = modifier.border(
      BorderStroke(1.dp, Color.Black),
      shape = RectangleShape
    )
  ) {
    ListItem(
      modifier = Modifier.fillMaxWidth(),
      headlineContent = { Text(text = "Food #${food.id} Total: ${food.total}") },
      supportingContent = {
        Column {
          Text(text = "Breakfast ${food.breakfast}")
          Text(text = "Lunch ${food.lunch}")
          Text(text = "Dinner ${food.dinner}")
        }
      },
      trailingContent = {
        IconButton(onClick = onDelete) {
          Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Food")
        }
      }
    )
  }
}

