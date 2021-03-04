package sl.com.eightdigitz.core

import android.content.Context
import sl.com.eightdigitz.presentation.Constant
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import sl.com.eightdigitz.core.data.datasource.HelpCenterDataSource
import sl.com.eightdigitz.core.data.repository.HelpCenterRepositoryImpl
import sl.com.eightdigitz.core.datasource.remote.HelpCenterRemoteDataSourceImpl
import sl.com.eightdigitz.core.domain.repository.HelpCenterRepository
import sl.com.eightdigitz.core.domain.usecase.HelpCenterUseCase
import sl.com.eightdigitz.core.ui.HelpCenterViewModel

val sharedPreferencesModule: Module = module {
    single {
        androidApplication().getSharedPreferences(Constant.PREF_NAME, Context.MODE_PRIVATE)
    }
}

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
        HelpCenterViewModel(
            helpCenterUseCase = get()
        )
    }
}

val useCaseModule: Module = module(override = true) {
    factory { HelpCenterUseCase(helpCenterRepository = get()) }
}

val repositoryModule: Module = module(override = true) {
    single {
        HelpCenterRepositoryImpl(
            helpCenterDataSource = get()
        ) as HelpCenterRepository
    }
}

val dataSourceModule: Module = module(override = true) {
    single {
        HelpCenterRemoteDataSourceImpl(
            context = get(),
            helpCenterApi = get()
        ) as HelpCenterDataSource
    }
}

