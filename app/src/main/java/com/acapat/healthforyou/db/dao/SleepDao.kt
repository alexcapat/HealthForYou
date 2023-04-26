package com.acapat.healthforyou.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.acapat.healthforyou.db.entity.SleepEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SleepDao {

  @Insert suspend fun insertSleep(sleep: SleepEntity)

  @Delete suspend fun deleteSleep(sleep: SleepEntity)

  @Query("SELECT * FROM sleep") suspend fun getAllSleeps(): List<SleepEntity>

  @Query("SELECT * FROM sleep") fun getAllSleepsFlow(): Flow<List<SleepEntity>>

  @Query("SELECT * FROM sleep ORDER BY sleep DESC LIMIT 3")
  suspend fun getTopSleeps(): List<SleepEntity>
}
