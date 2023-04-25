package com.acapat.healthforyou.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.acapat.healthforyou.db.entity.BodyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BodyDao {

  @Insert suspend fun insertBMI(bmi: BodyEntity)

  @Delete suspend fun deleteBMI(bmi: BodyEntity)

  @Query("SELECT * FROM bmi") suspend fun getAllBMI(): List<BodyEntity>

  @Query("SELECT * FROM bmi") fun getAllBMIFlow(): Flow<List<BodyEntity>>

  @Query("SELECT * FROM bmi ORDER BY (weight / (height * height)) DESC LIMIT 3")
  suspend fun getTopBmi(): List<BodyEntity>
}
