package sl.com.eightdigitz.country_picker


import sl.com.eightdigitz.country_picker.data.datasource.CountriesDataSource
import sl.com.eightdigitz.country_picker.data.repository.CountriesRepositoryImpl
import sl.com.eightdigitz.country_picker.datasource.remote.CountriesRemoteDataSourceImpl
import sl.com.eightdigitz.country_picker.domain.repository.CountriesRepository
import sl.com.eightdigitz.country_picker.domain.usecase.CountriesUseCase
import sl.com.eightdigitz.country_picker.presentation.country_picker.CountriesViewModel
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
        CountriesViewModel(mCountriesUseCase = get())
    }
}

val useCaseModule: Module = module {
    factory { CountriesUseCase(mCountriesRepository = get()) }
}


val repositoryModule: Module = module {
    single<CountriesRepository> {
        CountriesRepositoryImpl(
            get()
        )
    }
}

val dataSourceModule: Module = module {
    single<CountriesDataSource> {
        CountriesRemoteDataSourceImpl(
            get()
        )
    }
}
