package ru.handh.school.igor.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: ProfileEntity)

    @Query("SELECT * FROM ProfileEntity")
    fun getProfile(): Flow<List<ProfileEntity>>

    @Query("DELETE FROM ProfileEntity")
    suspend fun deleteAll()

    @Query("DELETE FROM ProfileEntity WHERE id = :profileId")
    suspend fun deleteProfileById(profileId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun replaceProfile(profile: ProfileEntity)
}

