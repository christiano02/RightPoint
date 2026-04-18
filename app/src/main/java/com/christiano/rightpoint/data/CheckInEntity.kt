package com.christiano.rightpoint.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "checkIns_table")
data class CheckInEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,
    val time: String,
    val type: String,

)
