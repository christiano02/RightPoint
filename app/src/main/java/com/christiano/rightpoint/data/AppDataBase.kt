package com.christiano.rightpoint.data

import android.os.Build
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PointEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun pointDao(): PointDao
}