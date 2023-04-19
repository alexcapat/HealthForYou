package com.acapat.healthforyou

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

@Singleton
class MainNavigator @Inject constructor() {

  private val _navigationRoutes = MutableSharedFlow<String>(extraBufferCapacity = 4)
  val navigationRoutesFlow = _navigationRoutes.asSharedFlow()

  private val _navigateUp = MutableSharedFlow<Unit>(extraBufferCapacity = 4)
  val navigateUpFlow = _navigateUp.asSharedFlow()

  fun navigateTo(route: String) {
    _navigationRoutes.tryEmit(route)
  }

  fun navigateUp() {
    _navigateUp.tryEmit(Unit)
  }
}
