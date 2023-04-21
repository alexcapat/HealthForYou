package com.acapat.healthforyou.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bmi")
data class BodyEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id: Long = 0L,
    @ColumnInfo("weight") val weight: Float,
    @ColumnInfo("height") val height: Float,
    )
{
    val bmiValue: Float
        get() = weight / (height * height)
}