package com.acapat.healthforyou.ui.main.home.steps.data

import android.content.Context
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.records.DistanceRecord
import androidx.health.connect.client.records.StepsRecord
import androidx.health.connect.client.request.AggregateRequest
import androidx.health.connect.client.time.TimeRangeFilter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class StepsDataViewModel @Inject constructor(@ApplicationContext private val context: Context) :
  ViewModel() {

  private val healthConnectClient = HealthConnectClient.getOrCreate(context)
  private val _uiData = MutableStateFlow(StepsDataUiData())
  val uiData = _uiData.asStateFlow()

  init {
    viewModelScope.launch {
      val today = Instant.now()
      val filter = createTimeRangeFilter(today)
      val data = getData(filter)
      _uiData.update { it.copy(isLoading = false, data = data) }
    }
  }
  private suspend fun getData(filter: TimeRangeFilter): StepsData {
    val response =
      healthConnectClient.aggregate(
        request =
          AggregateRequest(
            metrics = setOf(StepsRecord.COUNT_TOTAL, DistanceRecord.DISTANCE_TOTAL),
            timeRangeFilter = filter
          )
      )
    val stepsCount = response[StepsRecord.COUNT_TOTAL]
    val distance = response[DistanceRecord.DISTANCE_TOTAL]
    return StepsData(
      steps = stepsCount ?: 0L,
      distance = distance?.inKilometers ?: 0.0,
    )
  }

  private fun createTimeRangeFilter(day: Instant): TimeRangeFilter {
    val start = day.truncatedTo(ChronoUnit.DAYS)
    val end = start.plus(1, ChronoUnit.DAYS)
    println("Filter: $start - $end")
    return TimeRangeFilter.between(start, end)
  }
}
