package sl.com.eightdigitz.chat.di

import sl.com.eightdigitz.chat.data.datasource.FileUploadDataSource
import sl.com.eightdigitz.chat.data.datasource.UserDataSource
import sl.com.eightdigitz.chat.data.repository.FileUploadRepositoryImpl
import sl.com.eightdigitz.chat.data.repository.UserRepositoryImpl
import sl.com.eightdigitz.chat.datasource.remote.FileUploadDataSourceImpl
import sl.com.eightdigitz.chat.datasource.remote.UserDataSourceImpl
import sl.com.eightdigitz.chat.domain.repository.FileUploadRepository
import sl.com.eightdigitz.chat.domain.repository.UserRepository
import sl.com.eightdigitz.chat.domain.usecase.FileUploadUseCase
import sl.com.eightdigitz.chat.domain.usecase.UserUseCase
import sl.com.eightdigitz.chat.presentation.ui.chat.ChatViewModel
import sl.com.eightdigitz.chat.presentation.ui.chat.FCMViewModel
import sl.com.eightdigitz.chat.presentation.ui.user.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun injectChatModule() = loadFeature

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
    viewModel { ChatViewModel(fileUploadUseCase = get()) }
    viewModel { FCMViewModel() }
    viewModel { UserViewModel(userUseCase = get()) }
}

val useCaseModule: Module = module {
    factory { FileUploadUseCase(fileUploadRepository = get()) }
    factory { UserUseCase(userRepository = get()) }
}

val repositoryModule: Module = module {
    single<FileUploadRepository> { FileUploadRepositoryImpl(fileUploadDataSource = get()) }
    single<UserRepository> { UserRepositoryImpl(userDataSource = get()) }
}


val dataSourceModule: Module = module {
    single<FileUploadDataSource> {
        FileUploadDataSourceImpl(
          chatApi = get()
        )
    }
    single<UserDataSource> {
        UserDataSourceImpl(userApi = get())
    }
}
