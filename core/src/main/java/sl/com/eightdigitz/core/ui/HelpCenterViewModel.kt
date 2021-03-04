package sl.com.eightdigitz.core.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import sl.com.eightdigitz.core.domain.usecase.HelpCenterUseCase
import sl.com.eightdigitz.core.model.domain.DAppInfo
import sl.com.eightdigitz.country_picker.domain.model.DCountry
import sl.com.eightdigitz.network.ErrorHandler
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.setError
import sl.com.eightdigitz.presentation.setLoading
import sl.com.eightdigitz.presentation.setSuccess

class HelpCenterViewModel(private val helpCenterUseCase: HelpCenterUseCase) : ViewModel() {

    val liveDataCountries = MutableLiveData<Resource<MutableList<DCountry>>>()
    val liveDataAppInfo = MutableLiveData<Resource<DAppInfo>>()
    private val mCompositeDisposable = CompositeDisposable()

    fun getCountries(): MutableList<DCountry> {
        liveDataCountries.setLoading()
        mCompositeDisposable.add(
            Observable.just(helpCenterUseCase.getCountries())
                .subscribeOn(Schedulers.newThread())
                .map { it }
                .subscribe({
                    liveDataCountries.setSuccess(it, "")
                }, {
                    liveDataCountries.setError(it.message)
                })
        )

        return helpCenterUseCase.getCountries()
    }

    fun getAppInfo(country: String, language: String) {
        liveDataAppInfo.setLoading()
        mCompositeDisposable.add(
            helpCenterUseCase.getAppInfo(country, language)
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe({
                    liveDataAppInfo.setSuccess(it, null)
                }, {
                    liveDataAppInfo.setError(ErrorHandler.getApiErrorMessage(it))
                })
        )
    }

    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }
}