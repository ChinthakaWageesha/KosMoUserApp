package sl.com.eightdigitz.app.data.datasource

import io.reactivex.Single
import sl.com.eightdigitz.client.apiSupports.requests.NewOrderRequest
import sl.com.eightdigitz.core.model.domain.DOrder

interface OrderDataSource {

    fun placeNewOrder(newOrderRequest: NewOrderRequest): Single<DOrder>
}