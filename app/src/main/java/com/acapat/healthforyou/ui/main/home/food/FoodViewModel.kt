package com.acapat.healthforyou.ui.main.home.food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acapat.healthforyou.db.dao.FoodDao
import com.acapat.healthforyou.db.entity.FoodEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class FoodViewModel @Inject constructor(private val foodDao: FoodDao) : ViewModel() {

  private val _uiData = MutableStateFlow(FoodUiData(emptyList()))
  val uiData = _uiData.asStateFlow()
  init {
    foodDao
      .getAllFoodsFlow()
      .onEach { foods -> _uiData.update { it.copy(foods = foods) } }
      .launchIn(viewModelScope)
  }
  fun deleteFood(foodEntity: FoodEntity) {
    viewModelScope.launch { foodDao.deleteFood(foodEntity) }
  }
}
