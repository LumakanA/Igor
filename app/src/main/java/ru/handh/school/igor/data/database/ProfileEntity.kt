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
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val otherProfile = other as ProfileEntity

        return id == otherProfile.id && name == otherProfile.name && surname == otherProfile.surname
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + surname.hashCode()
        return result
    }
}
