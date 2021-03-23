package sl.com.eightdigitz.app.presentation.preferences

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import sl.com.eightdigitz.app.domain.usecase.ProfileUseCase
import sl.com.eightdigitz.core.model.domain.DPreference
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

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}