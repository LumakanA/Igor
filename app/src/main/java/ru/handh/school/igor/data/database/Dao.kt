package ru.handh.school.igor.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    suspend fun insertItem(profileEntity: ProfileEntity)
    @Query("SELECT * FROM ProfileEntity")
    fun getAllItems(): Flow<List<ProfileEntity>>
}