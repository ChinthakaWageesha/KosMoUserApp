package sl.com.eightdigitz.country_picker.presentation.country_picker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sl.com.eightdigitz.country_picker.datasource.model.mapToPresenter
import sl.com.eightdigitz.country_picker.domain.usecase.CountriesUseCase
import sl.com.eightdigitz.country_picker.presentation.models.PCountry
import sl.com.eightdigitz.country_picker.presentation.utils.Resources
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import setError
import setLoading
import setSuccess

class CountriesViewModel(private val mCountriesUseCase: CountriesUseCase) : ViewModel() {


    val mLiveData = MutableLiveData<Resources<MutableList<PCountry>>>()
    private val mCompositeDisposable = CompositeDisposable()


    fun getCountries(): MutableList<PCountry> {
        mLiveData.setLoading()
        mCompositeDisposable.add(
            Observable.just(mCountriesUseCase.getCountries())
                .subscribeOn(Schedulers.newThread())
                .map { it.mapToPresenter() }
                .subscribe({
                    mLiveData.setSuccess(it, "")
                }, {
                    mLiveData.setError(it.message)
                })
        )

        return mCountriesUseCase.getCountries().mapToPresenter()

    }


    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }
}