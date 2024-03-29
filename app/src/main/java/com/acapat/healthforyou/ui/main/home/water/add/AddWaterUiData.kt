package com.acapat.healthforyou.ui.main.home.water.add

data class AddWaterUiData(val emptyGlass: Int = 0, val fullGlass: Int = 0, val isLoading: Boolean = false
) {
  val total: Int
    get() = fullGlass - emptyGlass
  val totalMillis: Int
    get() = (fullGlass - emptyGlass) * 250

  val isWaterValid = true

  val isValid = isWaterValid
}
