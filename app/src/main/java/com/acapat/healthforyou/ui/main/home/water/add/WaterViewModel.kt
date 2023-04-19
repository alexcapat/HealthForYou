package com.acapat.healthforyou.ui.main.home.water.add

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class WaterViewModel @Inject constructor() : ViewModel() {
  private val _uiData = MutableStateFlow(AddWaterUiData())
  val uiData = _uiData.asStateFlow()

  fun addGlassOfWater(fullGlass: Int) {
    val newTotal = (_uiData.value.fullGlass - _uiData.value.emptyGlass) + 1
    if (newTotal in 0..20) {
      _uiData.update { it.copy(fullGlass = fullGlass, emptyGlass = it.fullGlass - newTotal) }
    }
  }

  fun removeGlassOfWater(emptyGlass: Int) {
    val newTotal = (_uiData.value.fullGlass - _uiData.value.emptyGlass) - 1
    if (newTotal in 0..20) {
      _uiData.update { it.copy(emptyGlass = emptyGlass, fullGlass = it.emptyGlass + newTotal) }
    }
  }
}
