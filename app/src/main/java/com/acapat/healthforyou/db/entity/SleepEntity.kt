package com.acapat.healthforyou.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sleep")
data class SleepEntity(
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo("id") val id: Long = 0L,
  @ColumnInfo("sleep") val sleep: Int,
) {
  val total: Int
    get() = sleep
}
