package com.example.vocalbridge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.vocalbridge.data.model.SessionEntity

@Database(
    entities = [SessionEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun sessionDao(): SessionDao
}