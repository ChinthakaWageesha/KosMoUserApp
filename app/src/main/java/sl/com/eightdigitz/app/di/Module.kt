package sl.com.eightdigitz.app.di

import sl.com.eightdigitz.app.domain.repository.ProfileRepository
import sl.com.eightdigitz.app.data.datasource.AuthDataSource
import sl.com.eightdigitz.app.data.datasource.ProfileDataSource
import sl.com.eightdigitz.app.data.repository.AuthRepositoryImpl
import sl.com.eightdigitz.app.data.repository.ProfileRepositoryImpl
import sl.com.eightdigitz.app.datasource.remote.AuthRemoteDataSourceImpl
import sl.com.eightdigitz.app.datasource.remote.ProfileRemoteDataSourceImpl
import sl.com.eightdigitz.app.domain.repository.AuthRepository
import sl.com.eightdigitz.app.domain.usecase.AuthUseCase
import sl.com.eightdigitz.app.domain.usecase.ProfileUseCase
import sl.com.eightdigitz.app.presentation.main.MainViewModule
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

val viewModelModule: Module = module {
    viewModel {
        MainViewModule(
            authUseCase = get(),
            sharedPreferences = get(),
            supportInterceptor = get()
        )
    }
}

val useCaseModule: Module = module(override = true) {
    factory { AuthUseCase(authRepository = get()) }
    factory { ProfileUseCase(profileRepository = get()) }
}

val repositoryModule: Module = module(override = true) {
    single {
        AuthRepositoryImpl(
            authDataSource = get(),
            sharedPreferences = get()
        ) as AuthRepository
    }
    single {
        ProfileRepositoryImpl(
            profileDataSource = get(),
            sharedPreferences = get()
        ) as ProfileRepository
    }
}

val dataSourceModule: Module = module(override = true) {
    single {
        AuthRemoteDataSourceImpl(
            authApi = get()
        ) as AuthDataSource
    }
    single {
        ProfileRemoteDataSourceImpl(
            profileApi = get()
        ) as ProfileDataSource
    }
}
