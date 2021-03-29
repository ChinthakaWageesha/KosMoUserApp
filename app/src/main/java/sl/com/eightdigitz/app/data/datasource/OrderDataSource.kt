package sl.com.eightdigitz.app.data.datasource

import io.reactivex.Single
import sl.com.eightdigitz.client.apiSupports.requests.NewOrderRequest
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DOrder

interface OrderDataSource {

    fun placeNewOrder(newOrderRequest: NewOrderRequest): Single<DOrder>

    fun getOrdersByStage(stages: String): Single<ListResponse<DOrder>>
}