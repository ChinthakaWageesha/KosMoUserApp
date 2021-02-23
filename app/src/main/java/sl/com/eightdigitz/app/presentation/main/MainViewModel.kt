package sl.com.eightdigitz.app.presentation.main

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import sl.com.eightdigitz.network.SupportInterceptor
import sl.com.eightdigitz.presentation.extensions.getIdToken

class MainViewModel(
    supportInterceptor: SupportInterceptor,
    sharedPreferences: SharedPreferences
) : ViewModel() {

    init {
        supportInterceptor.idToken = sharedPreferences.getIdToken()
    }
}