package com.acapat.healthforyou.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.acapat.healthforyou.db.entity.FoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Insert
    suspend fun insertFood(food: FoodEntity)

    @Delete
    suspend fun deleteFood(food: FoodEntity)

    @Query("SELECT * FROM food")
    suspend fun getAllFoods(): List<FoodEntity>

    @Query("SELECT * FROM food")
    fun getAllFoodsFlow(): Flow<List<FoodEntity>>

    @Query("SELECT * FROM food ORDER BY (breakfast + lunch + dinner) DESC LIMIT 3")
    suspend fun getTopFoods(): List<FoodEntity>
}