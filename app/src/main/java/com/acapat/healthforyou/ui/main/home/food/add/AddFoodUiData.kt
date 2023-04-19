package com.acapat.healthforyou.ui.main.home.food.add

data class AddFoodUiData(
  val breakfast: String = "",
  val lunch: String = "",
  val dinner: String = "",
  val isLoading: Boolean = false
) {

  val isBreakfastValid = breakfast.toIntOrNull() != null
  val isLunchValid = lunch.toIntOrNull() != null
  val isDinnerValid = dinner.toIntOrNull() != null

  val isValid = isBreakfastValid && isLunchValid && isDinnerValid

  val total: Int?
    get() {
      val breakfastValue = breakfast.toIntOrNull() ?: return null
      val lunchValue = lunch.toIntOrNull() ?: return null
      val dinnerValue = dinner.toIntOrNull() ?: return null
      return breakfastValue + lunchValue + dinnerValue
    }
}
