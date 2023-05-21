package com.acapat.healthforyou

import androidx.navigation.NavOptions
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

@Singleton
class MainNavigator @Inject constructor() {

  private val _navigationRoutes = MutableSharedFlow<NavigationData>(extraBufferCapacity = 4)
  val navigationRoutesFlow = _navigationRoutes.asSharedFlow()

  private val _navigateUp = MutableSharedFlow<Unit>(extraBufferCapacity = 4)
  val navigateUpFlow = _navigateUp.asSharedFlow()

  fun navigateTo(route: String, options: NavOptions? = null) {
    _navigationRoutes.tryEmit(NavigationData(route, options))
  }

  fun navigateUp() {
    _navigateUp.tryEmit(Unit)
  }
}

data class NavigationData(
  val route: String,
  val options: NavOptions? = null,
)
