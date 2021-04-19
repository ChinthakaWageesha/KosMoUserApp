package sl.com.eightdigitz.app.presentation.explore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import sl.com.eightdigitz.app.domain.usecase.ExploreUseCase
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.model.mapToPresentation
import sl.com.eightdigitz.network.ErrorHandler
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.setError
import sl.com.eightdigitz.presentation.setLoading
import sl.com.eightdigitz.presentation.setSuccess

class ExploreViewModel(
    private val exploreUseCase: ExploreUseCase
) : ViewModel() {

    val liveDataExplore = MutableLiveData<Resource<List<DUser>>>()
    private val compositeDisposable = CompositeDisposable()

    fun getTalents() {
        liveDataExplore.setLoading()
        compositeDisposable.add(
            exploreUseCase.getTalents()
                .subscribeOn(Schedulers.io())
                .map { it.data }
                .subscribe({
                    liveDataExplore.setSuccess(it!!)
                }, {
                    liveDataExplore.setError(
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