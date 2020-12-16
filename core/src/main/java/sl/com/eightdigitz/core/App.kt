package sl.com.eightdigitz.core

import android.app.Application
import sl.com.eightdigitz.core.di.sharedPreferencesModule
import sl.com.eightdigitz.di.apiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Unique initialization of Dependency Injection library to allow the use of application context
        startKoin {
            androidContext(this@App)

//          injecting APIs
            modules(listOf(
                apiModule, sharedPreferencesModule
            ))
        }
    }
}
