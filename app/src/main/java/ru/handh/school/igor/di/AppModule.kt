package ru.handh.school.igor.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.handh.school.igor.data.DeviceIdProvider
import ru.handh.school.igor.data.IgorRepository
import ru.handh.school.igor.data.KeyValueStorage
import ru.handh.school.igor.data.database.MainDb
import ru.handh.school.igor.domain.profile.ProfileUseCase
import ru.handh.school.igor.domain.projects.ProjectsUseCase
import ru.handh.school.igor.domain.session.SessionUseCase
import ru.handh.school.igor.domain.signin.SignInUseCase
import ru.handh.school.igor.domain.singout.SignOutUseCase
import ru.handh.school.igor.ui.screen.about.AboutViewModel
import ru.handh.school.igor.ui.screen.homepage.HomepageViewModel
import ru.handh.school.igor.ui.screen.profile.ProfileViewModel
import ru.handh.school.igor.ui.screen.signin.SignInViewModel

val appModule = module {
    single {
        KeyValueStorage(get())
    }
    single {
        IgorRepository(get())
    }
    single {
        DeviceIdProvider(get())
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            MainDb::class.java,
            "main_database"
        ).build()
    }
    single {
        get<MainDb>().profileDao
    }

    factory {
        SignInUseCase(get(), get())
    }
    factory {
        SessionUseCase(get(), get(), get())
    }
    factory {
        SignOutUseCase(get())
    }
    factory {
        ProfileUseCase(get(), get())
    }
    factory {
        ProjectsUseCase(get())
    }
    viewModel {
        SignInViewModel(get(), get())
    }
    viewModel {
        ProfileViewModel(get(), get())
    }
    viewModel {
        AboutViewModel(get())
    }
    viewModel {
        HomepageViewModel(get())
    }
}