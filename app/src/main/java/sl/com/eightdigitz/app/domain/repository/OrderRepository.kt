package sl.com.eightdigitz.app.domain.repository

import io.reactivex.Single
import sl.com.eightdigitz.client.apiSupports.requests.NewOrderRequest
import sl.com.eightdigitz.client.apiSupports.requests.UpdateOrderStatusRequest
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DOrder
import sl.com.eightdigitz.core.model.domain.DUpdateOrderStatus
import sl.com.eightdigitz.core.model.domain.DUser

interface OrderRepository {

    fun getTalentById(talentId: String): Single<DUser>

    fun placeNewOrder(newOrderRequest: NewOrderRequest): Single<DOrder>

    fun getOrdersByStage(stages: String): Single<ListResponse<DOrder>>

    fun updateOrderStatus(request: UpdateOrderStatusRequest): Single<DUpdateOrderStatus>
}