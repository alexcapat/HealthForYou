package com.acapat.healthforyou.ui.main.home.steps.availability

import android.content.Intent
import android.net.Uri
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.health.connect.client.HealthConnectClient
import androidx.hilt.navigation.compose.hiltViewModel

val providerPackageName = "com.google.android.apps.healthdata"

private val uriString =
  "market://details?id=$providerPackageName&url=healthconnect%3A%2F%2Fonboarding"

@Composable
fun StepsAvailabilityScreen(viewModel: StepsPermissionViewModel = hiltViewModel()) {
  val uiData = viewModel.uiData.collectAsState().value
  when (uiData.availabilityStatus) {
    HealthConnectClient.SDK_UNAVAILABLE_PROVIDER_UPDATE_REQUIRED -> {
      val context = LocalContext.current
      Button(
        onClick = {
          val intent =
            Intent(Intent.ACTION_VIEW)
              .setPackage("com.android.vending")
              .setData(Uri.parse(uriString))
              .putExtra("overlay", true)
              .putExtra("callerId", context.packageName)
          context.startActivity(intent)
        }
      ) {
        Text(text = "Update Health Connect")
      }
    }
  }
}
