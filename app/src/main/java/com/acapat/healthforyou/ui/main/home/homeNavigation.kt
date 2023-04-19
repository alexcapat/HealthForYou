package com.acapat.healthforyou.ui.main.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.acapat.healthforyou.Screen
import com.acapat.healthforyou.ui.main.home.bodycomposition.BodyCompositionScreen
import com.acapat.healthforyou.ui.main.home.food.FoodScreen
import com.acapat.healthforyou.ui.main.home.food.add.AddFoodScreen
import com.acapat.healthforyou.ui.main.home.steps.StepsScreen
import com.acapat.healthforyou.ui.main.home.water.add.WaterScreen

private val screenModifier = Modifier.fillMaxSize()

fun NavGraphBuilder.homeNavigation(navController: NavController) {
  navigation(startDestination = HomeScreen.MAIN.route, route = Screen.HOME.route) {
    composable(HomeScreen.MAIN.route) {
      HomeScreen(
        onStepGoalClick = { navController.navigate(HomeScreen.STEPS_GOAL.route) },
        onFoodClick = { navController.navigate(HomeScreen.FOOD.route) },
        onWaterClick = { navController.navigate(HomeScreen.WATER.route) },
        onBodyCompositionClick = { navController.navigate(HomeScreen.BODY_COMPOSITION.route) }
      )
    }
    composable(HomeScreen.STEPS_GOAL.route) {
      StepsScreen(stepCount = 5, kmCount = 5, kcalCount = 5)
    }
    composable(HomeScreen.FOOD.route) {
      FoodScreen(
        onAddFoodClick = { navController.navigate(HomeScreen.ADD_FOOD.route) },
        modifier = screenModifier
      )
    }
    composable(HomeScreen.ADD_FOOD.route) {
      AddFoodScreen(modifier = screenModifier)
    }
    composable(HomeScreen.WATER.route) { WaterScreen() }
    composable(HomeScreen.BODY_COMPOSITION.route) { BodyCompositionScreen() }
  }
}