package sl.com.eightdigitz.authentication.di

import sl.com.eightdigitz.authentication.data.datasource.AuthRemoteDataSource
import sl.com.eightdigitz.authentication.data.repository.AuthRepositoryImpl
import sl.com.eightdigitz.authentication.datasource.remote.AuthRemoteDataSourceImpl
import sl.com.eightdigitz.authentication.domain.repository.AuthRepository
import sl.com.eightdigitz.authentication.domain.usecase.AuthUseCase
import sl.com.eightdigitz.authentication.presentation.forgotpassword.ForgotPasswordViewModel
import sl.com.eightdigitz.authentication.presentation.login.LoginViewModel
import sl.com.eightdigitz.authentication.presentation.registration.RegistrationViewModel
import sl.com.eightdigitz.authentication.presentation.resetpassword.ResetPasswordViewModel
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

fun injectAuthApiLayer() = loadAuthAPiLayer

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
    viewModel { LoginViewModel(authUseCase = get()) }
    viewModel { ForgotPasswordViewModel(authUseCase = get()) }
    viewModel { RegistrationViewModel(authUseCase = get()) }
    viewModel { ResetPasswordViewModel(authUseCase = get()) }
}

val useCaseModule: Module = module(override = true) {
    factory { AuthUseCase(authRepository = get()) }
}

val repositoryModule: Module = module(override = true) {
    single {
        AuthRepositoryImpl(
            authRemoteDataSource = get(),
            sharedPreferences = get()
        ) as AuthRepository
    }
}

val dataSourceModule: Module = module(override = true) {
    single {
        AuthRemoteDataSourceImpl(
            authApi = get(),
            forgotPasswordApi = get(),
            resetPasswordApi = get(),
            mSharedPreferences = get(),
            mSupportInteceptor = get()
        ) as AuthRemoteDataSource
    }
}
