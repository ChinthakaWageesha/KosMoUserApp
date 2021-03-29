package sl.com.eightdigitz.app.presentation.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import sl.com.eightdigitz.app.domain.usecase.OrderUseCase
import sl.com.eightdigitz.client.apiSupports.requests.NewOrderRequest
import sl.com.eightdigitz.core.model.domain.DOrder
import sl.com.eightdigitz.core.model.mapToPresentation
import sl.com.eightdigitz.network.ErrorHandler
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.setError
import sl.com.eightdigitz.presentation.setLoading
import sl.com.eightdigitz.presentation.setSuccess

class OrderViewModel(
    private val orderUseCase: OrderUseCase
) : ViewModel() {

    val liveDataPlaceOrder = MutableLiveData<Resource<DOrder>>()
    val liveDataGetOrders = MutableLiveData<Resource<List<DOrder>>>()
    private val compositeDisposable = CompositeDisposable()

    fun placeNewOrder(newOrderRequest: NewOrderRequest) {
        liveDataPlaceOrder.setLoading()
        compositeDisposable.add(
            orderUseCase.placeNewOrder(newOrderRequest)
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe({
                    liveDataPlaceOrder.setSuccess(it, null)
                }, {
                    liveDataPlaceOrder.setError(
                        ErrorHandler.getApiErrorMessage(it).mapToPresentation()
                    )
                })
        )
    }

    fun getOrdersByStages(stages: String) {
        liveDataGetOrders.setLoading()
        compositeDisposable.add(
            orderUseCase.getOrdersByStages(stages)
                .subscribeOn(Schedulers.io())
                .map { it.data }
                .subscribe({
                    liveDataGetOrders.setSuccess(it!!, null)
                }, {
                    liveDataPlaceOrder.setError(
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