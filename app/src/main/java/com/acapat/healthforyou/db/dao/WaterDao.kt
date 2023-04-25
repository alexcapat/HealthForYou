package com.acapat.healthforyou.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.acapat.healthforyou.db.entity.WaterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WaterDao {

  @Insert suspend fun insertWater(food: WaterEntity)

  @Delete suspend fun deleteWater(food: WaterEntity)

  @Query("SELECT * FROM water") suspend fun getAllWater(): List<WaterEntity>

  @Query("SELECT * FROM water") fun getAllWaterFlow(): Flow<List<WaterEntity>>

  @Query("SELECT * FROM water ORDER BY glasses DESC LIMIT 3")
  suspend fun getTopWaters(): List<WaterEntity>
}
