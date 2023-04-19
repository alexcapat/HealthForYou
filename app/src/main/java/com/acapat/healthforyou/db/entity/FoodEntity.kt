package com.acapat.healthforyou.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class FoodEntity(
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo("id") val id: Long = 0L,
  @ColumnInfo("breakfast") val breakfast: Int,
  @ColumnInfo("lunch") val lunch: Int,
  @ColumnInfo("dinner") val dinner: Int,

) {
  val total: Int
    get() = breakfast + lunch + dinner
}
