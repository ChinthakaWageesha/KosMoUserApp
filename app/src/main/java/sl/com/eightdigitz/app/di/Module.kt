package sl.com.eightdigitz.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import sl.com.eightdigitz.app.data.datasource.*
import sl.com.eightdigitz.app.data.repository.*
import sl.com.eightdigitz.app.datasource.remote.*
import sl.com.eightdigitz.app.domain.repository.*
import sl.com.eightdigitz.app.domain.usecase.*
import sl.com.eightdigitz.app.presentation.explore.ExploreViewModel
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
            orderUseCase = get(),
            paymentUseCase = get()
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

    viewModel {
        ExploreViewModel(
            exploreUseCase = get()
        )
    }
}

val useCaseModule: Module = module(override = true) {
    factory { SearchUseCase(searchRepository = get()) }
    factory { OrderUseCase(orderRepository = get()) }
    factory { ProfileUseCase(profileRepository = get()) }
    factory { SettingsUseCase(settingsRepository = get()) }
    factory { ExploreUseCase(exploreRepository = get()) }
    factory { PaymentUseCase(paymentRepository = get()) }
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

    single {
        ExploreRepositoryImpl(
            exploreDataSource = get()
        ) as ExploreRepository
    }

    single {
        PaymentRepositoryImpl(
            paymentDataSource = get()
        ) as PaymentRepository
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

    single {
        ExploreDataSourceImpl(
            exploreApi = get()
        ) as ExploreDataSource
    }

    single {
        PaymentDataSourceImpl(
            paymentsApi = get()
        ) as PaymentDataSource
    }
}
