package ru.handh.school.igor.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        ProfileEntity::class
    ],
    version = 1
)
abstract class MainDb : RoomDatabase() {
    abstract val profileDao: ProfileDao
    companion object {
        fun createDatabase(context: Context): MainDb {
            return Room.databaseBuilder(context, MainDb::class.java, "profile.db").build()
        }
    }
}