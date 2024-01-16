package ru.handh.school.igor.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    @Query("SELECT * FROM ProfileEntity")
    fun getProfile(): Flow<List<ProfileEntity>>
    @Query("DELETE FROM ProfileEntity")
    suspend fun deleteAll()
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun replaceProfile(profile: ProfileEntity)
}
