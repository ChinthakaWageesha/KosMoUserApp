package sl.com.eightdigitz.app.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import sl.com.eightdigitz.app.domain.usecase.SearchUseCase
import sl.com.eightdigitz.client.apiSupports.requests.LogSearchRequest
import sl.com.eightdigitz.core.model.domain.DUserSearch
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.network.ErrorHandler
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.setError
import sl.com.eightdigitz.presentation.setLoading
import sl.com.eightdigitz.presentation.setSuccess

class SearchViewModel(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    val liveDataCategories = MutableLiveData<Resource<List<DPreference>>>()
    val liveDataRecentSearches = MutableLiveData<Resource<List<DUser>>>()
    val liveDataRemoveRecent = MutableLiveData<Resource<DUserSearch>>()
    val liveDataTalentByPreferences = MutableLiveData<Resource<List<DUser>>>()
    private val compositeDisposable = CompositeDisposable()

    fun getSearchCategories() {
        liveDataCategories.setLoading()
        compositeDisposable.add(
            searchUseCase.getSearchCategories()
                .subscribeOn(Schedulers.io())
                .map { it.data }
                .subscribe({
                    liveDataCategories.setSuccess(it!!, null)
                }, {
                    liveDataCategories.setError(ErrorHandler.getApiErrorMessage(it))
                })
        )
    }

    fun getRecentSearches() {
        liveDataRecentSearches.setLoading()
        compositeDisposable.add(
            searchUseCase.getRecentSearchedTalents()
                .subscribeOn(Schedulers.io())
                .map { it.data }
                .subscribe({
                    liveDataRecentSearches.setSuccess(it!!, null)
                }, {
                    liveDataRecentSearches.setError(ErrorHandler.getApiErrorMessage(it))
                })
        )
    }

    fun getTalentsByPreference(preferenceId: String) {
        liveDataTalentByPreferences.setLoading()
        compositeDisposable.add(
            searchUseCase.getTalentsByPreference(preferenceId)
                .subscribeOn(Schedulers.io())
                .map { it.data }
                .subscribe({
                    liveDataTalentByPreferences.setSuccess(it!!, null)
                }, {
                    liveDataTalentByPreferences.setError(ErrorHandler.getApiErrorMessage(it))
                })
        )
    }

    fun removeRecentlyViewedProfile(ownerId: String, searchType: String) {
        compositeDisposable.add(
            searchUseCase.removeRecentViewedProfile(
                ownerId = ownerId,
                searchType = searchType
            )
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe({
                    liveDataRemoveRecent.setSuccess(it, null)
                }, {
                    liveDataRemoveRecent.setError(ErrorHandler.getApiErrorMessage(it))
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
