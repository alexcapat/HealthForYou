package com.acapat.healthforyou.ui.main.home.steps

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.acapat.healthforyou.ui.main.home.HomeScreen
import com.acapat.healthforyou.ui.main.home.steps.availability.StepsAvailabilityScreen
import com.acapat.healthforyou.ui.main.home.steps.data.StepsDataScreen
import com.acapat.healthforyou.ui.main.home.steps.permission.StepsPermissionScreen

private val screenModifier = Modifier.fillMaxSize()

fun NavGraphBuilder.stepsNavigation(navController: NavController) {
  navigation(
    startDestination = StepsScreen.AVAILABILITY.route,
    route = HomeScreen.STEPS_GOAL.route
  ) {
    composable(StepsScreen.AVAILABILITY.route) { StepsAvailabilityScreen() }
    composable(StepsScreen.PERMISSION.route) { StepsPermissionScreen() }
    composable(StepsScreen.STEPS_DATA.route) {
      StepsDataScreen(stepCount = 5, kmCount = 5, kcalCount = 5)
    }
  }
}
