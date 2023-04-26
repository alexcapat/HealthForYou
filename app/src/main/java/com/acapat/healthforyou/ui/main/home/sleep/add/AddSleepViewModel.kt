package com.acapat.healthforyou.ui.main.home.sleep.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acapat.healthforyou.MainNavigator
import com.acapat.healthforyou.db.dao.SleepDao
import com.acapat.healthforyou.db.entity.SleepEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class AddSleepViewModel
@Inject
constructor(
  private val sleepDao: SleepDao,
  private val navigator: MainNavigator,
) : ViewModel() {

  private val _uiData = MutableStateFlow(AddSleepUiData())
  val uiData = _uiData.asStateFlow()

  fun setSleep(sleep: String) {
    _uiData.update { it.copy(sleep = sleep) }
  }

  fun insertSleep() {
    viewModelScope.launch {
      _uiData.update { it.copy(isLoading = true) }
      val sleepEntity =
        with(_uiData.value) {
          SleepEntity(
            sleep = sleep.toInt(),
          )
        }
      sleepDao.insertSleep(sleepEntity)
      navigator.navigateUp()
    }
  }
}
