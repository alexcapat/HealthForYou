package com.acapat.healthforyou.ui.main.home.steps.data

import java.time.Instant

data class StepsDataUiData(
  val instant: Instant = Instant.now(),
  val isLoading: Boolean = true,
  val data: StepsData = StepsData()
)

data class StepsData(
  val steps: Long = 0L,
  val distance: Double = 0.0,
)
