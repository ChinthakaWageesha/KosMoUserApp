package sl.com.eightdigitz.app.presentation.settings


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import sl.com.eightdigitz.app.domain.usecase.SettingsUseCase
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.mapToPresentation
import sl.com.eightdigitz.network.ErrorHandler
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.setError
import sl.com.eightdigitz.presentation.setLoading
import sl.com.eightdigitz.presentation.setSuccess

class SettingsViewModel constructor(
    private val settingsUseCase: SettingsUseCase
) : ViewModel() {

    val liveDataLogout = MutableLiveData<Resource<ListResponse<DPreference>>>()
    private val compositeDisposable = CompositeDisposable()

    fun logout() {
        liveDataLogout.setLoading()
        compositeDisposable.add(
            settingsUseCase.logout()
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe({
                    liveDataLogout.setSuccess(it!!, null)
                }, {
                    liveDataLogout.setError(
                        ErrorHandler.getApiErrorMessage(it).mapToPresentation()
                    )
                })
        )
    }
}