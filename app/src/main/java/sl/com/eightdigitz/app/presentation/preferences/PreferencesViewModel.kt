package sl.com.eightdigitz.app.presentation.preferences

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import sl.com.eightdigitz.app.domain.usecase.ProfileUseCase
import sl.com.eightdigitz.client.apiSupports.requests.AddUserPreferenceRequest
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.domain.DUserPreference
import sl.com.eightdigitz.core.model.mapToPresentation
import sl.com.eightdigitz.network.ErrorHandler
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.setError
import sl.com.eightdigitz.presentation.setLoading
import sl.com.eightdigitz.presentation.setSuccess

class PreferencesViewModel constructor(
    private val profileUseCase: ProfileUseCase
) : ViewModel() {

    val liveDataPreferences = MutableLiveData<Resource<List<DPreference>>>()
    val liveDataSetPreferences = MutableLiveData<Resource<DUserPreference>>()
    private val compositeDisposable = CompositeDisposable()

    fun getPreferences() {
        liveDataPreferences.setLoading()
        compositeDisposable.add(
            profileUseCase.getPreferences()
                .subscribeOn(Schedulers.io())
                .map { it.data }
                .subscribe({
                    liveDataPreferences.setSuccess(it!!, null)
                }, {
                    liveDataPreferences.setError(
                        ErrorHandler.getApiErrorMessage(it).mapToPresentation()
                    )
                })
        )
    }

    fun setPreferences(request: AddUserPreferenceRequest) {
        liveDataSetPreferences.setLoading()
        compositeDisposable.add(
            profileUseCase.setPreferences(request)
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe({
                    liveDataSetPreferences.setSuccess(it, null)
                }, {
                    liveDataPreferences.setError(
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