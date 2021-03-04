package sl.com.eightdigitz.app.domain.repository

import io.reactivex.Single
import sl.com.eightdigitz.client.apiSupports.requests.NewOrderRequest
import sl.com.eightdigitz.core.model.domain.DOrder

interface OrderRepository {

    fun placeNewOrder(newOrderRequest: NewOrderRequest): Single<DOrder>
}