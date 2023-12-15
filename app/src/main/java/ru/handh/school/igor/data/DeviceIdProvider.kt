package ru.handh.school.igor.data

import java.util.UUID

class DeviceIdProvider(
    private val storage: KeyValueStorage
) {
    val deviceId: String
        get() {
            if (storage.deviceId == null) {
                storage.deviceId = UUID.randomUUID().toString()
            }
            return requireNotNull(storage.deviceId)
        }
}