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
import sl.com.eightdigitz.core.model.domain.DAuth0
import sl.com.eightdigitz.network.ErrorHandler

class OPTViewModel constructor(private val authUseCase: AuthUseCase) : ViewModel() {

    val liveDataGetOTP = MutableLiveData<Resource<DAuth0>>()
    private val compositeDisposable = CompositeDisposable()

    fun getOTP(phoneNumber: String){
        liveDataGetOTP.setLoading()
        compositeDisposable.add(
            authUseCase.getOTP(phoneNumber)
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe({
                     liveDataGetOTP.setSuccess(it, null)
                },{
                    liveDataGetOTP.setError(ErrorHandler.getApiErrorMessage(it))
                })
        )
    }


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
