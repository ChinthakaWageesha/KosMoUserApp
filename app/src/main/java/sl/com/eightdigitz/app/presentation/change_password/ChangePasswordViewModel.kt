package sl.com.eightdigitz.app.presentation.change_password

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sl.com.eightdigitz.app.domain.usecase.AuthUseCase
import sl.com.eightdigitz.models.Success
import sl.com.eightdigitz.network.ErrorHandler
import sl.com.eightdigitz.network.SupportInterceptor
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.extensions.getToken
import sl.com.eightdigitz.presentation.setError
import sl.com.eightdigitz.presentation.setLoading
import sl.com.eightdigitz.presentation.setSuccess
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ChangePasswordViewModel(
    private val authUseCase: AuthUseCase,
    private val supportInterceptor: SupportInterceptor,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    init {
        supportInterceptor.accessToken = sharedPreferences.getToken()
    }

    val liveData = MutableLiveData<Resource<Success>>()
    private val compositeDisposable = CompositeDisposable()

    fun changePassword(oldPassword: String, password: String) {
        liveData.setLoading()
        compositeDisposable.add(
            authUseCase.passwordChange(oldPassword, password)
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe(
                    {
                        liveData.setSuccess(it, "Password updated successfully.")
                    },
                    {
                        liveData.setError(ErrorHandler.getApiErrorMessage(it))
                    })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
