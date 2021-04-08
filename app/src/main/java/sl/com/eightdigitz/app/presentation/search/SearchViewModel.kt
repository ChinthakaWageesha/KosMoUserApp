package sl.com.eightdigitz.app.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import sl.com.eightdigitz.app.domain.usecase.SearchUseCase
import sl.com.eightdigitz.core.model.domain.DUserSearch
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.model.mapToPresentation
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
    val liveDataTalents = MutableLiveData<Resource<List<DUser>>>()
    private val compositeDisposable = CompositeDisposable()
    var searchKeyPreferenceTalents: String? = null
    var searchKeyTalents: String? = null

    fun getSearchCategories() {
        liveDataCategories.setLoading()
        compositeDisposable.add(
            searchUseCase.getSearchCategories()
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

    fun getRecentSearches() {
        liveDataRecentSearches.setLoading()
        compositeDisposable.add(
            searchUseCase.getRecentSearchedTalents()
                .subscribeOn(Schedulers.io())
                .map { it.data }
                .subscribe({
                    liveDataRecentSearches.setSuccess(it!!, null)
                }, {
                    liveDataRecentSearches.setError(
                        ErrorHandler.getApiErrorMessage(it).mapToPresentation()
                    )
                })
        )
    }

    fun getTalentsByPreference(preferenceId: String) {
        if (searchKeyPreferenceTalents.isNullOrEmpty()) {
            liveDataTalentByPreferences.setLoading()
        }
        compositeDisposable.add(
            searchUseCase.getTalentsByPreference(
                preferenceId = preferenceId,
                searchKey = searchKeyPreferenceTalents
            )
                .subscribeOn(Schedulers.io())
                .map { it.data }
                .subscribe({
                    liveDataTalentByPreferences.setSuccess(it!!, null)
                }, {
                    liveDataTalentByPreferences.setError(
                        ErrorHandler.getApiErrorMessage(it).mapToPresentation()
                    )
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
                    liveDataRemoveRecent.setError(
                        ErrorHandler.getApiErrorMessage(it).mapToPresentation()
                    )
                })
        )
    }

    fun getTalents() {
        if (searchKeyTalents.isNullOrEmpty()) {
            liveDataTalents.setLoading()
        }

        compositeDisposable.add(
            searchUseCase.getTalents(
                searchKey = searchKeyTalents
            )
                .subscribeOn(Schedulers.io())
                .map { it.data }
                .subscribe({
                    liveDataTalents.setSuccess(it!!, null)
                }, {
                    liveDataTalents.setError(
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
