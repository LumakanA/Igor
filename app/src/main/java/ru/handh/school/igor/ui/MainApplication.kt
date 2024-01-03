package ru.handh.school.igor.ui

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.handh.school.igor.data.database.MainDb
import ru.handh.school.igor.di.appModule

class MainApplication : Application() {
    val database by lazy { MainDb.createDatabase(this) }
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}