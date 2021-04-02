package sl.com.eightdigitz.app.data.repository

import io.reactivex.Single
import sl.com.eightdigitz.app.data.datasource.OrderDataSource
import sl.com.eightdigitz.app.domain.repository.OrderRepository
import sl.com.eightdigitz.client.apiSupports.requests.NewOrderRequest
import sl.com.eightdigitz.client.apiSupports.requests.UpdateOrderStatusRequest
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DOrder
import sl.com.eightdigitz.core.model.domain.DUpdateOrderStatus
import sl.com.eightdigitz.core.model.domain.DUser

class OrderRepositoryImpl constructor(
    private val orderDataSource: OrderDataSource
) : OrderRepository {

    override fun getTalentById(talentId: String): Single<DUser> =
        orderDataSource.getTalentById(talentId)

    override fun placeNewOrder(newOrderRequest: NewOrderRequest): Single<DOrder> =
        orderDataSource.placeNewOrder(newOrderRequest)

    override fun getOrdersByStage(stages: String): Single<ListResponse<DOrder>> =
        orderDataSource.getOrdersByStage(stages)

    override fun updateOrderStatus(request: UpdateOrderStatusRequest): Single<DUpdateOrderStatus> =
        orderDataSource.updateOrderStatus(request)
}