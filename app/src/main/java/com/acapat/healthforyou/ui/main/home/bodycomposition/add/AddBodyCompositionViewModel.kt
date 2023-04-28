package com.acapat.healthforyou.ui.main.home.bodycomposition.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acapat.healthforyou.MainNavigator
import com.acapat.healthforyou.db.dao.BodyDao
import com.acapat.healthforyou.db.entity.BodyEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class AddBodyCompositionViewModel
@Inject
constructor(private val bodyDao: BodyDao, private val navigator: MainNavigator) : ViewModel() {
  private val _uiData = MutableStateFlow(AddBodyCompositionUiData())
  val uiData = _uiData.asStateFlow()
  fun setWeight(weight: String) {
    _uiData.update { it.copy(weight = weight) }
  }
  fun setHeight(height: String) {
    _uiData.update { it.copy(height = height) }
  }

  fun insertBMI() {
    viewModelScope.launch {
      _uiData.update { it.copy(isLoading = true) }
      val bodyEntity =
        with(_uiData.value) {
          BodyEntity(
            weight = weight.toFloat(),
            height = height.toFloat(),
          )
        }
      bodyDao.insertBMI(bodyEntity)
      navigator.navigateUp()
    }
  }
}
