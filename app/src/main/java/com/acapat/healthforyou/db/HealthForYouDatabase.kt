package com.acapat.healthforyou.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.acapat.healthforyou.db.dao.FoodDao
import com.acapat.healthforyou.db.entity.FoodEntity

@Database(
    entities = [FoodEntity::class],
    version = 1
)
abstract class HealthForYouDatabase : RoomDatabase(){

    abstract fun foodDao():FoodDao

}