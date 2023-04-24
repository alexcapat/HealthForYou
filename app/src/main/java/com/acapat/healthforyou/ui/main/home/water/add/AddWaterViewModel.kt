package com.acapat.healthforyou.ui.main.home.water.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acapat.healthforyou.MainNavigator
import com.acapat.healthforyou.db.dao.WaterDao
import com.acapat.healthforyou.db.entity.WaterEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class AddWaterViewModel
@Inject
constructor(private val waterDao: WaterDao, private val navigator: MainNavigator) : ViewModel() {
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

  fun insertWater() {
    viewModelScope.launch {
      _uiData.update { it.copy(isLoading = true) }
      val waterEntity = with(_uiData.value) { WaterEntity(glasses = total, ml = total * 250) }
      waterDao.insertWater(waterEntity)
      navigator.navigateUp()
    }
  }
}
