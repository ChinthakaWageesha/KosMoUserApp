package sl.com.eightdigitz.authentication.presentation.verifyOTP

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sl.com.eightdigitz.authentication.domain.usecase.AuthUseCase
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.setError
import sl.com.eightdigitz.presentation.setLoading
import sl.com.eightdigitz.presentation.setSuccess
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import sl.com.eightdigitz.core.model.domain.DOTP
import sl.com.eightdigitz.core.model.domain.DOTPToken
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.model.mapToPresentation
import sl.com.eightdigitz.network.ErrorHandler

class OTPViewModel constructor(private val authUseCase: AuthUseCase) : ViewModel() {

    val liveDataOTP = MutableLiveData<Resource<DOTP>>()
    val liveDataOTPToken = MutableLiveData<Resource<DOTPToken>>()
    val liveDataUser = MutableLiveData<Resource<DUser>>()

    private val compositeDisposable = CompositeDisposable()

    fun getOTP(phoneNumber: String) {
        liveDataOTP.setLoading()
        compositeDisposable.add(
            authUseCase.getOTP(phoneNumber)
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe({
                    liveDataOTP.setSuccess(it, null)
                }, {
                    liveDataOTP.setError(ErrorHandler.getApiErrorMessage(it).mapToPresentation())
                })
        )
    }

    fun getOTPToken(phoneNumber: String, otp: String) {
        liveDataOTPToken.setLoading()
        compositeDisposable.add(
            authUseCase.getOTPToken(phoneNumber, otp)
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe({
                    liveDataOTPToken.setSuccess(it, null)
                }, {
                    liveDataOTPToken.setError(ErrorHandler.getApiErrorMessage(it).mapToPresentation())
                })
        )
    }

    fun getUserByIDToken(idToken: String) {
        liveDataUser.setLoading()
        compositeDisposable.add(
            authUseCase.getUserByIDToken(idToken)
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe({
                    liveDataUser.setSuccess(it, null)
                }, {
                    liveDataUser.setError(ErrorHandler.getApiErrorMessage(it).mapToPresentation())
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
