package sl.com.eightdigitz.authentication.presentation.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sl.com.eightdigitz.authentication.domain.usecase.AuthUseCase
import sl.com.eightdigitz.core.model.mapToPresentation
import sl.com.eightdigitz.core.model.presentation.PUser
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.setError
import sl.com.eightdigitz.presentation.setLoading
import sl.com.eightdigitz.presentation.setSuccess
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import sl.com.eightdigitz.client.models.AddUserPreferenceRequest
import sl.com.eightdigitz.client.models.RegisterRequest
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.domain.DUserPreference
import sl.com.eightdigitz.network.ErrorHandler

class RegistrationViewModel constructor(private val authUseCase: AuthUseCase) : ViewModel() {

    val liveDataSaveUser = MutableLiveData<Resource<PUser>>()
    val liveDataCategories = MutableLiveData<Resource<List<DPreference>>>()
    val liveDataPreference = MutableLiveData<Resource<DUserPreference>>()
    private val compositeDisposable = CompositeDisposable()


    fun getPreferences() {
        liveDataCategories.setLoading()
        compositeDisposable.add(
            authUseCase.getPreferences()
                .subscribeOn(Schedulers.io())
                .map { it.data }
                .subscribe({
                    liveDataCategories.setSuccess(it!!, null)
                }, {
                    liveDataCategories.setError(ErrorHandler.getApiErrorMessage(it))
                })
        )
    }

    fun createAccount(request: RegisterRequest) = compositeDisposable.add(
        authUseCase.createAccount(request)
            .doOnSubscribe {
                liveDataSaveUser.setLoading()
            }
            .subscribeOn(Schedulers.io())
            .map { it.mapToPresentation() }
            .subscribe(
                { liveDataSaveUser.setSuccess(it) },
                { liveDataSaveUser.setError(ErrorHandler.getApiErrorMessage(it))}
            )
    )

    fun addUserPreferences(request: AddUserPreferenceRequest) {
        liveDataPreference.setLoading()
        compositeDisposable.add(
            authUseCase.addUserPreference(request)
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe({
                    liveDataPreference.setSuccess(it, null)
                },{
                    liveDataPreference.setError(ErrorHandler.getApiErrorMessage(it))
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
