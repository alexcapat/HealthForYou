package com.acapat.healthforyou.ui.main.home.sleep

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acapat.healthforyou.db.dao.SleepDao
import com.acapat.healthforyou.db.entity.SleepEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SleepViewModel @Inject constructor(private val sleepDao: SleepDao) : ViewModel() {
  private val _uiData = MutableStateFlow(SleepUiData(emptyList()))
  val uiData = _uiData.asStateFlow()

  init {
    sleepDao
      .getAllSleepsFlow()
      .onEach { sleeps -> _uiData.update { it.copy(sleeps = sleeps) } }
      .launchIn(viewModelScope)
  }

  fun deleteSleep(sleepEntity: SleepEntity) {
    viewModelScope.launch { sleepDao.deleteSleep(sleepEntity) }
  }
}
