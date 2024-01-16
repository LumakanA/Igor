package ru.handh.school.igor.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        ProfileEntity::class
    ],
    version = 1
)
abstract class MainDb : RoomDatabase() {
    abstract val profileDao: ProfileDao
}