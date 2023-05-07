package com.acapat.healthforyou.ui.main.home.steps.permission

import android.content.Context
import androidx.health.connect.client.HealthConnectClient
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acapat.healthforyou.MainNavigator
import com.acapat.healthforyou.ui.main.home.steps.StepsScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class StepsPermissionsViewModel
@Inject
constructor(
  @ApplicationContext private val context: Context,
  private val navigator: MainNavigator,
) : ViewModel() {
  private val healthConnectClient = HealthConnectClient.getOrCreate(context)
  private val _uiData = MutableStateFlow(StepsPermissionUiData())
  val uiData = _uiData.asStateFlow()

  init {
    _uiData
      .onEach {
        if (it.granted == true) {
          navigator.navigateTo(StepsScreen.STEPS_DATA.route)
        }
      }
      .launchIn(viewModelScope)
    viewModelScope.launch {
      val grantedPermissions = healthConnectClient.permissionController.getGrantedPermissions()
      setPermissionGranted(granted = grantedPermissions.containsAll(PERMISSIONS))
    }
  }
  fun setPermissionGranted(granted: Boolean) {
    _uiData.update { it.copy(granted = granted) }
  }
}

data class StepsPermissionUiData(
  val granted: Boolean? = null,
)
