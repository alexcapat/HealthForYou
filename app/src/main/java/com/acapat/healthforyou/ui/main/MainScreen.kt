package com.acapat.healthforyou.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.acapat.healthforyou.MainNavigator
import com.acapat.healthforyou.Screen
import com.acapat.healthforyou.ui.main.home.homeNavigation
import com.acapat.healthforyou.ui.main.records.PersonalStatsScreen
import com.acapat.healthforyou.ui.main.videos.VideosScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navigator: MainNavigator, modifier: Modifier = Modifier) {
  val navController = rememberNavController()
  LaunchedEffect(navigator) { navigator.navigateUpFlow.collect { navController.navigateUp() } }
  LaunchedEffect(navigator) {
    navigator.navigationRoutesFlow.collect { (route, options) ->
      navController.navigate(route, options)
    }
  }
  Scaffold(
    modifier = modifier,
    topBar = {
      @Suppress("UNUSED_VARIABLE")
      val currentEntry = navController.currentBackStackEntryAsState().value
      TopAppBar(
        title = { Text(text = "Health4You") },
        navigationIcon = {
          if (navController.previousBackStackEntry != null) {
            IconButton(onClick = navController::navigateUp) {
              Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
          }
        }
      )
    },
    bottomBar = { BottomBar(navController = navController) }
  ) { padding ->
    NavHost(
      navController = navController,
      startDestination = Screen.HOME.route,
      modifier = Modifier.padding(padding)
    ) {
      homeNavigation(navController)
      composable(Screen.VIDEOS.route) { VideosScreen() }
      composable(Screen.MY_STATS.route) { PersonalStatsScreen() }
    }
  }
}

@Composable
private fun BottomBar(navController: NavHostController) {
  val currentDestination = navController.currentBackStackEntryAsState().value
  val hierarchy = currentDestination?.destination?.hierarchy

  println(hierarchy?.toList())
  NavigationBar(modifier = Modifier.fillMaxWidth()) {
    Screen.values().forEach { screen ->
      NavigationBarItem(
        selected = hierarchy?.any { it.route == screen.route } == true,
        onClick = { navController.navigate(screen.route) },
        icon = { Icon(imageVector = screen.icon, contentDescription = null) },
        label = { Text(text = screen.label) }
      )
    }
  }
}

