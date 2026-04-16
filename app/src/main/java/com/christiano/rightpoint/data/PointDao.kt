package com.christiano.rightpoint.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PointDao {
    @Insert
    suspend fun insertPoint(point: PointEntity)

    @Query("SELECT * FROM points_table ORDER BY id DESC")
    fun listAllThePoints(): Flow<List<PointEntity>>

}