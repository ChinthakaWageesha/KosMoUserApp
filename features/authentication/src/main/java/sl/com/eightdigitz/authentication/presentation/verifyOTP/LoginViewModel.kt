package sl.com.eightdigitz.authentication.presentation.verifyOTP

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sl.com.eightdigitz.authentication.domain.usecase.AuthUseCase
import sl.com.eightdigitz.core.domain.model.mapToDomain
import sl.com.eightdigitz.core.domain.model.mapToPresentation
import sl.com.eightdigitz.core.presentation.model.PUser
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.setError
import sl.com.eightdigitz.presentation.setLoading
import sl.com.eightdigitz.presentation.setSuccess
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel constructor(private val authUseCase: AuthUseCase) : ViewModel() {

    val liveData = MutableLiveData<Resource<PUser>>()

    private val compositeDisposable = CompositeDisposable()

    fun get(pUser: PUser) =
        compositeDisposable.add(authUseCase.get(pUser.mapToDomain())
            .doOnSubscribe {
                liveData.setLoading()
            }
            .subscribeOn(Schedulers.io())
            .map { it.mapToPresentation() }
            .subscribe(
                { liveData.setSuccess(it) },
                { liveData.setError(it.message) })
        )

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
