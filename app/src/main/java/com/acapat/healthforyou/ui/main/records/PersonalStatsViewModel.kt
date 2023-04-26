package com.acapat.healthforyou.ui.main.records

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acapat.healthforyou.db.dao.BodyDao
import com.acapat.healthforyou.db.dao.FoodDao
import com.acapat.healthforyou.db.dao.SleepDao
import com.acapat.healthforyou.db.dao.WaterDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PersonalStatsViewModel
@Inject
constructor(
  private val foodDao: FoodDao,
  private val sleepDao: SleepDao,
  private val bodyDao: BodyDao,
  private val waterDao: WaterDao
) : ViewModel() {

  private val _personalStats = MutableStateFlow(emptyList<PersonalStat>())
  val personalStats = _personalStats.asStateFlow()

  init {
    viewModelScope.launch {
      val topFoods = foodDao.getTopFoods().map { it.total.toString() }
      val topBodies = bodyDao.getTopBmi().map { it.bmiValue.toString() }
      val topWaters = waterDao.getTopWaters().map { it.total.toString() }
      val topSleep = sleepDao.getTopSleeps().map { it.total.toString() }
      _personalStats.value =
        listOf(
          PersonalStat("Top Calories Intake", topFoods),
          PersonalStat("Top BMI Score", topBodies),
          PersonalStat("Top Glasses of Water", topWaters),
          PersonalStat("Top Hours of Sleep", topSleep)
        )
    }
  }
}
