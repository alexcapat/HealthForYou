package com.acapat.healthforyou.ui.main.home.steps.permission

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.health.connect.client.PermissionController
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.StepsRecord
import androidx.hilt.navigation.compose.hiltViewModel

val PERMISSIONS =
  setOf(
    HealthPermission.getReadPermission(StepsRecord::class),
    HealthPermission.getWritePermission(StepsRecord::class)
  )

val requestPermissionActivityContract = PermissionController.createRequestPermissionResultContract()

@Composable
fun StepsPermissionScreen(viewModel: StepsPermissionsViewModel = hiltViewModel()) {
  val requestPermissions =
    rememberLauncherForActivityResult(contract = requestPermissionActivityContract) {
      val granted = it.containsAll(PERMISSIONS)
      viewModel.setPermissionGranted(granted)
    }

  val uiData = viewModel.uiData.collectAsState().value

  when (uiData.granted) {
    null, true -> {
      // afisam ca se incarca ceve
      CircularProgressIndicator()
    }
    false -> {
      requestPermissions.launch(PERMISSIONS)
    }

  }
}
