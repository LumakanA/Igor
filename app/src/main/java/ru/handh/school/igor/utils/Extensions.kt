package ru.handh.school.igor.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build

private fun PackageManager.getPackageInfoCompat(
    packageName: String,
    flags: Int = 0
): PackageInfo =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(flags.toLong()))
    } else {
        getPackageInfo(packageName, flags)
    }

private val PackageInfo.versionCodeCompat: Long
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        longVersionCode
    } else {
        @Suppress("DEPRECATION")
        versionCode.toLong()
    }

val Context.versionName: String
    get() {
        val manager = packageManager
        val info = manager.getPackageInfoCompat(packageName)
        return info.versionName
    }

val Context.versionCode: Long
    get() {
        val manager = packageManager
        val info = manager.getPackageInfoCompat(packageName)
        return info.versionCodeCompat
    }