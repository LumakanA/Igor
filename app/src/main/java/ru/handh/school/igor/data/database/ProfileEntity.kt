package ru.handh.school.igor.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.handh.school.igor.R

@Entity(tableName = "ProfileEntity")
data class ProfileEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val surname:String,
    val icon: Int = R.drawable.icon_profile
)
