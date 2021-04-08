package sl.com.eightdigitz.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import sl.com.eightdigitz.app.data.datasource.OrderDataSource
import sl.com.eightdigitz.app.data.datasource.ProfileDataSource
import sl.com.eightdigitz.app.data.datasource.SearchDataSource
import sl.com.eightdigitz.app.data.datasource.SettingsDataSource
import sl.com.eightdigitz.app.data.repository.OrderRepositoryImpl
import sl.com.eightdigitz.app.data.repository.ProfileRepositoryImpl
import sl.com.eightdigitz.app.data.repository.SearchRepositoryImpl
import sl.com.eightdigitz.app.data.repository.SettingsRepositoryImpl
import sl.com.eightdigitz.app.datasource.remote.OrderDataSourceImpl
import sl.com.eightdigitz.app.datasource.remote.ProfileDataSourceImpl
import sl.com.eightdigitz.app.datasource.remote.SearchDataSourceImpl
import sl.com.eightdigitz.app.datasource.remote.SettingsDataSourceImpl
import sl.com.eightdigitz.app.domain.repository.OrderRepository
import sl.com.eightdigitz.app.domain.repository.ProfileRepository
import sl.com.eightdigitz.app.domain.repository.SearchRepository
import sl.com.eightdigitz.app.domain.repository.SettingsRepository
import sl.com.eightdigitz.app.domain.usecase.OrderUseCase
import sl.com.eightdigitz.app.domain.usecase.ProfileUseCase
import sl.com.eightdigitz.app.domain.usecase.SearchUseCase
import sl.com.eightdigitz.app.domain.usecase.SettingsUseCase
import sl.com.eightdigitz.app.presentation.search.SearchViewModel
import sl.com.eightdigitz.app.presentation.order.OrderViewModel
import sl.com.eightdigitz.app.presentation.preferences.PreferencesViewModel
import sl.com.eightdigitz.app.presentation.profile.ProfileViewModel
import sl.com.eightdigitz.app.presentation.search.LogSearchViewModel
import sl.com.eightdigitz.app.presentation.settings.SettingsViewModel

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
        SearchViewModel(
            searchUseCase = get()
        )
    }

    viewModel {
        LogSearchViewModel(
            searchUseCase = get()
        )
    }

    viewModel {
        OrderViewModel(
            orderUseCase = get()
        )
    }

    viewModel {
        ProfileViewModel(
            profileUseCase = get()
        )
    }

    viewModel {
        PreferencesViewModel(
            profileUseCase = get()
        )
    }

    viewModel {
        SettingsViewModel(
            settingsUseCase = get()
        )
    }
}

val useCaseModule: Module = module(override = true) {
    factory { SearchUseCase(searchRepository = get()) }
    factory { OrderUseCase(orderRepository = get()) }
    factory { ProfileUseCase(profileRepository = get()) }
    factory { SettingsUseCase(settingsRepository = get()) }
}

val repositoryModule: Module = module(override = true) {
    single {
        SearchRepositoryImpl(
            searchDataSource = get()
        ) as SearchRepository
    }

    single {
        OrderRepositoryImpl(
            orderDataSource = get()
        ) as OrderRepository
    }

    single {
        ProfileRepositoryImpl(
            profileDataSource = get()
        ) as ProfileRepository
    }

    single {
        SettingsRepositoryImpl(
            settingsDataSource = get()
        ) as SettingsRepository
    }
}

val dataSourceModule: Module = module(override = true) {
    single {
        SearchDataSourceImpl(
            mSharedPreferences = get(),
            preferencesApi = get(),
            searchHistoryApi = get()
        ) as SearchDataSource
    }

    single {
        OrderDataSourceImpl(
            mSharedPreferences = get(),
            orderApi = get()
        ) as OrderDataSource
    }

    single {
        ProfileDataSourceImpl(
            mSharedPreferences = get(),
            authApi = get(),
            multimediaApi = get(),
            preferencesApi = get()
        ) as ProfileDataSource
    }

    single {
        SettingsDataSourceImpl(
            mSharedPreferences = get(),
            preferencesApi = get()
        ) as SettingsDataSource
    }
}
