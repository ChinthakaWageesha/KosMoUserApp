package sl.com.eightdigitz.app.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import sl.com.eightdigitz.app.domain.usecase.SearchUseCase
import sl.com.eightdigitz.client.apiSupports.requests.LogSearchRequest
import sl.com.eightdigitz.core.model.domain.DUserSearch
import sl.com.eightdigitz.network.ErrorHandler
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.setError
import sl.com.eightdigitz.presentation.setLoading
import sl.com.eightdigitz.presentation.setSuccess

class LogSearchViewModel(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    val liveDataPreferenceSearch = MutableLiveData<Resource<DUserSearch>>()
    private val compositeDisposable = CompositeDisposable()

    fun logPreferenceSearch(logSearchRequest: LogSearchRequest){
        compositeDisposable.add(
            searchUseCase.logPreferenceSearch(logSearchRequest)
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe({
                    liveDataPreferenceSearch.setSuccess(it, null)
                },{
                    liveDataPreferenceSearch.setError(ErrorHandler.getApiErrorMessage(it))
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}