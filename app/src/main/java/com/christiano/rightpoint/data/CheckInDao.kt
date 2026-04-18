package com.christiano.rightpoint.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckInDao {
    @Insert
    suspend fun insertCheckIn(checkIn: CheckInEntity)

    @Query("SELECT * FROM checkIns_table ORDER BY id DESC")
    fun listAllTheCheckIns(): Flow<List<CheckInEntity>>

}