package sl.com.eightdigitz.authentication.di

import sl.com.eightdigitz.authentication.data.datasource.AuthDataSource
import sl.com.eightdigitz.authentication.data.repository.AuthRepositoryImpl
import sl.com.eightdigitz.authentication.datasource.remote.AuthDataSourceImpl
import sl.com.eightdigitz.authentication.domain.repository.AuthRepository
import sl.com.eightdigitz.authentication.domain.usecase.AuthUseCase
import sl.com.eightdigitz.authentication.presentation.verifyOTP.OTPViewModel
import sl.com.eightdigitz.authentication.presentation.registration.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            viewModelModule,
            useCaseModule,
            repositoryModule,
            dataSourceModule
        )
    )
}

private val loadAuthAPiLayer by lazy {
    loadKoinModules(
        listOf(
            useCaseModule,
            repositoryModule,
            dataSourceModule
        )
    )
}

val viewModelModule: Module = module {
    viewModel { OTPViewModel(authUseCase = get()) }
    viewModel { RegistrationViewModel(authUseCase = get()) }
}

val useCaseModule: Module = module(override = true) {
    factory { AuthUseCase(authRepository = get()) }
}

val repositoryModule: Module = module(override = true) {
    single {
        AuthRepositoryImpl(
            authDataSource = get()
        ) as AuthRepository
    }
}

val dataSourceModule: Module = module(override = true) {
    single {
        AuthDataSourceImpl(
            authApi = get(),
            mSharedPreferences = get(),
            mSupportInterceptor = get()
        ) as AuthDataSource
    }
}
