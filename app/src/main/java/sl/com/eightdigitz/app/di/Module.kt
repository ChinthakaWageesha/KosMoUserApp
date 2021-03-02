package sl.com.eightdigitz.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import sl.com.eightdigitz.app.data.datasource.SearchDataSource
import sl.com.eightdigitz.app.data.repository.SearchRepositoryImpl
import sl.com.eightdigitz.app.datasource.remote.SearchDataSourceImpl
import sl.com.eightdigitz.app.domain.repository.SearchRepository
import sl.com.eightdigitz.app.domain.usecase.SearchUseCase
import sl.com.eightdigitz.app.presentation.search.SearchViewModel
import sl.com.eightdigitz.app.presentation.main.MainViewModel
import sl.com.eightdigitz.app.presentation.search.LogSearchViewModel

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
        MainViewModel(
            sharedPreferences = get(),
            supportInterceptor = get()
        )
    }
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
}

val useCaseModule: Module = module(override = true) {
    factory { SearchUseCase(searchRepository = get()) }
}

val repositoryModule: Module = module(override = true) {
    single {
        SearchRepositoryImpl(
            searchDataSource = get()
        ) as SearchRepository
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
}
