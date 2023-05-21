package com.acapat.healthforyou.ui.main.home.steps.permission

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.health.connect.client.PermissionController
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.DistanceRecord
import androidx.health.connect.client.records.StepsRecord
import androidx.hilt.navigation.compose.hiltViewModel

val PERMISSIONS =
  setOf(
    HealthPermission.getReadPermission(StepsRecord::class),
    HealthPermission.getReadPermission(DistanceRecord::class)
  )

@Composable
fun StepsPermissionScreen(viewModel: StepsPermissionsViewModel = hiltViewModel()) {
  val requestPermissionActivityContract = remember {
    PermissionController.createRequestPermissionResultContract()
  }
  val requestPermissions =
    rememberLauncherForActivityResult(contract = requestPermissionActivityContract) {
      val granted = it.containsAll(PERMISSIONS)
      viewModel.setPermissionGranted(granted)
    }

  val uiData = viewModel.uiData.collectAsState().value

  when (uiData.granted) {
    null,
    true -> {
      // afisam ca se incarca ceve
      CircularProgressIndicator()
    }
    false -> {
      Button(onClick = { requestPermissions.launch(PERMISSIONS) }) {
        Text(text = "Request Permission")
      }
    }
  }
}
