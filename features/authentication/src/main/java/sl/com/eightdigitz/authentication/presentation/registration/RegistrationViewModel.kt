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
import sl.com.eightdigitz.client.apiSupports.models.FirebaseToken
import sl.com.eightdigitz.client.apiSupports.requests.AddUserPreferenceRequest
import sl.com.eightdigitz.client.apiSupports.requests.RegisterRequest
import sl.com.eightdigitz.core.model.domain.DFirebaseToken
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.models.Success
import sl.com.eightdigitz.network.ErrorHandler

class RegistrationViewModel constructor(private val authUseCase: AuthUseCase) : ViewModel() {

    val liveDataSaveUser = MutableLiveData<Resource<PUser>>()
    val liveDataCategories = MutableLiveData<Resource<List<DPreference>>>()
    val liveDataPreference = MutableLiveData<Resource<DUser>>()
    val liveDataNotification = MutableLiveData<Resource<Success>>()
    val liveDataFirebaseToken = MutableLiveData<Resource<DFirebaseToken>>()
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
                    liveDataCategories.setError(
                        ErrorHandler.getApiErrorMessage(it).mapToPresentation()
                    )
                })
        )
    }

    fun registerFirebaseToken(request: FirebaseToken) {
        compositeDisposable.add(
            authUseCase.registerFirebaseToken(request)
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe({
                    liveDataFirebaseToken.setSuccess(it, null)
                }, {
                    liveDataFirebaseToken.setError(
                        ErrorHandler.getApiErrorMessage(it).mapToPresentation()
                    )
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
                {
                    liveDataSaveUser.setError(
                        ErrorHandler.getApiErrorMessage(it).mapToPresentation()
                    )
                }
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
                }, {
                    liveDataPreference.setError(
                        ErrorHandler.getApiErrorMessage(it).mapToPresentation()
                    )
                })
        )
    }

    fun requestWelcomeNotification(userId: String) {
        compositeDisposable.add(authUseCase.requestWelcomeNotification(userId)
            .subscribeOn(Schedulers.io())
            .map { it }
            .subscribe({
                liveDataNotification.setSuccess(it, null)
            }, {
                liveDataNotification.setError(
                    ErrorHandler.getApiErrorMessage(it).mapToPresentation()
                )
            })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
