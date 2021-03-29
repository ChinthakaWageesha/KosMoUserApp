package sl.com.eightdigitz.app.data.repository

import io.reactivex.Single
import sl.com.eightdigitz.app.data.datasource.OrderDataSource
import sl.com.eightdigitz.app.domain.repository.OrderRepository
import sl.com.eightdigitz.client.apiSupports.requests.NewOrderRequest
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DOrder

class OrderRepositoryImpl constructor(
    private val orderDataSource: OrderDataSource
) : OrderRepository {

    override fun placeNewOrder(newOrderRequest: NewOrderRequest): Single<DOrder> =
        orderDataSource.placeNewOrder(newOrderRequest)

    override fun getOrdersByStage(stages: String): Single<ListResponse<DOrder>> =
        orderDataSource.getOrdersByStage(stages)
}