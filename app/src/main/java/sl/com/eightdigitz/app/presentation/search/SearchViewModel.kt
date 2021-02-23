package sl.com.eightdigitz.app.presentation.search

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import sl.com.eightdigitz.app.domain.usecase.SearchUseCase
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.model.presentation.PUser
import sl.com.eightdigitz.network.ErrorHandler
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.extensions.getUserString
import sl.com.eightdigitz.presentation.extensions.jsonStringMapTo
import sl.com.eightdigitz.presentation.setError
import sl.com.eightdigitz.presentation.setLoading
import sl.com.eightdigitz.presentation.setSuccess

class SearchViewModel(
    private val mSharedPreferences: SharedPreferences,
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    val liveDataCategories = MutableLiveData<Resource<List<DPreference>>>()
    val liveDataRecentSearches = MutableLiveData<Resource<List<DUser>>>()
    private val compositeDisposable = CompositeDisposable()

    fun getHomeCategories() {
        liveDataCategories.setLoading()
        compositeDisposable.add(
            searchUseCase.getHomeCategories()
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

        val mUser = mSharedPreferences.getUserString()?.jsonStringMapTo<PUser>()

        liveDataRecentSearches.setLoading()
        compositeDisposable.add(
            searchUseCase.getRecentSearchedTalents(userId = mUser?.id!!)
                .subscribeOn(Schedulers.io())
                .map { it.data }
                .subscribe({
                    liveDataRecentSearches.setSuccess(it!!, null)
                }, {
                    liveDataRecentSearches.setError(ErrorHandler.getApiErrorMessage(it))
                })
        )
    }
}
