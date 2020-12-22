package sl.com.eightdigitz.core

import android.content.Context
import sl.com.eightdigitz.presentation.Constant
import org.koin.android.ext.koin.androidApplication
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

val sharedPreferencesModule: Module = module {
    single {
        androidApplication().getSharedPreferences(Constant.PREF_NAME, Context.MODE_PRIVATE)
    }
}
