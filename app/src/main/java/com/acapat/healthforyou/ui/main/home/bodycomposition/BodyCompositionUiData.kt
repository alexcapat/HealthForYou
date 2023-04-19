package com.acapat.healthforyou.ui.main.home.bodycomposition

data class BodyCompositionUiData(
  val weight: String = "",
  val height: String = "",
/*val age: Int = 0,*/
) {
  val isWeightValid = weight.toFloatOrNull() != null
  val isHeightValid = height.toFloatOrNull() != null

  val isValid: Boolean
    get() = isHeightValid && isWeightValid

  //  val weight: Float = 0f,
  //  val height: Float = 0f,

  val bmi: Float?
    get() {
      val weightValue = weight.toFloatOrNull() ?: return null
      val heightValue = height.toFloatOrNull() ?: return null
      return weightValue / (heightValue * heightValue)
    }
}
