package com.acapat.healthforyou.ui.main.home.water

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acapat.healthforyou.db.dao.WaterDao
import com.acapat.healthforyou.db.entity.WaterEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class WaterViewModel @Inject constructor(private val waterDao: WaterDao) : ViewModel() {

  private val _uiData = MutableStateFlow(WaterUiData(emptyList()))
  val uiData = _uiData.asStateFlow()

  init {
    waterDao
      .getAllWaterFlow()
      .onEach { water -> _uiData.update { it.copy(water = water) } }
      .launchIn(viewModelScope)
  }

  fun deleteWater(waterEntity: WaterEntity) {
    viewModelScope.launch { waterDao.deleteWater(waterEntity) }
  }
}
