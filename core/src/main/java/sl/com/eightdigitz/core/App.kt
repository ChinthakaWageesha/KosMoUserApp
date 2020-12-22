package sl.com.eightdigitz.core

import android.app.Application
import sl.com.eightdigitz.di.apiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)

//          injecting APIs
            modules(
                listOf(
                    apiModule, sharedPreferencesModule
                )
            )
        }
    }
}
