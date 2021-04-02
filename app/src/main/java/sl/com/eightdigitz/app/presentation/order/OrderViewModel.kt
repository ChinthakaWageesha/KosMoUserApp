package sl.com.eightdigitz.app.presentation.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.load.HttpException
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import sl.com.eightdigitz.app.domain.usecase.OrderUseCase
import sl.com.eightdigitz.client.apiSupports.requests.NewOrderRequest
import sl.com.eightdigitz.client.apiSupports.requests.UpdateOrderStatusRequest
import sl.com.eightdigitz.core.model.domain.DOrder
import sl.com.eightdigitz.core.model.domain.DUpdateOrderStatus
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.model.mapToPresentation
import sl.com.eightdigitz.network.ErrorHandler
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.setError
import sl.com.eightdigitz.presentation.setLoading
import sl.com.eightdigitz.presentation.setSuccess

class OrderViewModel(
    private val orderUseCase: OrderUseCase
) : ViewModel() {

    val liveDataTalent = MutableLiveData<Resource<DUser>>()
    val liveDataPlaceOrder = MutableLiveData<Resource<DOrder>>()
    val liveDataGetOrders = MutableLiveData<Resource<List<DOrder>>>()
    val liveDataUpdateStatus = MutableLiveData<Resource<DUpdateOrderStatus>>()
    private val compositeDisposable = CompositeDisposable()

    fun getTalentById(talentId: String) {
        liveDataTalent.setLoading()
        compositeDisposable.add(
            orderUseCase.getTalentById(talentId)
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe({
                    liveDataTalent.setSuccess(it)
                }, {
                    if ((it as retrofit2.HttpException).code() == 404) {
                        liveDataTalent.setError(it.code())
                    } else {
                        liveDataTalent.setError(
                            ErrorHandler.getApiErrorMessage(it).mapToPresentation()
                        )
                    }
                })
        )
    }

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
                    if ((it as retrofit2.HttpException).code() == 404) {
                        liveDataGetOrders.setError(it.code())
                    } else {
                        liveDataGetOrders.setError(
                            ErrorHandler.getApiErrorMessage(it).mapToPresentation()
                        )
                    }
                })
        )
    }

    fun updateOrderStatus(request: UpdateOrderStatusRequest){
        liveDataUpdateStatus.setLoading()
        compositeDisposable.add(
            orderUseCase.updateOrderStatus(request)
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe({
                   liveDataUpdateStatus.setSuccess(it, null)
                },{
                    liveDataUpdateStatus.setError(
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