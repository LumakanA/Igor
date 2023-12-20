package ru.handh.school.igor.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.handh.school.igor.data.DeviceIdProvider
import ru.handh.school.igor.data.IgorRepository
import ru.handh.school.igor.data.KeyValueStorage
import ru.handh.school.igor.domain.session.SessionUseCase
import ru.handh.school.igor.domain.signin.SignInUseCase
import ru.handh.school.igor.domain.singout.SignOutUseCase
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
    factory {
        SignInUseCase(get(), get())
    }
    factory {
        SessionUseCase(get(), get(), get())
    }
    factory {
        SignOutUseCase(get())
    }
    viewModel {
        SignInViewModel(get(), get())
    }
    viewModel {
        ProfileViewModel(get())
    }
}