package com.acapat.healthforyou.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "water")
data class WaterEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id: Long = 0L,
    @ColumnInfo("glasses") val glasses: Int,
    @ColumnInfo("ml") val ml: Int,
)
{
    val total: Int
        get() = glasses
}