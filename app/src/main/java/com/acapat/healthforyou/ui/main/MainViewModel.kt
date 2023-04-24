package com.acapat.healthforyou.ui.main

import androidx.health.connect.client.HealthConnectClient
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//@HiltViewModel
class MainViewModel @Inject constructor(private val healthConnectClient: HealthConnectClient) :
  ViewModel() {
  private val _uiData = MutableStateFlow(MainUiData())
  val uiData = _uiData.asStateFlow()

  private val _requestPermission = MutableSharedFlow<Unit>()
  val requestPermission = _requestPermission.asSharedFlow()

  init {
    viewModelScope.launch {
      val isPermissionGranted =
        healthConnectClient.permissionController.getGrantedPermissions().containsAll(PERMISSIONS)
      _uiData.update { it.copy(isPermissionGranted = isPermissionGranted) }
      if (!isPermissionGranted) {
        _requestPermission.emit(Unit)
      }
    }
  }
  fun setPermissionGranted() {
    _uiData.update { it.copy(isPermissionGranted = true) }
  }
}

data class MainUiData(val isPermissionGranted: Boolean = false)
