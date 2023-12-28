package ru.handh.school.igor.data

import android.content.Context
import android.util.Log
import io.ktor.client.plugins.auth.providers.BearerTokens

private const val SharedPreferencesDefaultName = "app_preferences"

private const val KeyAccessToken = "access_token"
private const val KeyRefreshToken = "refresh_token"
private const val KeyDeviceId = "device_id"
private const val TAG = "KeyValueStorage"

class KeyValueStorage(
    context: Context
) {
    private val prefs = context.getSharedPreferences(
        SharedPreferencesDefaultName,
        Context.MODE_PRIVATE
    )

    var accessToken: String?
        get() {
            val value = getString(KeyAccessToken)
            Log.d(TAG, "Access Token: $value")
            return value
        }
        set(value) {
            Log.d(TAG, "Setting Access Token: $value")
            putString(KeyAccessToken, value)
        }

    var refreshToken: String?
        get() {
            val value = getString(KeyRefreshToken)
            Log.d(TAG, "Refresh Token: $value")
            return value
        }
        set(value) {
            Log.d(TAG, "Setting Refresh Token: $value")
            putString(KeyRefreshToken, value)
        }

    var deviceId: String?
        get() {
            val value = getString(KeyDeviceId)
            Log.d(TAG, "Device ID: $value")
            return value
        }
        set(value) {
            Log.d(TAG, "Setting Device ID: $value")
            putString(KeyDeviceId, value)
        }

    private fun getString(key: String): String? {
        val value = prefs.getString(key, null)
        Log.d(TAG, "Getting String for key $key: $value")
        return value
    }

    private fun putString(key: String, value: String?) {
        Log.d(TAG, "Putting String for key $key: $value")
        prefs.edit()
            .putString(key, value)
            .apply()
    }

    fun loadTokens(): BearerTokens {
        val accessToken = this.accessToken
        val refreshToken = this.refreshToken
        return BearerTokens(accessToken.toString(), refreshToken.toString())
    }
}