package com.acapat.healthforyou.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.acapat.healthforyou.db.dao.BodyDao
import com.acapat.healthforyou.db.dao.FoodDao
import com.acapat.healthforyou.db.dao.WaterDao
import com.acapat.healthforyou.db.entity.BodyEntity
import com.acapat.healthforyou.db.entity.FoodEntity
import com.acapat.healthforyou.db.entity.WaterEntity

@Database(entities = [FoodEntity::class, BodyEntity::class, WaterEntity::class], version = 1)
abstract class HealthForYouDatabase : RoomDatabase() {

  abstract fun foodDao(): FoodDao
  abstract fun bodyDao(): BodyDao
  abstract fun waterDao(): WaterDao

}
