package com.acapat.healthforyou.ui.main.home.steps.data

data class StepsDataUiData(
  val isLoading: Boolean = true,
  val data: StepsData = StepsData()
)

data class StepsData(
  val steps: Long = 0L,
  val distance: Double = 0.0,
)
