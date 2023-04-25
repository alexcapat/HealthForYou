package com.acapat.healthforyou.ui.main.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.acapat.healthforyou.Screen
import com.acapat.healthforyou.ui.main.home.bodycomposition.BodyCompositionScreen
import com.acapat.healthforyou.ui.main.home.bodycomposition.add.AddBodyCompositionScreen
import com.acapat.healthforyou.ui.main.home.food.FoodScreen
import com.acapat.healthforyou.ui.main.home.food.add.AddFoodScreen
import com.acapat.healthforyou.ui.main.home.steps.StepsScreen
import com.acapat.healthforyou.ui.main.home.water.WaterScreen
import com.acapat.healthforyou.ui.main.home.water.add.AddWaterScreen

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
    // ================Steps=====================================================
    composable(HomeScreen.STEPS_GOAL.route) {
      StepsScreen(stepCount = 5, kmCount = 5, kcalCount = 5)
    }
    /// ================Food=====================================================

    composable(HomeScreen.FOOD.route) {
      FoodScreen(
        onAddFoodClick = { navController.navigate(HomeScreen.ADD_FOOD.route) },
        modifier = screenModifier
      )
    }
    composable(HomeScreen.ADD_FOOD.route) { AddFoodScreen(modifier = screenModifier) }
    // ================Water=====================================================

    composable(HomeScreen.ADD_WATER.route) { AddWaterScreen(modifier = screenModifier) }
    composable(HomeScreen.WATER.route) {
      WaterScreen(
        onAddWaterClick = { navController.navigate(HomeScreen.ADD_WATER.route) },
        modifier = screenModifier
      )
    }

    // ================Body Composition=====================================================
    composable(HomeScreen.ADD_BODY_COMPOSITION.route) {
      AddBodyCompositionScreen(modifier = screenModifier)
    }
    composable(HomeScreen.BODY_COMPOSITION.route) {
      BodyCompositionScreen(
        onAddBMIClick = { navController.navigate(HomeScreen.ADD_BODY_COMPOSITION.route) },
        modifier = screenModifier
      )
    }
  }
}
