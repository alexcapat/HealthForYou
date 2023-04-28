package com.acapat.healthforyou.ui.main.home.food.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acapat.healthforyou.MainNavigator
import com.acapat.healthforyou.db.dao.FoodDao
import com.acapat.healthforyou.db.entity.FoodEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class AddFoodViewModel
@Inject
constructor(private val foodDao: FoodDao, private val navigator: MainNavigator) : ViewModel() {
  private val _uiData = MutableStateFlow(AddFoodUiData())
  val uiData = _uiData.asStateFlow()

  fun setBreakfast(breakfast: String) {
    _uiData.update { it.copy(breakfast = breakfast) }
  }
  fun setDinner(dinner: String) {
    _uiData.update { it.copy(dinner = dinner) }
  }
  fun setLunch(lunch: String) {
    _uiData.update { it.copy(lunch = lunch) }
  }
  fun insertFood() {
    viewModelScope.launch {
      _uiData.update { it.copy(isLoading = true) }
      val foodEntity =
        with(_uiData.value) {
          FoodEntity(breakfast = breakfast.toInt(), lunch = lunch.toInt(), dinner = dinner.toInt())
        }
      foodDao.insertFood(foodEntity)
      navigator.navigateUp()
    }
  }
}
