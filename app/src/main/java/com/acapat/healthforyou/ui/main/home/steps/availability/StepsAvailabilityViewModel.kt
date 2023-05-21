package com.acapat.healthforyou.ui.main.home.steps.availability

import android.content.Context
import android.util.Log
import androidx.health.connect.client.HealthConnectClient
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import com.acapat.healthforyou.MainNavigator
import com.acapat.healthforyou.ui.main.home.HomeScreen
import com.acapat.healthforyou.ui.main.home.steps.StepsScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class StepsPermissionViewModel
@Inject
constructor(
  @ApplicationContext private val context: Context,
  private val navigator: MainNavigator
) : ViewModel() {

  private val _uiData =
    MutableStateFlow(
      StepsPermissionUiData(availabilityStatus = HealthConnectClient.sdkStatus(context))
    )
  val uiData = _uiData.asStateFlow()

  init {
    _uiData
      .onEach {
        Log.i("Availability", it.availabilityStatus.toString())
        if (it.availabilityStatus == HealthConnectClient.SDK_AVAILABLE) {
          navigator.navigateTo(
            StepsScreen.PERMISSION.route,
            navOptions { popUpTo(HomeScreen.STEPS_GOAL.route) }
          )
        }
      }
      .launchIn(viewModelScope)
  }
}
