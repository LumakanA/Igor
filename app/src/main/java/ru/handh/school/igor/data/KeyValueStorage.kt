package ru.handh.school.igor.data

import android.content.Context

private const val SharedPreferencesDefaultName = "app_preferences"

private const val KeyAccessToken = "access_token"
private const val KeyRefreshToken = "refresh_token"
private const val KeyDeviceId = "device_id"

class KeyValueStorage(
    context: Context
) {
    private val prefs = context.getSharedPreferences(
        SharedPreferencesDefaultName,
        Context.MODE_PRIVATE
    )

    var accessToken: String?
        get() {
            return getString(KeyAccessToken)
        }
        set(value) {
            putString(KeyAccessToken, value)
        }

    var refreshToken: String?
        get() {
            return getString(KeyRefreshToken)
        }
        set(value) {
            putString(KeyRefreshToken, value)
        }

    var deviceId: String?
        get() {
            return getString(KeyDeviceId)
        }
        set(value) {
            putString(KeyDeviceId, value)
        }

    private fun getString(key: String): String? {
        return prefs.getString(key, null)
    }

    private fun putString(key: String, value: String?) {
        prefs.edit()
            .putString(key, value)
            .apply()
    }
}