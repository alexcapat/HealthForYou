package com.acapat.healthforyou.ui.main.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
  onFoodClick: () -> Unit,
  onSleepClick: () -> Unit,
  onWaterClick: () -> Unit,
  onBodyCompositionClick: () -> Unit,
  onStepGoalClick: () -> Unit
) {
  Column(modifier = Modifier.padding()) {
    Button(
      onClick = onStepGoalClick,
      modifier = Modifier.fillMaxWidth().height(60.dp).padding(top = 16.dp),
      shape = RoundedCornerShape(45.dp),
    ) {
      Text(text = "Steps Goal", fontSize = 20.sp)
    }
    Button(
      onClick = onFoodClick,
      modifier = Modifier.fillMaxWidth().height(60.dp).padding(top = 16.dp),
      shape = RoundedCornerShape(45.dp)
    ) {
      Text(text = "Food", fontSize = 20.sp)
    }
    Button(
      onClick = onSleepClick,
      modifier = Modifier.fillMaxWidth().height(60.dp).padding(top = 16.dp),
      shape = RoundedCornerShape(45.dp)
    ) {
      Text(text = "Sleep", fontSize = 20.sp)
    }
    Button(
      onClick = onWaterClick,
      modifier = Modifier.fillMaxWidth().height(60.dp).padding(top = 16.dp),
      shape = RoundedCornerShape(45.dp)
    ) {
      Text(text = "Water", fontSize = 20.sp)
    }
    Button(
      onClick = onBodyCompositionClick,
      modifier = Modifier.fillMaxWidth().height(60.dp).padding(top = 16.dp),
      shape = RoundedCornerShape(45.dp)
    ) {
      Text(text = "Body Composition", fontSize = 20.sp)
    }
  }
}

enum class HomeScreen(val route: String) {
  MAIN("main"),
  FOOD("food"),
  ADD_FOOD("add_food"),
  STEPS_GOAL("steps_goal"),
  WATER("water"),
  ADD_WATER("add_water"),
  SLEEP("sleep"),
  ADD_SLEEP("add_sleep"),
  BODY_COMPOSITION("body_composition"),
  ADD_BODY_COMPOSITION("add_body_composition")
}
