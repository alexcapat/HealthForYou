package com.acapat.healthforyou.ui.main.home.bodycomposition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acapat.healthforyou.db.dao.BodyDao
import com.acapat.healthforyou.db.entity.BodyEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class BodyCompositionViewModel @Inject constructor(private val bodyDao: BodyDao) : ViewModel() {

  private val _uiData = MutableStateFlow(BodyCompositionUiData(emptyList()))
  val uiData = _uiData.asStateFlow()

  init {
    bodyDao
      .getAllBMIFlow()
      .onEach { body -> _uiData.update { it.copy(body = body) } }
      .launchIn(viewModelScope)
  }

  fun deleteBMI(bodyEntity: BodyEntity) {
    viewModelScope.launch { bodyDao.deleteBMI(bodyEntity) }
  }
}
