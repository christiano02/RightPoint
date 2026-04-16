package com.christiano.rightpoint.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "points_table")
data class PointEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,
    val time: String,
    val type: String,

)
