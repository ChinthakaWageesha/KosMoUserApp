package sl.com.eightdigitz.notifications.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import sl.com.eightdigitz.notifications.presentation.data.datasource.NotificationDataSource
import sl.com.eightdigitz.notifications.presentation.data.repository.NotificationRepositoryImpl
import sl.com.eightdigitz.notifications.presentation.datasource.remote.NotificationDataSourceImpl
import sl.com.eightdigitz.notifications.presentation.domain.repository.NotificationRepository
import sl.com.eightdigitz.notifications.presentation.domain.usecase.NotificationUseCase
import sl.com.eightdigitz.notifications.presentation.notification.NotificationViewModel

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
        NotificationViewModel(
            notificationUseCase = get()
        )
    }
}


val useCaseModule: Module = module {
    factory { NotificationUseCase(notificationRepository = get()) }
}

val repositoryModule: Module = module {
    single {
        NotificationRepositoryImpl(
            notificationDataSource = get()
        ) as NotificationRepository
    }
}

val dataSourceModule: Module = module(override = true) {
    single {
        NotificationDataSourceImpl(
            notificationApi = get()
        ) as NotificationDataSource
    }
}