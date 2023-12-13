package ru.handh.school.igor.data.util

import android.content.Context
import android.content.SharedPreferences
import java.util.UUID

class PreferencesManager(context: Context) {

    companion object {
        private const val PREF_NAME = "IgorApp"
        private const val DEVICE_ID_KEY = "DeviceId"
    }

    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    val getDeviceId: String
        get() = preferences.getString(DEVICE_ID_KEY, "") ?: generateAndSaveDeviceId()

    init {
        generateAndSaveDeviceId()
    }

    private fun generateAndSaveDeviceId(): String {
        val newDeviceId = UUID.randomUUID().toString()
        preferences.edit().putString(DEVICE_ID_KEY, newDeviceId).apply()
        return newDeviceId
    }
}
