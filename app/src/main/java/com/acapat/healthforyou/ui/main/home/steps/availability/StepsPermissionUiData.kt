package com.acapat.healthforyou.ui.main.home.steps.availability

import androidx.health.connect.client.HealthConnectClient.Companion.AvailabilityStatus

data class StepsPermissionUiData(
  @AvailabilityStatus val availabilityStatus: Int,
)
