package sl.com.eightdigitz.authentication.presentation.resetpassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sl.com.eightdigitz.authentication.datasource.model.Success
import sl.com.eightdigitz.authentication.domain.usecase.AuthUseCase
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.setError
import sl.com.eightdigitz.presentation.setLoading
import sl.com.eightdigitz.presentation.setSuccess
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ResetPasswordViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    val liveData =
        MutableLiveData<Resource<Success>>()

    private val compositeDisposable = CompositeDisposable()

    fun resetPassword(
        email: String,
        token: String,
        password: String,
        passwordConfirmation: String
    ) = compositeDisposable.add(
        authUseCase.resetPassword(email, token, password, passwordConfirmation)
            .doOnSubscribe {
                liveData.setLoading()
            }
            .subscribeOn(Schedulers.io())
            .subscribe(
                { liveData.setSuccess(it) },
                { liveData.setError(it.message) }
            )
    )

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}