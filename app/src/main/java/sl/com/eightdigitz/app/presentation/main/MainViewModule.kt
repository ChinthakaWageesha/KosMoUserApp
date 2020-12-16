package sl.com.eightdigitz.app.presentation.main

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sl.com.eightdigitz.app.domain.usecase.AuthUseCase
import sl.com.eightdigitz.core.presentation.model.PUser
import sl.com.eightdigitz.models.Success
import sl.com.eightdigitz.network.ErrorHandler
import sl.com.eightdigitz.network.SupportInterceptor
import sl.com.eightdigitz.presentation.*
import sl.com.eightdigitz.presentation.extensions.get
import sl.com.eightdigitz.presentation.extensions.getToken
import sl.com.eightdigitz.presentation.extensions.jsonStringMapTo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModule(
    private val authUseCase: AuthUseCase,
    private val supportInterceptor: SupportInterceptor,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    init {
        supportInterceptor.accessToken = sharedPreferences.getToken()
    }

    val liveData = MutableLiveData<Resource<Success>>()

    private val compositeDisposable = CompositeDisposable()

    fun logoutUser() {
        liveData.setLoading()
        compositeDisposable.add(
            authUseCase.logoutUser()
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe(
                    {
                        liveData.setSuccess(it, null)
                    },
                    {
                        liveData.setError(ErrorHandler.getApiErrorMessage(it))
                    })
        )
    }

    fun getUser(): PUser? = sharedPreferences.get<String>(Constant.PREF_USER)?.jsonStringMapTo<PUser>()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
